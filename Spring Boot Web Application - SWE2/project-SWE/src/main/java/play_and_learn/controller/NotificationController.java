package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import play_and_learn.service.UserService;

@Controller
public class NotificationController {
	@Autowired
	private UserService userService;	
	
	@RequestMapping("/notifications")
	public String openNotifications(Model model) {
		
		model.addAttribute(userService.getLoggedInUser());
		model.addAttribute("notifications", userService.findByUsername(userService.getLoggedInUser()).getNotifications());
		
		return "notifications";
	}
}
