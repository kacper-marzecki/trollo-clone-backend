package com.kmarzecki.trollo.api.board

import com.kmarzecki.trollo.service.BoardService
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = ["*"], allowCredentials = "true", allowedHeaders = ["*"])
class BoardController(
        val boardService: BoardService
) {

    @PutMapping("/{id}")
    fun update(@RequestBody request: UpdateBoardRequest, @PathVariable id: UUID, principal: Principal): BoardResponse? {
        return boardService.update(request, id, principal)
    }

    @PostMapping
    fun create(@RequestBody request: CreateBoardRequest, principal: Principal): BoardResponse? {
        return boardService.create(request, principal)
    }

    @GetMapping
    fun getBoards(principal: Principal): List<BoardResponse> {
        return boardService.getBoards(principal)
    }

    @GetMapping("/{id}/lanes")
    fun getBoard(@PathVariable id: UUID, principal: Principal): List<LaneResponse> {
        return boardService.getLanesForBoard(id, principal)
    }


    @DeleteMapping("/{id}")
    fun deleteBoard(@PathVariable id: UUID, principal: Principal) {
        boardService.deleteBoard(id, principal)
    }
}