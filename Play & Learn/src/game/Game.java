package game;

import java.io.Serializable;

public class Game implements Serializable {
    private static int gCount=0;
    private String name;
    private String description;
    private int id;
    private int rating;  // TODO
    private String creatorTeacherUsername;
    private String gameType; // MCQ , True & False , etc.

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    Template template = new Template(); // could be any game built using the temp


    public Game() {
        name="";
        description="";
        id=gCount++;
        rating = 0;
        creatorTeacherUsername = "";
        gameType = "";
    }

    public Game(String name, String description, String creatorTeacherUsername , String gameType) {
        this.name = name;
        this.description = description;
        this.id = gCount++;
        this.rating = 0;
        this.creatorTeacherUsername = creatorTeacherUsername;
        this.gameType = gameType;
    }

    public static int getgCount() {
        return gCount;
    }

    public static void setgCount(int gCount) {
        Game.gCount = gCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCreatorTeacherUsername() {
        return creatorTeacherUsername;
    }

    public void setCreatorTeacherUsername(String creatorTeacherUsername) {
        this.creatorTeacherUsername = creatorTeacherUsername;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }


    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", rating=" + rating +
                ", creatorTeacherUsername='" + creatorTeacherUsername + '\'' +
                ", template=" + template +
                '}';
    }
}
