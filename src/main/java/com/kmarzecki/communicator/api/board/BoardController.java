package com.kmarzecki.communicator.api.board;

import com.kmarzecki.communicator.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = {"*"},allowCredentials = "true", allowedHeaders = "*")
@AllArgsConstructor
public class BoardController {
    BoardService boardService;

    @PutMapping("/{id}")
    BoardResponse update(@RequestBody UpdateBoardRequest request, @PathVariable UUID id, Principal principal) {
        return boardService.update(request, id, principal);
    }

    @PostMapping
    BoardResponse create(@RequestBody  CreateBoardRequest request, Principal principal) {
        return boardService.create(request, principal);
    }

    @GetMapping
    List<BoardResponse> getBoards(Principal principal) {
        return boardService.getBoards(principal);
    }

    @DeleteMapping("/{id}")
    void deleteBoard(@PathVariable  UUID id, Principal principal) {
        boardService.deleteBoard(id, principal);
    }


}
