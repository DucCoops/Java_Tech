package com.iiex.lab7_th.Repository;

import com.iiex.lab7_th.Domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}