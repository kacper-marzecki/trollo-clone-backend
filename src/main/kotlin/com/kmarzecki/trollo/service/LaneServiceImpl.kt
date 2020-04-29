package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.board.LaneResponse
import com.kmarzecki.trollo.api.lane.LaneCreateRequest
import com.kmarzecki.trollo.api.lane.LaneUpdateRequest
import com.kmarzecki.trollo.exception.ElementNotFoundException
import com.kmarzecki.trollo.exception.OperationNotPermittedException
import com.kmarzecki.trollo.model.LaneEntity
import com.kmarzecki.trollo.repository.BoardRepository
import com.kmarzecki.trollo.repository.LaneRepository
import com.kmarzecki.trollo.util.ifNotThen
import com.kmarzecki.trollo.util.str
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.security.Principal
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class LaneServiceImpl(
        val laneRepository: LaneRepository,
        val boardRepository: BoardRepository,
        val boardService: BoardService
) : LaneService {
    override fun update(request: LaneUpdateRequest, laneId: UUID, principal: Principal): LaneResponse {
        val lane = laneRepository.findByIdOrNull(laneId)
                ?: throw ElementNotFoundException("Lane", laneId.str)
        boardService.hasAccessToBoard(lane.board.id!!, principal)
                .ifNotThen { throw OperationNotPermittedException() }
        lane.name = request.name
        lane.positionInBoard = request.positionInBoard
        return laneRepository.save(lane)
                .let(this::map)
    }

    fun map(lane: LaneEntity) = LaneResponse(
            id = lane.id!!,
            name = lane.name,
            positionInBoard = lane.positionInBoard
    )

    override fun create(request: LaneCreateRequest, principal: Principal): LaneResponse {
        if (laneRepository.existsByNameAndBoard_Id(request.name, request.boardId)) {
            throw OperationNotPermittedException("Lane with name ${request.name} already exists")
        }
        val board = boardRepository.findByIdOrNull(request.boardId)
                ?: throw ElementNotFoundException("Board", request.boardId.str)
        boardService.hasAccessToBoard(request.boardId, principal)
                .ifNotThen { throw OperationNotPermittedException() }
        return laneRepository.save(LaneEntity(
                board = board,
                name = request.name,
                positionInBoard = 0
        )).let(this::map)
    }


}