package com.contest.chaeso.domain.report.repository;

import com.contest.chaeso.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
