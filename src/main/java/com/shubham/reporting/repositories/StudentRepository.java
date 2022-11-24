package com.shubham.reporting.repositories;

import com.shubham.reporting.entities.Student;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
  Optional<Student> findByStudentId(final String studentId);
}
