package play_and_learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import play_and_learn.model.Course;
import play_and_learn.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	public void saveCourse(Course course) {
		courseRepository.save(course);
	}
	
	public Course findByID(int courseID) {
		return courseRepository.findByCourseId(courseID);
	}
	
	public Course findByName(String courseName) {
		return courseRepository.findByCourseName(courseName);
	}
	
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
	public long getNumberOfRecords() {
		return courseRepository.count();
	}
	
	public void deleteCourse(int courseID) {
		courseRepository.delete(courseID);
	}
}
