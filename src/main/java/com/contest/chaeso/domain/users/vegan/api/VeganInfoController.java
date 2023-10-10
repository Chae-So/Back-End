package com.contest.chaeso.domain.users.vegan.api;


import com.contest.chaeso.domain.users.vegan.api.dto.res.ResponseVeganInfoListDto;
import com.contest.chaeso.domain.users.vegan.application.VeganInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "VeganController")
@RequestMapping("/api/vegan")
public class VeganInfoController {

    private final VeganInfoService veganInfoService;

    /**
     * 비건 단계 리스트 출력
     *
     * @return List<ResponseVeganInfoListDto>
     */
    @ApiOperation(value = "비건 단계 정보 조회", notes = "비건 단계에 대한 정보를 출력합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @GetMapping
    public List<ResponseVeganInfoListDto> veganInfo() {
        return veganInfoService.veganInfoList();
    }
}
