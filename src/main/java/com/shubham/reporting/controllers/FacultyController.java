package com.shubham.reporting.controllers;

import com.shubham.reporting.dataclasses.UpdateReportRequest;
import com.shubham.reporting.services.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/faculty")
public class FacultyController {
  private StudentService studentService;

  @Autowired
  public FacultyController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping("update-report")
  public ResponseEntity<?> addStudentReport(@RequestBody UpdateReportRequest updateReportRequest) {
    log.info("Report update request for student {}", updateReportRequest.getStudentId());
    return ResponseEntity.ok(studentService.updateStudentReport(updateReportRequest));
  }

}
