package com.nicksy.security.entity;

import com.nicksy.security.util.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authorities")
@Getter
@Setter
@Builder
public class Authority {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum authority;
}
