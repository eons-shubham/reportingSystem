package com.shubham.reporting.controllers;

import com.shubham.reporting.dataclasses.Grade;
import com.shubham.reporting.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportsController {
  private StudentService studentService;

  @Autowired
  public ReportsController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/average-batch-performance")
  public ResponseEntity<?> getPercentageOfClassInRecentSemester(@RequestParam Grade grade,
                                                                       @RequestParam Integer academicYear) {
    log.info("Report on avg. percentage of class in recent sem requests for  grade {}", grade);
    return ResponseEntity.ok(studentService.getAvgClassPerformanceInRecentSem(grade, academicYear));
  }

  @GetMapping("/average-batch-subject-marks")
  public ResponseEntity<?> getAverageSubjectMarks(@RequestParam String subjectCode,
                                                   @RequestParam Grade grade,
                                                   @RequestParam Integer academicYear) {
    log.info("Report on avg. subject marks of class {}", grade);
    return ResponseEntity.ok(studentService.getAvgSubjectMarks(subjectCode, grade, academicYear));
  }

  @GetMapping("/toppers")
  public ResponseEntity<?> getToppers(@RequestParam Grade grade,
                                                  @RequestParam Integer academicYear) {
    log.info("Report on avg. subject marks of class {}", grade);
    return ResponseEntity.ok(studentService.getToppers(grade, academicYear));
  }
}
