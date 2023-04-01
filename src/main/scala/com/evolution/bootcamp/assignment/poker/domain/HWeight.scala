package com.evolution.bootcamp.assignment.poker.domain

import com.evolution.bootcamp.assignment.poker.domain.CardRank.CRank
import com.evolution.bootcamp.assignment.poker.domain.HandRank.HRank

case class HWeight(handRank: HRank, cardsRanks: List[CRank])

