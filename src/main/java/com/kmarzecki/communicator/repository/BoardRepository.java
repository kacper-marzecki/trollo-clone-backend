package com.kmarzecki.communicator.repository;

import com.kmarzecki.communicator.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<BoardEntity, UUID> {
    boolean existsByNameAndUsers_Username(String name, String username);
    List<BoardEntity> findAllByUsers_Username(String username);
    void deleteAlLByIdAndUsers_Username(String id, String username);
}
