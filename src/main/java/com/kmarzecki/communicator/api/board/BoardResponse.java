package com.kmarzecki.communicator.api.board;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardResponse {
    UUID boardId;
    String name;
}
