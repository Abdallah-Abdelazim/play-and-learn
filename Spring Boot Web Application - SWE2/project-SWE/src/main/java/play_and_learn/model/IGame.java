package play_and_learn.model;


public interface IGame {
    
    void addComment(Comment comment);
    Course getCourse();
    void setCourse(Course course);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    String getCreatorTeacherUsername();
    void setCreatorTeacherUsername(String creatorTeacherUsername);
    
}
