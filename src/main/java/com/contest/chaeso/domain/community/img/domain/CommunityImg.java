package com.contest.chaeso.domain.community.img.domain;

import com.contest.chaeso.domain.community.community.domain.Community;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_img_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "co_id")
    private Community community;

    @Column(length = 512)
    private String coImgLink;
}
