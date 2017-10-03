package io.cortex.cortexweb.model;


import javax.persistence.*;

@Entity
@Table(name = "Questions")
public class CommunityQuestion {
    @Id
    @GeneratedValue
    @Column(name = "question_number")
    private int QUESTION_NUMBER;

    @Column(name = "username")
    //@NotNull
    private String username;

    @Column(name = "title")
    //@NotNull
    private String title;

    @Column(name = "content")
    //@NotNull
    private String body;

    @Column(name = "email")
    private String email;

    @Column(name = "reputation_score")
    private int reputationScore;

    @Column(name = "img_url")
    private String img_url;

    @Column(name = "picture_path")
    private String PICTURE_PATH;

    private long time_stamp;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQUESTION_NUMBER() {
        return QUESTION_NUMBER;
    }

    public void setQUESTION_NUMBER(int QUESTION_NUMBER) {
        this.QUESTION_NUMBER = QUESTION_NUMBER;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getReputationScore() {
        return reputationScore;
    }

    public void setReputationScore(int reputationScore) {
        this.reputationScore = reputationScore;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPICTURE_PATH() {
        return PICTURE_PATH;
    }

    public void setPICTURE_PATH(String PICTURE_PATH) {
        this.PICTURE_PATH = PICTURE_PATH;
    }
}
