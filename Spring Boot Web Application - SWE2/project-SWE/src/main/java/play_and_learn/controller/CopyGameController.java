package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import play_and_learn.model.ChosenGameIDRecord;
import play_and_learn.model.Course;
import play_and_learn.model.Game;
import play_and_learn.model.Notification;
import play_and_learn.model.User;
import play_and_learn.model.UsernameRecord;
import play_and_learn.service.CourseService;
import play_and_learn.service.GameService;
import play_and_learn.service.UserService;

@Controller
public class CopyGameController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private GameService gameService;
	
	@GetMapping(value = "/copygame")
	public String openCreateGameForm(@RequestParam(value="courseID", required=true) int courseID
			, Model model) {
		
		// check if there is no logged in user & the user must be a teacher
		if (userService.getLoggedInUser() ==null) {
			model.addAttribute("error", "Please create a teacher account then log in using it.");
			return "unauthorized-access";
		}	
		else if (!(userService.findByUsername(userService.getLoggedInUser()).getRole().equals("teacher"))) {
			model.addAttribute("error", "You must be a teacher to enter this page.");
			return "unauthorized-access";
		}
		
		model.addAttribute("courseID", courseID);
		model.addAttribute("games", gameService.getAllGames());
		model.addAttribute("chosenGameID", new ChosenGameIDRecord());
		return "copygame";
	}
	
	@PostMapping(value = "/copygame")
	public String createGame(@RequestParam(value="courseID", required=true) int courseID, 
			@ModelAttribute("chosenGameID") ChosenGameIDRecord chosenGameID, Model model) {
		
		Course course = courseService.findByID(courseID);
		Game game = gameService.findByID(Integer.parseInt(chosenGameID.id));
		
		Game copiedGame = new Game(game.getName(), game.getDescription()
				, game.getCreatorTeacherUsername(), game.getGameType(), game.getNumOfQuestions());
		copiedGame.setNumOfQuestions(game.getNumOfQuestions());
		copiedGame.setCreatorTeacherUsername(game.getCreatorTeacherUsername());
		
		copiedGame.setCourse(course);
		
		course.addGame(copiedGame);
		
		courseService.saveCourse(course);
		
		course = courseService.findByID(courseID);
		
		/* notify users in the notification list with this new game */
		for (UsernameRecord usernameRecord : course.getNotifiedUsers()) {
			User user = userService.findByUsername(usernameRecord.getUsername());
			
			Notification notification = new Notification("A new game has been added to course: "
			+ course.getCourseName() + ".");
			notification.setUser(user);
			
			user.addNotification(notification);
			
			userService.save(user);
		}
		/************************************************************/
		
		return "redirect:/game?courseID=" + courseID + "&gameID=" + course.getCourseGames().get(course.getCourseGames().size()-1).getGameId(); // redirects 
	}


}
