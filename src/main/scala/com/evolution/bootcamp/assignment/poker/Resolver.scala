package com.evolution.bootcamp.assignment.poker

import com.evolution.bootcamp.assignment.poker.domain.CardParser._
import com.evolution.bootcamp.assignment.poker.domain._
import CardRank._
import HandRank._

object Resolver {
    def calcIfFlush(h: Combination): Boolean =
        h.toList.map(_.suit).toSet.size == 1

    def calcMbStraightRank(reversedRanks: List[CRank]): Option[CRank] = {
        if (reversedRanks == List(RA, R5, R4, R3, R2))
            Some(R5)
        else if (reversedRanks
            .zip(reversedRanks.tail)
            .map{ case (higher, lower) => higher.id - lower.id }
            .toSet == Set(1)
        ) Some(reversedRanks.head)
        else None
    }

    def calcWeight(hand: Combination): HWeight = {
        val reversedRanks = hand.toList.map(_.rank).sorted.reverse

        calcMbStraightRank(reversedRanks).fold {
            reversedRanks
                .groupBy(identity).values.toList
                .sortBy(_.head)
                .reverse
            match {
                case List(q, _, _, _) :: List(o) :: Nil => HWeight(Quads, List(q, o, R2, R2, R2))
                case List(o) :: List(q, _, _, _) :: Nil => HWeight(Quads, List(q, o, R2, R2, R2))

                case List(t, _, _) :: List(p, _) :: Nil => HWeight(FullHouse, List(t, p, R2, R2, R2))
                case List(p, _) :: List(t, _, _) :: Nil => HWeight(FullHouse, List(t, p, R2, R2, R2))

                case List(t, _, _) :: List(o2) :: List(o1) :: Nil => HWeight(Trips, List(t, o2, o1, R2, R2))
                case List(o2) :: List(t, _, _) :: List(o1) :: Nil => HWeight(Trips, List(t, o2, o1, R2, R2))
                case List(o2) :: List(o1) :: List(t, _, _) :: Nil => HWeight(Trips, List(t, o2, o1, R2, R2))

                case List(p2, _) :: List(p1, _) :: List(o) :: Nil => HWeight(TwoPairs, List(p2, p1, o, R2, R2))
                case List(p2, _) :: List(o) :: List(p1, _) :: Nil => HWeight(TwoPairs, List(p2, p1, o, R2, R2))
                case List(o) :: List(p2, _) :: List(p1, _) :: Nil => HWeight(TwoPairs, List(p2, p1, o, R2, R2))

                case List(p, _) :: List(o3) :: List(o2) :: List(o1) :: Nil => HWeight(Pair, List(p, o3, o2, o1, R2))
                case List(o3) :: List(p, _) :: List(o2) :: List(o1) :: Nil => HWeight(Pair, List(p, o3, o2, o1, R2))
                case List(o3) :: List(o2) :: List(p, _) :: List(o1) :: Nil => HWeight(Pair, List(p, o3, o2, o1, R2))
                case List(o3) :: List(o2) :: List(o1) :: List(p, _) :: Nil => HWeight(Pair, List(p, o3, o2, o1, R2))

                case _  =>
                    val handRank = if (calcIfFlush(hand)) Flush else HighCard
                    HWeight(handRank, reversedRanks)
            }
        } { straightRank =>
            val handRank = if (calcIfFlush(hand)) StraightFlush else Straight
            HWeight(handRank, List(straightRank, R2, R2, R2, R2))
        }
    }

    def compareEstimates(lower: Estimate, higher: Estimate): Boolean = {
        val lowerIds = lower.bestHandWeight.handRank.id :: lower.bestHandWeight.cardsRanks.map(_.id)
        val higherIds = higher.bestHandWeight.handRank.id :: higher.bestHandWeight.cardsRanks.map(_.id)

        (lowerIds.zip(higherIds)
            .map{ case (lowerId, higherId) => higherId - lowerId }
            .filter(_ != 0)
            :+ higher.playerLiteral.compareTo(lower.playerLiteral)
            ).head > 0
    }

    def sortByWeight(bestHandWeights: List[HWeight], playerLiterals: List[String]): List[Estimate] =
        playerLiterals.zip(bestHandWeights)
            .map{ case (playerLiteral, bestHandWeight) => Estimate(playerLiteral, bestHandWeight) }
            .sortWith(compareEstimates)

    def calcBestWeightDraw(playerCardsDraw: PlayerCardsDraw): Either[CardParseErr, HWeight] =
        Right(calcWeight(hand = playerCardsDraw.toHand))

    def calcBestWeightTexas(boardLiteral: String)(playerCardsTexas: PlayerCardsTexas): Either[CardParseErr, HWeight] =
        for {
            boardCards <- parseNCards(PlayerCardsDraw.apply, 5)(boardLiteral)
            possibleHands = (playerCardsTexas.toList ++ boardCards.toList).combinations(5).toList
            possibleWeights = possibleHands.map(h => calcWeight(PlayerCardsDraw(h).get.toHand))
        } yield sortByWeight(
            possibleWeights, playerLiterals = possibleWeights.map(_ => "")
        ).reverse.head.bestHandWeight

    def calcBestWeightOmaha(boardLiteral: String)(playerCardsOmaha: PlayerCardsOmaha): Either[CardParseErr, HWeight] = {
        for {
            cardsBoard <- parseNCards(PlayerCardsDraw.apply, 5)(boardLiteral)
            possibleHands = (for {
                boardCards3 <- cardsBoard.toList.combinations(3)
                playerCards2 <- playerCardsOmaha.toList.combinations(2)
            } yield playerCards2 ++ boardCards3).toList
            possibleWeights = possibleHands.map(h => calcWeight(PlayerCardsDraw(h).get.toHand))
        } yield sortByWeight(
            possibleWeights, playerLiterals = possibleWeights.map(_ => "")
        ).reverse.head.bestHandWeight
    }
}
