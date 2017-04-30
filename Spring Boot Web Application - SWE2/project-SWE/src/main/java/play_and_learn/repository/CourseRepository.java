package play_and_learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import play_and_learn.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

	Course findByCourseId(int courseId);
	
	Course findByCourseName(String courseName);
	
	List<Course> findAll();
	
}
