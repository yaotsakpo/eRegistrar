package eRegistrar.service;

import eRegistrar.model.Student;

import java.util.List;

public interface StudentService {

    public abstract List<Student> getAllStudents();
    public abstract Student saveStudent(Student Student);
    public abstract Student getStudentById(Integer StudentId);
    public abstract void deleteStudentById(Integer StudentId);
    public abstract List<Student> searchStudents(String searchString);
}
