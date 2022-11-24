package com.shubham.reporting.repositories;

import com.shubham.reporting.dataclasses.Grade;
import com.shubham.reporting.entities.Student;
import com.shubham.reporting.entities.Subject;
import com.shubham.reporting.entities.SubjectReport;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectReportRepository extends CrudRepository<SubjectReport, Long> {

  Optional<SubjectReport> findSubjectReportBySemesterAndStudentAndSubject(Integer semester, Student student, Subject subject);
  List<SubjectReport> findAllBySemesterAndGradeAndAcademicYear(Integer semester, Grade grade, Integer academicYear);

  List<SubjectReport> findAllByAndGradeAndAcademicYear(Grade grade, Integer academicYear);

  List<SubjectReport> findAllBySubjectAndGradeAndAcademicYear(Subject subject, Grade grade, Integer academicYear);
  @Query(value = "select max(semester) as recent from subject_reports", nativeQuery = true)
  Optional<Integer> findRecentSemester();
}
