package com.shubham.reporting.repositories;

import com.shubham.reporting.entities.Subject;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
  Optional<Subject> findBySubjectCode(String subjectCode);
}
