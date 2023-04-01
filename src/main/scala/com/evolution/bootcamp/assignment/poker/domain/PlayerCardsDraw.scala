package com.evolution.bootcamp.assignment.poker.domain

class PlayerCardsDraw private(val list: List[Card]) {
  def toList: List[Card] =
    list

  def toHand: Combination =
    Combination(list).get
}

object PlayerCardsDraw {
  def apply(list: List[Card]): Option[PlayerCardsDraw] =
    Option.when(list.length == 5)(new PlayerCardsDraw(list))
}

