package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.card.CardCreateRequest
import com.kmarzecki.trollo.api.card.CardResponse
import com.kmarzecki.trollo.api.card.CardShortInfoResponse
import com.kmarzecki.trollo.api.card.LaneCardsResponse
import com.kmarzecki.trollo.exception.ElementNotFoundException
import com.kmarzecki.trollo.exception.OperationNotPermittedException
import com.kmarzecki.trollo.model.CardEntity
import com.kmarzecki.trollo.repository.CardRepository
import com.kmarzecki.trollo.repository.LaneRepository
import com.kmarzecki.trollo.util.aggregateBy
import com.kmarzecki.trollo.util.ifNotThen
import com.kmarzecki.trollo.util.str
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.security.Principal
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class CardServiceImpl(
        val cardRepository: CardRepository,
        val laneRepository: LaneRepository,
        val boardService: BoardService
) : CardService {
    override fun getCardsForLanes(boardId: UUID, principal: Principal): List<LaneCardsResponse> {
        boardService.hasAccessToBoard(boardId, principal)
                .ifNotThen { throw OperationNotPermittedException() }
        return cardRepository.findAllByLane_Board_Id(boardId)
                .aggregateBy { it.lane.id }
                .entries
                .map {
                    LaneCardsResponse(
                            laneId = it.key!!,
                            cards = it.value.map(this::mapToShortResponse))
                }
    }

    override fun getCard(cardId: UUID): CardResponse {
        TODO("Not yet implemented")
    }

    fun mapToShortResponse(card: CardEntity) = CardShortInfoResponse(
            positionInLane = card.positionInLane,
            name = card.name
    )

    override fun create(request: CardCreateRequest, principal: Principal): CardShortInfoResponse {
        val lane = laneRepository.findByIdOrNull(request.laneId)
                ?: throw ElementNotFoundException("Lane", request.laneId.str)
        boardService.hasAccessToBoard(lane.board.id!!, principal)
                .ifNotThen { throw OperationNotPermittedException() }

        return cardRepository.save(CardEntity(
                name = request.name,
                description = request.description,
                positionInLane = 0,
                lane = lane,
                tasks = mutableSetOf(),
                files = mutableSetOf()
        )).let(this::mapToShortResponse)
    }
}