package com.example.demo20.service;


import com.example.demo20.exception.StudentNotFoundException;
import com.example.demo20.model.Student;
import com.example.demo20.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        logger.debug("Calling constructor StudentService");
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.debug("Calling method createStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.debug("Calling method findStudent (studentId = {})", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new StudentNotFoundException (id);
        }
        return student;
    }

    public Student editStudent(Student student) {
        logger.debug("Calling method editStudent (studentId = {})", student.getId());
        if (studentRepository.findById(student.getId()).orElse(null) == null) {
            return null;
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.debug("Calling method deleteStudent (studentId = {})", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        logger.debug("Calling method getStudentsByAge (age = {})", age);
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.debug("Calling method findByAgeBetween (minAge = {}, maxAge = {})", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getStudentFaculty(long id) {
        logger.debug("Calling method getStudentFaculty (studentId = {})", id);
        Student student = findStudent(id);
        if (student == null) {
            return null;
        }
        return student.getFaculty();
    }

    public NumberOfStudents getAllStudentsNumber() {
        logger.debug("Calling method getAllStudentsNumber");
        return studentRepository.getAllStudentsNumber();
    }

    public AverageAgeOfStudents getAverageAge() {
        logger.debug("Calling method getAverageAge");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastStudentsById(int limit) {
        logger.debug("Calling method getLastStudentsById (limit = {})", limit);
        return studentRepository.getLastStudentsById(limit);
    }
}