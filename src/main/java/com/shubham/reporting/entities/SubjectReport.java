package com.shubham.reporting.entities;

import com.shubham.reporting.dataclasses.Grade;
import com.shubham.reporting.dataclasses.Semester;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subject_reports", uniqueConstraints=@UniqueConstraint(columnNames= {"student_id","subject_id"}))
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class SubjectReport extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @OneToOne
  @JoinColumn(name = "subject_id")
  private Subject subject;

  private Integer marks;

  private Integer totalMarks;

  private Integer semester;

  @Enumerated(EnumType.STRING)
  @Column
  private Grade grade;

  private Integer academicYear;
}
