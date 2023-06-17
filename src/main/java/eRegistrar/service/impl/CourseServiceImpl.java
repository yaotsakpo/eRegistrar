package eRegistrar.service.impl;

import eRegistrar.model.Course;
import eRegistrar.repository.CourseRepository;
import eRegistrar.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course Course) {
        return courseRepository.save(Course);
    }

    @Override
    public Course getCourseById(Integer CourseId) {
        return (courseRepository.findById(CourseId)).orElse(null);
    }

    @Override
    public void deleteCourseById(Integer CourseId) {
            courseRepository.deleteById(CourseId);
    }

    @Override
    public List<Course> searchCourses(String searchString) {
        return courseRepository.findByCourseNameContaining(searchString);
    }
}
