package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.card.CardCreateRequest
import com.kmarzecki.trollo.api.card.CardResponse
import com.kmarzecki.trollo.api.card.CardShortInfoResponse
import com.kmarzecki.trollo.api.card.LaneCardsResponse
import java.security.Principal
import java.util.*

interface CardService  {
    fun getCardsForLanes(boardId: UUID, principal: Principal): List<LaneCardsResponse>
    fun getCard(cardId: UUID): CardResponse
    fun create(request: CardCreateRequest, principal: Principal): CardShortInfoResponse
}
