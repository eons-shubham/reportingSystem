package com.shubham.reporting.services;

import com.shubham.reporting.dataclasses.AdmissionRequest;
import com.shubham.reporting.dataclasses.Grade;
import com.shubham.reporting.dataclasses.UpdateReportRequest;
import com.shubham.reporting.entities.Student;
import com.shubham.reporting.entities.Subject;
import com.shubham.reporting.entities.SubjectReport;
import com.shubham.reporting.repositories.StudentRepository;
import com.shubham.reporting.repositories.SubjectReportRepository;
import com.shubham.reporting.repositories.SubjectRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private StudentRepository studentRepository;

  private SubjectRepository subjectRepository;

  private SubjectReportRepository subjectReportRepository;

  public Student admitStudent(AdmissionRequest admissionRequest) {
    final Student newStudent =
        Student.builder()
            .name(admissionRequest.getStudentName())
            .studentId(UUID.randomUUID().toString())
            .enrollmentYear(LocalDate.now().getYear())
            .grade(admissionRequest.getGrade())
            .build();

    return studentRepository.save(newStudent);
  }

  public SubjectReport updateStudentReport(UpdateReportRequest updateReportRequest) {
    final String studentId = updateReportRequest.getStudentId();
    final Student student = studentRepository.findByStudentId(studentId)
        .orElseThrow(() -> new RuntimeException("No student found with id " + studentId));

    final String subjectCode = updateReportRequest.getSubjectCode();
    final Subject subject = subjectRepository.findBySubjectCode(subjectCode)
        .orElseThrow(() -> new RuntimeException("No subject found with subjectCode " + subjectCode));

    final SubjectReport subjectReport = subjectReportRepository.findSubjectReportBySemesterAndStudentAndSubject(
            updateReportRequest.getSemester(),
            student,
            subject
        )
        .map(report -> {
          report.setMarks(updateReportRequest.getMarks());
          return report;
        })
        .orElse(SubjectReport.builder()
            .marks(updateReportRequest.getMarks())
            .semester(updateReportRequest.getSemester())
            .student(student)
            .subject(subject)
            .build());

    return subjectReportRepository.save(subjectReport);
  }

  public Double getAvgClassPerformanceInRecentSem(Grade grade, Integer academicYear) {
    final Integer recentSemester = subjectReportRepository.findRecentSemester()
        .orElseThrow(() -> new RuntimeException("No student report in database found"));

    final List<SubjectReport> reports =
        subjectReportRepository.findAllBySemesterAndGradeAndAcademicYear(recentSemester, grade, academicYear);

    Integer scored = 0;
    Integer total = 0;
    for (var report : reports) {
      scored += report.getMarks();
      total += report.getTotalMarks();
    }

    return scored * 0.1D / total * 100.0;
  }

  public Double getAvgSubjectMarks(String subjectCode, Grade grade, Integer academicYear) {
    final Subject subject = subjectRepository.findBySubjectCode(subjectCode)
        .orElseThrow(() -> new RuntimeException("No subject found with subjectCode " + subjectCode));

    final List<SubjectReport> reports =
        subjectReportRepository.findAllBySubjectAndGradeAndAcademicYear(subject, grade, academicYear);

    Integer scored = 0;
    for (var report : reports) {
      scored += report.getMarks();
    }

    return scored * 0.1D / reports.size();
  }

  public List<String> getToppers(Grade grade, Integer academicYear) {

    final List<SubjectReport> reports =
        subjectReportRepository.findAllByGradeAndAcademicYear(grade, academicYear);



    Map<String, Double> result = new TreeMap<>();

    for (var report : reports) {
      final String student = report.getStudent().getStudentId();
      final Double current = result.getOrDefault(student, 0.0D);

      result.put(student, current + report.getMarks());
    }

    List<Pair> casted = new ArrayList<>();
    for (var entry : result.entrySet()) {
      casted.add(new Pair(entry.getKey(), entry.getValue()));
    }

    casted.sort(Comparator.comparingDouble(e -> e.second));


    final ArrayList<String> ans = new ArrayList<>();

    if (casted.size() > 0) {
      ans.add(casted.get(casted.size() - 1).first);
    }
    if (casted.size() > 1) {
      ans.add(casted.get(casted.size() - 2).first);
    }

    return ans;
  }

  private static class Pair {
    public String first;
    public Double second;

    public Pair(String first, Double second) {
      this.first = first;
      this.second = second;
    }
  }

}
