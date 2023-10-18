package com.contest.chaeso.domain.report.application;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import com.contest.chaeso.domain.community.review.review.domain.repository.CommunityReviewRepository;
import com.contest.chaeso.domain.report.Report;
import com.contest.chaeso.domain.report.api.dto.RequestReportDto;
import com.contest.chaeso.domain.report.repository.ReportRepository;
import com.contest.chaeso.global.exception.CustomException;
import com.contest.chaeso.global.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.contest.chaeso.domain.report.Report.reportFunction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class ReportServiceTest {

    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private CommunityReviewRepository communityReviewRepository;
    @Autowired
    private ReportRepository reportRepository;

    @Test
    @DisplayName("게시글 신고 테스트")
    void report() {
        RequestReportDto requestReportDto = new RequestReportDto("C", 714L);
        if (requestReportDto.getPostType().equals("C")) {
            Community beReported = communityRepository.findById(requestReportDto.getReportPostId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_POSTS));
            Report report = reportRepository.save(reportFunction(beReported.getUsers(), beReported, null));
            assertThat(report).isNotNull();
        } else if (requestReportDto.getPostType().equals("R")) {
            CommunityReview beReported = communityReviewRepository.findById(requestReportDto.getReportPostId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_REVIEW));
            Report report = reportRepository.save(reportFunction(beReported.getUsers(), null, beReported));
            assertThat(report).isNull();
        } else {
            throw new RuntimeException();
        }

    }

}