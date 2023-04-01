package com.evolution.bootcamp.assignment.poker.domain

import cats.implicits._
import com.evolution.bootcamp.assignment.poker.domain.CardRank._
import com.evolution.bootcamp.assignment.poker.domain.CardSuit._

object CardParser {
    sealed trait CardParseErr
    final case class BadCardsCount(input: String, expectedCardsCount: Int) extends CardParseErr
    final case class BadCardReprLen(input: String) extends CardParseErr
    final case class BadRank(input: String) extends CardParseErr
    final case class BadSuit(input: String) extends CardParseErr

    def parseTwoChars(inputStr: String): Option[(Char, Char)] =
        inputStr.toList match {
            case rankChar :: suitChar :: Nil => Some(rankChar, suitChar)
            case _ => None
        }

    def parseRank(inputChar: Char): Option[CRank] =
        Map('2' -> R2, '3' -> R3, '4' -> R4, '5' -> R5, '6' -> R6, '7' -> R7, '8' -> R8,
            '9' -> R9, 'T' -> RT, 'J' -> RJ, 'Q' -> RQ, 'K' -> RK, 'A' -> RA
        ).get(inputChar)

    def parseSuit(inputChar: Char): Option[CSuit] =
        Map('c' -> SC, 'd' -> SD, 'h' -> SH, 's' -> SS
        ).get(inputChar)

    def parseCard(inputStr: String): Either[CardParseErr, Card] = {
        for {
            twoChars <- parseTwoChars(inputStr).toRight(BadCardReprLen(inputStr))
            (rankChar, suitChar) = twoChars
            rank <- parseRank(rankChar).toRight(BadRank(inputStr))
            suit <- parseSuit(suitChar).toRight(BadSuit(inputStr))
        } yield Card(rank, suit)
    }

    def parseAllCards(inputStr: String): Either[CardParseErr, List[Card]] =
        inputStr.grouped(2).map(parseCard).toList.sequence

    def parseNCards[N_CARDS](
        parseVec: List[Card] => Option[N_CARDS], expectedCardsNum: Int
    )(inputStr: String): Either[CardParseErr, N_CARDS] =
        for {
            allCards <- parseAllCards(inputStr)
            vecCards <- parseVec(allCards).toRight(BadCardsCount(inputStr, expectedCardsNum))
        } yield vecCards
}
