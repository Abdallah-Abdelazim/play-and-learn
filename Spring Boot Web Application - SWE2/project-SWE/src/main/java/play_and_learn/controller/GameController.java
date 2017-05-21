package play_and_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import play_and_learn.model.Comment;
import play_and_learn.model.Course;
import play_and_learn.model.Game;
import play_and_learn.model.GameChange;
import play_and_learn.model.IGame;
import play_and_learn.model.Notification;
import play_and_learn.model.User;
import play_and_learn.model.UsernameRecord;
import play_and_learn.service.CourseService;
import play_and_learn.service.GameService;
import play_and_learn.service.UserService;

@Controller
public class GameController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private GameService gameService;
	
	@GetMapping("/game")
	String openGame(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		// check if there is no logged in user
		if (userService.getLoggedInUser() ==null) {
			model.addAttribute("error", "Please create an account then log in using it.");
			return "unauthorized-access";
		}	
		
		Course course = courseService.findByID(courseID);
		Game game = course.getGameByID(gameID);
		
		model.addAttribute("currentLoggedInUser", userService.getLoggedInUser());
		model.addAttribute("courseID", courseID);
		model.addAttribute("game", game);
		
		return "game";
	}
	
	
	@PostMapping("/game")
	String evaluateUserAnswers(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID
			, Model model) {
		
		Course course = courseService.findByID(courseID);
		IGame game = course.getGameByID(gameID);
		
		model.addAttribute("currentLoggedInUser", userService.getLoggedInUser());
		model.addAttribute("courseID", courseID);
		model.addAttribute("game", game);
		
		/* add user to notification list in course */
		UsernameRecord usernameRecord = new UsernameRecord(userService.getLoggedInUser());
		usernameRecord.setCourse(course);
		if (!(course.getNotifiedUsers().contains(usernameRecord))) {
			course.addNotifiedUser(usernameRecord);
		}
		
		courseService.saveCourse(course);
		/*******************************************/
		
		return "game";
	}
	
	@GetMapping("/addcomment")
	String openCommentForm(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		model.addAttribute("courseID", courseID);
		model.addAttribute("gameID", gameID);
		model.addAttribute("comment", new Comment());
		
		return "addcomment";
	}
	
	@PostMapping("/addcomment")
	String addComment(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID
			, @ModelAttribute("comment") Comment comment, Model model) {
		
		Course course = courseService.findByID(courseID);
		
		comment.setGame(gameService.findByID(gameID));
		
		comment.setCommenterName(userService.getLoggedInUser());
		course.getGameByID(gameID).addComment(comment);
		
		courseService.saveCourse(course);  // update the course back
		
		/* notify the creator teacher with this new comment */
		User creatorTeacher = userService.findByUsername(
				gameService.findByID(gameID).getCreatorTeacherUsername());
		
		Notification notification = new Notification("User " 
				+ userService.getLoggedInUser() + " has commented on your course "
				+ course.getCourseName() + ".");
		notification.setUser(creatorTeacher);
		
		creatorTeacher.addNotification(notification);
		
		userService.save(creatorTeacher);
		/*******************************************************/
		
		return "redirect:/game?courseID=" + courseID + "&gameID=" + gameID; // redirect to game
	}
	
	@RequestMapping("/cancelgame")
	public String cancelGame(@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		Course course = courseService.findByID(courseID);
		course.getGameByID(gameID).setCancelled(true);
		
		/* Record the game change */
		GameChange gameChange = new GameChange("Cancel Game", userService.getLoggedInUser());
		gameChange.setGame(course.getGameByID(gameID));
		course.getGameByID(gameID).addGameChange(gameChange);
		/**************************/
		
		courseService.saveCourse(course);
		
		return "redirect:/course?courseID=" + courseID; // redirect to course
	}
	
	@RequestMapping("/trackgame")
	public String openTrackGamePage (@RequestParam(value="courseID", required=true) int courseID
			, @RequestParam(value="gameID", required=true) int gameID, Model model) {
		
		model.addAttribute("courseID", courseID);
		model.addAttribute("gameID", gameID);
		model.addAttribute("game", courseService.findByID(courseID).getGameByID(gameID));
		model.addAttribute("gamechanges"
				, courseService.findByID(courseID).getGameByID(gameID).getGameChanges());
		return "trackgame";
	}

}
