package eRegistrar.controller;

import eRegistrar.model.Block;
import eRegistrar.model.Course;
import eRegistrar.model.Registration;
import eRegistrar.model.Student;
import eRegistrar.service.BlockService;
import eRegistrar.service.CourseService;
import eRegistrar.service.RegistrationService;
import eRegistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService RegistrationService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private BlockService blockService;

    @GetMapping(value = {"/eRegistrar/Registration/list","/Registration/list"})
    public ModelAndView listRegistrations() {
        ModelAndView modelAndView = new ModelAndView();
        List<Registration> Registrations = RegistrationService.getAllRegistrations();
        modelAndView.addObject("Registrations", Registrations);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("RegistrationsCount", Registrations.size());
        modelAndView.setViewName("Registration/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eRegistrar/Registration/new","/Registration/new"})
    public String displayNewRegistrationForm(Model model) {
        List<Student> studentList = studentService.getAllStudents();
        List<Course> courseList = courseService.getAllCourses();
        List<Block> blockList = blockService.getAllBlocks();
        model.addAttribute("Students", studentList);
        model.addAttribute("courses", courseList);
        model.addAttribute("blocks", blockList);
        model.addAttribute("Registration", new Registration());
        return "Registration/new";
    }

    @PostMapping(value = {"/eRegistrar/Registration/new","/Registration/new"})
    public String addNewRegistration(@Valid @ModelAttribute("Registration") Registration Registration,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Registration/new";
        }
        Registration = RegistrationService.saveRegistration(Registration);
        return "redirect:/eRegistrar/Registration/list";
    }

    @GetMapping(value = {"/eRegistrar/Registration/edit/{RegistrationId}","/Registration/edit/{RegistrationId}"})
    public String editRegistration(@PathVariable Integer RegistrationId, Model model) {
        Registration Registration = RegistrationService.getRegistrationById(RegistrationId);
        if (Registration != null) {
            model.addAttribute("Registration", Registration);
            return "Registration/edit";
        } else {
            // TODO
        }
        return "Registration/list";
    }

    @PostMapping(value = {"/eRegistrar/Registration/edit","/Registration/edit"})
    public String updateRegistration(@Valid @ModelAttribute("Registration") Registration Registration,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Registration/edit";
        }
        Registration = RegistrationService.saveRegistration(Registration);
        return "redirect:/eRegistrar/Registration/list";
    }

    @GetMapping(value = {"/eRegistrar/Registration/delete/{RegistrationId}","/Registration/delete/{RegistrationId}"})
    public String deleteRegistration(@PathVariable Integer RegistrationId, Model model) {
        RegistrationService.deleteRegistrationById(RegistrationId);
        return "redirect:/eRegistrar/Registration/list";
    }

    @GetMapping(value = {"/eRegistrar/Registration/search", "/Registration/search"})
    public ModelAndView searchRegistrations(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Registration> Registrations = RegistrationService.searchRegistrations(searchString);
        modelAndView.addObject("Registrations", Registrations);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("RegistrationsCount", Registrations.size());
        modelAndView.setViewName("Registration/list");
        return modelAndView;
    }
}
