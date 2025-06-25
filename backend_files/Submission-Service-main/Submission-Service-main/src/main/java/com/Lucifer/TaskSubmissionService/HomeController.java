package com.Lucifer.TaskSubmissionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@CrossOrigin
    @GetMapping("/submission")
    public ResponseEntity<String> HomeController(){
        return new ResponseEntity<>("Welcome to task submission service", HttpStatus.OK);
    }
}
