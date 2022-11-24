package com.shubham.reporting.dataclasses;

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
public class AdmissionRequest {
  private String studentName;
  private String phoneNumber;
  private Grade grade;
}
