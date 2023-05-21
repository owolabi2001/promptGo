package com.tem.TransportApp.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_token")
public class Token {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    public AppUser  user;
}
