package io.cortex.cortexweb.model;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
