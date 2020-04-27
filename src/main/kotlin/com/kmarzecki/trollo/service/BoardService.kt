package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.board.BoardResponse
import com.kmarzecki.trollo.api.board.CreateBoardRequest
import com.kmarzecki.trollo.api.board.LaneResponse
import com.kmarzecki.trollo.api.board.UpdateBoardRequest
import java.security.Principal
import java.util.*

interface BoardService {
    fun create(request: CreateBoardRequest, principal: Principal): BoardResponse
    fun getBoards(principal: Principal): List<BoardResponse>
    fun deleteBoard(boardId: UUID, principal: Principal)
    fun update(request: UpdateBoardRequest, id: UUID, principal: Principal): BoardResponse
    fun getLanesForBoard(boardId: UUID, principal: Principal): List<LaneResponse>
}