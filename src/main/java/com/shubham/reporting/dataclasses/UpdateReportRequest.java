package com.shubham.reporting.dataclasses;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateReportRequest {
  private String studentId;
  private String subjectCode;
  private Integer marks;
  private Integer semester;
}
