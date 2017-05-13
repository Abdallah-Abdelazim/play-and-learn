package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import play_and_learn.model.User;
import play_and_learn.service.CourseService;
import play_and_learn.service.UserService;
import play_and_learn.validator.UserValidator;

@Controller
public class SignupController {
	
	@Autowired
    private UserValidator userValidator;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private CourseService courseService;

	
	@GetMapping("/signup")
	public String openSignup(Model model) {		
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping(value = "/signup")
    public String regisetration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		
		userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
        	model.addAttribute("error", "Data Validation Failed. Pleaes Enter Valid Data."
        			+ " Either the username is duplicate or you entered data less than the specified size.");
            return "signup";
        }

        userService.save(user);
		
        userService.setActiveUsername(user.getUsername());
		
		model.addAttribute("courses", courseService.getAllCourses());
        
        return "welcome";
    }
	
	
}
