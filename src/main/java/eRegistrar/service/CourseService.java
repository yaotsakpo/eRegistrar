package eRegistrar.service;

import eRegistrar.model.Course;

import java.util.List;

public interface CourseService {

    public abstract List<Course> getAllCourses();
    public abstract Course saveCourse(Course Course);
    public abstract Course getCourseById(Integer CourseId);
    public abstract void deleteCourseById(Integer CourseId);
    public abstract List<Course> searchCourses(String searchString);
}
