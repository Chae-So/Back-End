package com.contest.chaeso.domain.users.vegan.api;


import com.contest.chaeso.domain.users.vegan.application.VeganInfoService;
import com.contest.chaeso.domain.users.vegan.domain.VeganLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/vegan")
public class VeganInfoController {

    private final VeganInfoService veganInfoService;

    /**
     * 비건 단계 리스트 출력
     * @return Map<Long, String>
     */
    @GetMapping
    public Map<Long, String> veganInfo() {
        return veganInfoService.veganInfoList();
    }
}
