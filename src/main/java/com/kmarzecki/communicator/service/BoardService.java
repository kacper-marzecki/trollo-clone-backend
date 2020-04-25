package com.kmarzecki.communicator.service;

import com.kmarzecki.communicator.api.board.BoardResponse;
import com.kmarzecki.communicator.api.board.CreateBoardRequest;
import com.kmarzecki.communicator.api.board.UpdateBoardRequest;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface BoardService {
    BoardResponse create(CreateBoardRequest request, Principal principal);
    List<BoardResponse> getBoards(Principal principal);
    void deleteBoard(UUID boardId, Principal principal);
    BoardResponse update(UpdateBoardRequest request, UUID id, Principal principal);
}
