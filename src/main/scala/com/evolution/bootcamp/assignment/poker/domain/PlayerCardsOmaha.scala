package com.evolution.bootcamp.assignment.poker.domain

class PlayerCardsOmaha private(val list: List[Card]) {
  def toList: List[Card] =
    list
}

object PlayerCardsOmaha {
  def apply(list: List[Card]): Option[PlayerCardsOmaha] =
    Option.when(list.length == 4)(new PlayerCardsOmaha(list))
}

