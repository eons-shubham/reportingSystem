package com.shubham.reporting.entities;


import com.shubham.reporting.dataclasses.Grade;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = {"studentId"})})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Student extends BaseEntity {
  private String name;
  private String studentId;
  private Integer enrollmentYear;

  @Enumerated(EnumType.STRING)
  @Column
  private Grade grade;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
  private Set<SubjectReport> reports;
}
