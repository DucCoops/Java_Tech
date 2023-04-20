package com.iiex.lab7_th;

import com.iiex.lab7_th.Domain.Student;
import com.iiex.lab7_th.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab7ThApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Lab7ThApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Môn học công nghệ java");
        };
    }
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public  void run(String... args) throws Exception {
        // Add at least 3 students to the database
        Student student1 = new Student(null, "John Doe", 21, "john.doe@example.com", 7.5);
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
    }
}
