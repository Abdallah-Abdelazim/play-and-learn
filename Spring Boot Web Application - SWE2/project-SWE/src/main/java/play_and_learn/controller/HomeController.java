package play_and_learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import play_and_learn.ProjectSweApplication;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"/", "/index"})
	public String openHome(Model model) {  // model allows to add attributes for the view
		
//		System.out.println(ProjectSweApplication.activeUsername);
		
		// implement logout
		ProjectSweApplication.activePassword = null; // indicates no logged in user
		ProjectSweApplication.activeUsername = null; // indicates no logged in user
		
		model.addAttribute("pageTitle", "Play & Learn"); // change the page title
		model.addAttribute("studentsNumber", "1050");
		model.addAttribute("TeachersNumber", "2212");
		model.addAttribute("coursesNumber", "123");
		model.addAttribute("developmentContributor", "3");
		return "index";	
	}
	
	@RequestMapping("/about")
	public String openAbout() {
		return "about";	
	}
}
