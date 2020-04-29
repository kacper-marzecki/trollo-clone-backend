package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.board.BoardResponse
import com.kmarzecki.trollo.api.board.CreateBoardRequest
import com.kmarzecki.trollo.api.board.LaneResponse
import com.kmarzecki.trollo.api.board.UpdateBoardRequest
import com.kmarzecki.trollo.exception.OperationNotPermittedException
import com.kmarzecki.trollo.model.BoardEntity
import com.kmarzecki.trollo.repository.BoardRepository
import com.kmarzecki.trollo.repository.LaneRepository
import com.kmarzecki.trollo.repository.UserRepository
import org.springframework.stereotype.Service
import java.security.Principal
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class BoardServiceImpl(
        val repository: BoardRepository,
        val userRepository: UserRepository,
        val laneRepository: LaneRepository
) : BoardService {

    override fun create(request: CreateBoardRequest, principal: Principal): BoardResponse {
        if (repository.existsByNameAndUsers_Username(request.name, principal.name)) {
            throw OperationNotPermittedException("Already exists")
        }
        val user = userRepository.findByUsername(principal.name)!!
        val saved = repository.save(BoardEntity(null, request.name, listOf(user), ArrayList()))
        return BoardResponse(
                id = saved.id!!,
                name = saved.name
        )
    }

    private fun map(entity: BoardEntity): BoardResponse = BoardResponse(
            id = entity.id!!,
            name = entity.name
    )

    override fun getBoards(principal: Principal): List<BoardResponse> {
        return repository.findAllByUsers_Username(principal.name)
                .map(this::map)
    }

    override fun deleteBoard(boardId: UUID, principal: Principal) {
        repository.deleteAlLByIdAndUsers_Username(UUID.fromString(boardId.toString()), principal.name)
    }

    override fun update(request: UpdateBoardRequest, id: UUID, principal: Principal): BoardResponse {
        return BoardResponse(UUID.fromString("stub"), "stub")
    }

    override fun getLanesForBoard(boardId: UUID, principal: Principal): List<LaneResponse> {
        if (!repository.existsByIdAndUsers_Username(boardId, principal.name)) {
            throw OperationNotPermittedException("Not your board m8")
        }
        return laneRepository.findAllByBoard_Id(boardId)
                .map {
                    LaneResponse(
                            id = it.id!!,
                            name = it.name,
                            positionInBoard = it.positionInBoard
                    )
                }
    }

    override fun hasAccessToBoard(
            boardId: UUID,
            principal: Principal
    ) = repository.existsByIdAndUsers_Username(boardId, principal.name)
}