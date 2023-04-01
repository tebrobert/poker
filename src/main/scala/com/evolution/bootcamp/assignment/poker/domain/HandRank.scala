package com.evolution.bootcamp.assignment.poker.domain

object HandRank extends Enumeration {
  type HRank = Value
  val HighCard, Pair, TwoPairs, Trips, Straight, Flush, FullHouse, Quads, StraightFlush = Value
}
