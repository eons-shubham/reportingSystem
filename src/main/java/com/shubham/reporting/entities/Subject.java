package com.shubham.reporting.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subjects",  uniqueConstraints= @UniqueConstraint(columnNames= {"subjectCode"}))
@Getter
@Builder
@NoArgsConstructor
@Setter
public class Subject extends BaseEntity {
  private String subjectCode;
  private String subjectName;
  private String description;
  private Integer credits;

  public Subject(String subjectCode, String subjectName, String description, Integer credits) {
    this.subjectCode = subjectCode;
    this.subjectName = subjectName;
    this.description = description;
    this.credits = credits;
  }
}
