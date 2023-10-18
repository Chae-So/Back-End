package com.contest.chaeso.domain.report.api;

import com.contest.chaeso.domain.report.api.dto.RequestReportDto;
import com.contest.chaeso.domain.report.application.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
@Api(value = "ReportController")
public class ReportController {

    private final ReportService reportService;

    @ApiOperation(value = "게시글 신고", notes = "커뮤니티 게시글, 댓글을 신고합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 500, message = "fail")
    })
    @PostMapping
    public ResponseEntity getReport(@RequestBody RequestReportDto requestReportDto) {
        reportService.report(requestReportDto);
        return ResponseEntity.ok().build();
    }
}
