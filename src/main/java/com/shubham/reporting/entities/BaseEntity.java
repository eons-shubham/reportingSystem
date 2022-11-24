package com.shubham.reporting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseEntity {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;

  @CreationTimestamp
  @JsonIgnore
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @JsonIgnore
  private LocalDateTime updatedAt;

  @Version
  @JsonIgnore
  private Integer version;
}