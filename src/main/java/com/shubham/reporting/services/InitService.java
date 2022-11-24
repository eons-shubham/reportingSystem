package com.shubham.reporting.services;

import com.shubham.reporting.entities.Subject;
import com.shubham.reporting.repositories.SubjectRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitService {
  @Autowired private final SubjectRepository subjectRepository;

  @PostConstruct
  public void init() {


    final Subject english = new Subject("ENG01", "English", "English for middle schoolers",  5);
    final Subject maths = new Subject("MAT01", "Maths", "Maths for middle schoolers",  5);
    final Subject science = new Subject("SCI01", "Science", "Science for middle schoolers",  5);

    subjectRepository.saveAll(List.of(english, maths, science));
  }
}
