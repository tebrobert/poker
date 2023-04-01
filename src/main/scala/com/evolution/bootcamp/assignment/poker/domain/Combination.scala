package com.evolution.bootcamp.assignment.poker.domain

class Combination private(val list: List[Card]) {
  def toList: List[Card] =
    list
}

object Combination {
  def apply(list: List[Card]): Option[Combination] =
    Option.when(list.length == 5)(new Combination(list))
}
