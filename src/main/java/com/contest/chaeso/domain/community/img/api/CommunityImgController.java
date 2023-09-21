package com.contest.chaeso.domain.community.img.api;

import com.contest.chaeso.domain.community.img.application.CommunityImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/community/img")
public class CommunityImgController {

    private final CommunityImgService communityImgService;

}
