package com.Lucifer.TaskSubmissionService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionServiceImpementation implements SubmissionService{
	
	@Autowired
	private SubmissionRepo submissionRepo;
	
	@Autowired
	private TaskService taskService;
	
//	@Autowired
//	private UserService userService;
	
	@Override
	public Submission submitTask(Long taskId, String githubLink, Long userId,String jwt) throws Exception {
		Task task = taskService.getTaskById(taskId, jwt);
		if (task != null) {
			Submission submission = new Submission();
			submission.setTaskId(taskId);
			submission.setUserId(userId);
			submission.setGithubLink(githubLink);
			submission.setSubmissionTime(LocalDateTime.now());
			
			return submissionRepo.save(submission); 
		}
		throw new Exception("Task not found with id "+taskId);
	}

	@Override
	public Submission getTaskSubmissionById(Long submissionId) throws Exception {
		return submissionRepo.findById(submissionId).orElseThrow(()-> new Exception("Task Submission not found with id :"+submissionId));
	}

	@Override
	public List<Submission> getAllTaskSubmissions() {
		return submissionRepo.findAll();
	}

	@Override
	public List<Submission> getAllTaskSubmissionsByTaskId(Long taskId) {
		return submissionRepo.findByTaskId(taskId);
	}

	@Override
	public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
		Submission submission = getTaskSubmissionById(id);
		submission.setStatus(status);
		if (status.equals("ACCEPT")) {
			taskService.completeTask(submission.getTaskId());
		}
		return submissionRepo.save(submission);
	}

}
