package com.contest.chaeso.domain.users.vegan.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VeganInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vegan_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VeganLevel level;
}
