package com.nicksy.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Getter
@Setter
public class UserPrincipal implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) //fetch with other details
    @JoinTable(name = "user_authority_join_table",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

}
