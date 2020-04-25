package com.kmarzecki.communicator.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String description;
    private Integer positionInLane;
    @ManyToOne
    private LaneEntity lane;
    @OneToMany
    private List<CardTaskEntity> tasks;
    @OneToMany
    private List<FileEntity> files;
}
