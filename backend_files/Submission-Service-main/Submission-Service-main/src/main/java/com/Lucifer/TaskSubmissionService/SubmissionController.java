package com.Lucifer.TaskSubmissionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/submission")
public class SubmissionController {
	
	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@CrossOrigin
	@PostMapping()
	public ResponseEntity<Submission> submitTask(@RequestParam Long taskid,@RequestParam String githubLink,@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.getUserProfile(jwt);
		Submission submission = submissionService.submitTask(taskid, githubLink,user.getId(), jwt);
		return new ResponseEntity<>(submission,HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.getUserProfile(jwt);
		Submission submission = submissionService.getTaskSubmissionById(id);
		return new ResponseEntity<>(submission,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping()
	public ResponseEntity<List<Submission>> getAllSubmissions(@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.getUserProfile(jwt);
		List<Submission> submissions = submissionService.getAllTaskSubmissions();
		return new ResponseEntity<>(submissions,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/task/{taskId}")
	public ResponseEntity<List<Submission>> getAllSubmissionsByTaskId(@PathVariable Long taskId,@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.getUserProfile(jwt);
		List<Submission> submissions = submissionService.getAllTaskSubmissionsByTaskId(taskId);
		return new ResponseEntity<>(submissions,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity <Submission> acceptOrDeclineSubmission(@PathVariable Long id,@RequestParam("status") String status ,@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.getUserProfile(jwt);
		Submission submission = submissionService.acceptDeclineSubmission(id, status);
		return new ResponseEntity<>(submission,HttpStatus.OK);
	}
}
