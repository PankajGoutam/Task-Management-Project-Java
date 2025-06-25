package com.Lucifer.TaskSubmissionService;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepo extends JpaRepository<Submission, Long> {

	List<Submission> findByTaskId(Long taskId);
}
