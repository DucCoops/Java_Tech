package com.iiex.lab7_th_2;

import com.iiex.lab7_th_2.Model.Student;
import com.iiex.lab7_th_2.Repository.StudentPagingAndSorting;
import com.iiex.lab7_th_2.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class Lab7Th2Application implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;


    public static void main(String[] args) {
        SpringApplication.run(Lab7Th2Application.class, args);
    }

    @Override
    public  void run(String... args) throws Exception {


        // Add at least 3 students to the database
        Student student1 = new Student(null, "JOhn Doe", 21, "john.doe@example.com", 7.5);
        Student student2 = new Student(null, "Jane Smith", 23, "jane.smith@example.com", 8.0);
        Student student3 = new Student(null, "Bob Johnson", 20, "bob.johnson@example.com", 7.0);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        // Read the student list and print it to the console
        System.out.println("All students:");
        studentRepository.findAll().forEach(System.out::println);

        // Update any student's information and print out the results after updating
        Student studentToUpdate = studentRepository.findById(1L).orElseThrow();
        studentToUpdate.setName("John Smith");
        studentRepository.save(studentToUpdate);
        System.out.println("Updated student:");
        System.out.println(studentRepository.findById(1L).orElseThrow());

        // Delete a student from the database and print the result after deleting
        studentRepository.deleteById(2L);
        System.out.println("After deleting student:");
        studentRepository.findAll().forEach(System.out::println);

        System.out.println("Print student > 21t");
        studentRepository.findByAgeGreaterThanOrEqual(21).forEach(System.out::println);
        System.out.println("number of students whose ielts score is equal to 7.5:");
        System.out.println(studentRepository.countByIeltsScore(7.5));
        System.out.println("The list of students whose name contains the word: jo");
        studentRepository.findByNameContaining("Jo").forEach(System.out::println);


    }
}
