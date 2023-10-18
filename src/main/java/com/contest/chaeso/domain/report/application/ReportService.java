package com.contest.chaeso.domain.report.application;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import com.contest.chaeso.domain.community.review.review.domain.repository.CommunityReviewRepository;
import com.contest.chaeso.domain.report.api.dto.RequestReportDto;
import com.contest.chaeso.domain.report.repository.ReportRepository;
import com.contest.chaeso.global.exception.CustomException;
import com.contest.chaeso.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.contest.chaeso.domain.report.Report.reportFunction;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final CommunityRepository communityRepository;
    private final CommunityReviewRepository communityReviewRepository;
    private final ReportRepository reportRepository;

    @Transactional
    public void report(RequestReportDto requestReportDto) {
        if (requestReportDto.getPostType().equals("C")) {
            Community beReported = communityRepository.findById(requestReportDto.getReportPostId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_POSTS));
            reportRepository.save(reportFunction(beReported.getUsers(), beReported, null));
        } else if (requestReportDto.getPostType().equals("R")) {
            CommunityReview beReported = communityReviewRepository.findById(requestReportDto.getReportPostId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_REVIEW));
            reportRepository.save(reportFunction(beReported.getUsers(), null, beReported));
        } else {
            throw new RuntimeException();
        }
    }
}
