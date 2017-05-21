package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import play_and_learn.model.User;
import play_and_learn.service.AuthenticationService;
import play_and_learn.service.UserService;

@Controller
public class LoginController {
	@Autowired
    private AuthenticationService authService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String openLogin(Model model) {
		model.addAttribute("user", new User());
		return "login";	
	}
	
	@PostMapping("/login")
	public String openWelcome(@ModelAttribute("user") User user, Model model) {
		
		if (authService.verifyUser(user.getUsername(), user.getPassword())) {
			
			userService.setActiveUsername(user.getUsername());
			
			
			return "redirect:/welcome";  // redirect to user page
		}
		else {
			model.addAttribute("error", "Username or Password is Incorrect");
			return "login";
		}
	}

}
