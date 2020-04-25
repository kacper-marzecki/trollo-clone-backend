package com.kmarzecki.communicator.service;

import com.kmarzecki.communicator.api.board.BoardResponse;
import com.kmarzecki.communicator.api.board.CreateBoardRequest;
import com.kmarzecki.communicator.api.board.UpdateBoardRequest;
import com.kmarzecki.communicator.exception.OperationNotPermittedException;
import com.kmarzecki.communicator.model.BoardEntity;
import com.kmarzecki.communicator.model.auth.UserEntity;
import com.kmarzecki.communicator.repository.BoardRepository;
import com.kmarzecki.communicator.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private BoardRepository repository;
    private UserRepository userRepository;
    @Override
    @Transactional
    public BoardResponse create(CreateBoardRequest request, Principal principal) {
        if(repository.existsByNameAndUsers_Username(request.getName(), principal.getName())) {
            throw new OperationNotPermittedException("Already exists");
        }
        UserEntity user = userRepository.findByUsername(principal.getName());
        List<UserEntity> users = new ArrayList<>();
        users.add(user);
        BoardEntity saved = repository.save(new BoardEntity(null, request.getName(), users ,new ArrayList<>()));
        return BoardResponse.builder()
                .boardId(saved.getId())
                .name(saved.getName())
                .build();
    }

    private BoardResponse map(BoardEntity entity) {
        return BoardResponse.builder()
                .boardId(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<BoardResponse> getBoards(Principal principal) {
        List<BoardEntity> boards  = repository.findAllByUsers_Username(principal.getName());
        return boards.stream().map(this::map).collect(Collectors.toList());

    }

    @Override
    public void deleteBoard(UUID boardId, Principal principal) {
        repository.deleteAlLByIdAndUsers_Username(boardId.toString(), principal.getName());
    }

    @Override
    public BoardResponse update(UpdateBoardRequest request, UUID id, Principal principal) {
        return null;
    }
}
