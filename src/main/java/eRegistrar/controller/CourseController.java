package eRegistrar.controller;

import eRegistrar.model.Course;
import eRegistrar.model.Student;
import eRegistrar.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService CourseService;

    @GetMapping(value = {"/eRegistrar/Course/list","/Course/list"})
    public ModelAndView listCourses() {
        ModelAndView modelAndView = new ModelAndView();
        List<Course> Courses = CourseService.getAllCourses();
        modelAndView.addObject("Courses", Courses);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("CoursesCount", Courses.size());
        modelAndView.addObject("Course", new Course());
        modelAndView.setViewName("Course/courselist");
        return modelAndView;
    }

    @GetMapping(value = {"/eRegistrar/Course/new","/Course/new"})
    public String displayNewCourseForm(Model model) {
        model.addAttribute("Course", new Course());
        return "Course/new";
    }

    @PostMapping(value = {"/eRegistrar/Course/new","/Course/new"})
    public String addNewCourse(@Valid @ModelAttribute("Course") Course Course,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Course/new";
        }
        Course = CourseService.saveCourse(Course);
        return "redirect:/eRegistrar/Course/list";
    }

    @GetMapping(value = {"/eRegistrar/Course/edit/{CourseId}","/Course/edit/{CourseId}"})
    public ResponseEntity<Course> editCourse(@PathVariable Integer CourseId, Model model) {
        return ResponseEntity.ok().body(CourseService.getCourseById(CourseId));
    }

    @PostMapping(value = {"/eRegistrar/Course/edit","/Course/edit"})
    public String updateCourse(@Valid @ModelAttribute("Course") Course Course,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Course/edit";
        }
        Course = CourseService.saveCourse(Course);
        return "redirect:/eRegistrar/Course/list";
    }

    @GetMapping(value = {"/eRegistrar/Course/delete/{CourseId}","/Course/delete/{CourseId}"})
    public String deleteCourse(@PathVariable Integer CourseId, Model model) {
        CourseService.deleteCourseById(CourseId);
        return "redirect:/eRegistrar/Course/list";
    }

    @GetMapping(value = {"/eRegistrar/Course/search", "/Course/search"})
    public ModelAndView searchCourses(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Course> Courses = CourseService.searchCourses(searchString);
        modelAndView.addObject("Courses", Courses);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("CoursesCount", Courses.size());
        modelAndView.setViewName("Course/courselist");
        return modelAndView;
    }

    @GetMapping(value = {"/eRegistrar/Course/search/{searchString}", "/Student/Course/{searchString}"})
    public ResponseEntity<?> searchCoursesUsingAjax(@PathVariable String searchString) {
        List<Course> courses = CourseService.searchCourses(searchString);
        return ResponseEntity.ok().body(courses);
    }
}
