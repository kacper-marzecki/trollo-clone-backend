package com.kmarzecki.communicator.model.auth;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


/**
 * Entity representing the user
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    /**
     * User id
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    /**
     * User username
     */
    private String username;
    /**
     * User password digest
     */
    private String password;

    private String email;

    private String avatar_id;
    /**
     * User roles
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
