package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private FileSystemView studentOptional;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public  List<Student> getStudents() {

        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){

            throw  new IllegalArgumentException("email taken");

        }

        studentRepository.save(student);

    }


    public void deleteStudent(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);

        if(!exists){

            throw new IllegalArgumentException("student whith id" + studentId +" does not exists");

        }

        studentRepository.deleteById(studentId);


    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student With id " +studentId+
                        " does not exist"));
                if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){

                     student.setName(name);

                }
                if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){

                    student.setEmail(email);

                }else{

            throw  new IllegalArgumentException("email taken");
        }
        student.setEmail(email);
        
       


    }
}
