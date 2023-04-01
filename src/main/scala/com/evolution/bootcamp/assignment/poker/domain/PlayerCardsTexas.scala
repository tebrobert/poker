package com.evolution.bootcamp.assignment.poker.domain

class PlayerCardsTexas private(val list: List[Card]) {
  def toList: List[Card] =
    list
}

object PlayerCardsTexas {
  def apply(list: List[Card]): Option[PlayerCardsTexas] =
    Option.when(list.length == 2)(new PlayerCardsTexas(list))
}

