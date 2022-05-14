package com.example.botheroku.repository;

import com.example.botheroku.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report,Long> {
//    findByCreatedAtBetween
    List<Report> findByDateBetween(Timestamp date, Timestamp date2);

    List<Report> findBySupervisorIdAndDateBetween(Long supervisor_id, Timestamp date, Timestamp date2);

    Optional<Report> findByMessageId(@NotNull String messageId);


}
