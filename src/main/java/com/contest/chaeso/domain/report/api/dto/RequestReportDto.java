package com.contest.chaeso.domain.report.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestReportDto {

    private String postType;
    private Long reportPostId;

}
