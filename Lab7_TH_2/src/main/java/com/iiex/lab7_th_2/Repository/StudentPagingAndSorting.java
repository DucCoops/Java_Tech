package com.iiex.lab7_th_2.Repository;


import java.util.List;

import com.iiex.lab7_th_2.Model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;


@NoRepositoryBean
public interface StudentPagingAndSorting extends PagingAndSortingRepository<Student, Long> {

    // Example implementation of sorting by age in descending order, and then by ielts score in ascending order
    @Override
    List<Student> findAll(Sort sort);

    // Example implementation of paging functionality to read students 4-5-6
    @Override
    Page<Student> findAll(Pageable pageable);
}
