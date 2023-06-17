package eRegistrar.controller;

import eRegistrar.model.Student;
import eRegistrar.service.StudentService;
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
public class StudentController {

    @Autowired
    private StudentService StudentService;

    @GetMapping(value = {"/eRegistrar/Student/list","/Student/list"})
    public ModelAndView listStudents() {
        ModelAndView modelAndView = new ModelAndView();
        List<Student> Students = StudentService.getAllStudents();
        modelAndView.addObject("Students", Students);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("StudentsCount", Students.size());
        modelAndView.addObject("Student", new Student());
        modelAndView.setViewName("Student/studentList");
        return modelAndView;
    }

    @GetMapping(value = {"/eRegistrar/Student/new","/Student/new"})
    public String displayNewStudentForm(Model model) {
        model.addAttribute("Student", new Student());
        return "Student/new";
    }

    @PostMapping(value = {"/eRegistrar/Student/new","/Student/new"})
    public String addNewStudent(@Valid @ModelAttribute("Student") Student Student,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Student/new";
        }

        Student = StudentService.saveStudent(Student);

        return "redirect:/eRegistrar/Student/list";
    }

    @GetMapping(value = {"/eRegistrar/Student/edit/{StudentId}","/Student/edit/{StudentId}"})
    public ResponseEntity<Student> editStudent(@PathVariable Integer StudentId) {
        return ResponseEntity.ok().body(StudentService.getStudentById(StudentId));
    }

    @PostMapping(value = {"/eRegistrar/Student/edit","/Student/edit"})
    public String updateStudent(@Valid @ModelAttribute("Student") Student Student,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Student/edit";
        }
        Student = StudentService.saveStudent(Student);
        return "redirect:/eRegistrar/Student/list";
    }

    @GetMapping(value = {"/eRegistrar/Student/delete/{StudentId}","/Student/delete/{StudentId}"})
    public String deleteStudent(@PathVariable Integer StudentId, Model model) {
        StudentService.deleteStudentById(StudentId);
        return "redirect:/eRegistrar/Student/list";
    }

    @GetMapping(value = {"/eRegistrar/Student/search", "/Student/search"})
    public ModelAndView searchStudents(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Student> Students = StudentService.searchStudents(searchString);
        modelAndView.addObject("Students", Students);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("StudentsCount", Students.size());
        modelAndView.setViewName("Student/studentList");
        return modelAndView;
    }

    @GetMapping(value = {"/eRegistrar/Student/search/{searchString}", "/Student/search/{searchString}"})
    public ResponseEntity<?> searchStudentsUsingAjax(@PathVariable String searchString) {
        List<Student> Students = StudentService.searchStudents(searchString);
        return ResponseEntity.ok().body(Students);
    }
}
