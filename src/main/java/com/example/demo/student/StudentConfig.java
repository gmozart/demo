package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {



    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){

        return args -> {

                    Student mariam = new Student(
                    "marta",
                    "mar.ta@gmail.com",
                    LocalDate.of(1968,Month.JANUARY,5)

                    );

                    Student alex = new Student(
                    "teste",
                    "te.este@gmail.com",
                    LocalDate.of(1991,Month.FEBRUARY,27)

            );

            repository.saveAll(
                    List.of(mariam,alex)
            );

        };



    }


}
