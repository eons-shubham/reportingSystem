package com.shubham.reporting.controllers;

import com.shubham.reporting.dataclasses.AdmissionRequest;
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
@RequestMapping("/admin")
public class AdminController {
  private StudentService studentService;

  @Autowired
  public AdminController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping("/admit")
  public ResponseEntity<?> addStudent(@RequestBody AdmissionRequest admissionRequest) {
    log.info("Admission Request for student {}", admissionRequest.getStudentName());
    return ResponseEntity.ok(studentService.admitStudent(admissionRequest));
  }
}
