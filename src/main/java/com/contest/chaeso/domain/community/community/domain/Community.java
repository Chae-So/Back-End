package com.contest.chaeso.domain.community.community.domain;

import com.contest.chaeso.domain.common.BaseTimeEntity;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_id")
    private Long coId;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Users> users = new ArrayList<>();

    @NotNull
    @Column(length = 100)
    private String title;

    @NotNull
    @Column(length = 30)
    private String writer;

    @NotNull
    @Column(columnDefinition = "text")
    private String text;


}
