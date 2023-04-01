package com.evolution.bootcamp.assignment.poker

import cats.implicits._
import com.evolution.bootcamp.assignment.poker.domain.CardParser._
import com.evolution.bootcamp.assignment.poker.Resolver._
import com.evolution.bootcamp.assignment.poker.domain._

object Main {
    def sort[PLAYER_CARDS](
        playersLiterals: List[String],
        parsePlayerCards: String => Either[CardParseErr, PLAYER_CARDS],
        calcBestHandWeight: PLAYER_CARDS => Either[CardParseErr, HWeight]
    ): Either[CardParseErr, List[Estimate]] =
        for {
            playersCards <- playersLiterals.map(parsePlayerCards).sequence
            bestHandWeights <- playersCards.map(calcBestHandWeight).sequence
        } yield sortByWeight(bestHandWeights, playersLiterals)

    def reprSorted(mbSortedEstimates: Either[CardParseErr, List[Estimate]]): String =
        (mbSortedEstimates map {
            case Nil => ""
            case sortedEstimates @ sHead :: sTail =>
                sortedEstimates.zip(sTail.map(_.bestHandWeight) :+ sHead.bestHandWeight)
                    .map{ case (estimate, nextWeight) =>
                        val separator = if (estimate.bestHandWeight == nextWeight) "=" else " "
                        estimate.playerLiteral + separator
                    }
                    .foldLeft("")(_ + _)
                    .dropRight(1)
        }).fold(_.toString, identity)

    def main(line: String): String = {
        val ErrorPrefix = "Error: "

        line.split("\\s+").toList match {
            case "five-card-draw" :: playersLiterals =>
                reprSorted(sort(playersLiterals, parseNCards(PlayerCardsDraw.apply, 5), calcBestWeightDraw))
            case "texas-holdem" :: boardLiteral :: playersLiterals =>
                reprSorted(sort(playersLiterals, parseNCards(PlayerCardsTexas.apply, 2), calcBestWeightTexas(boardLiteral)))
            case "omaha-holdem" :: boardLiteral :: playersLiterals =>
                reprSorted(sort(playersLiterals, parseNCards(PlayerCardsOmaha.apply, 4), calcBestWeightOmaha(boardLiteral)))
            case _ :: _ => ErrorPrefix + "Unrecognized game type"
            case _ => ErrorPrefix + "Invalid input"
        }
    }
}
