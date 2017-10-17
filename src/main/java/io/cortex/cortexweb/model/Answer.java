package io.cortex.cortexweb.model;

import io.cortex.cortexweb.utils.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Answers")
public class Answer {
    @Id
    @Column(name = "time_stamp")
    private long time_stamp;

    @Column(name = "question_number")
    private String question_number;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "img_url")
    private String img_url;

    @Column(name = "reputation_score")
    private int reputation_score;

    @Column(name = "answer")
    private String body;

    @Column(name = "marked")
    private int marked;

    @Column(name = "parsed_time")
    private String parsed_time;

    public String getParsed_time() {
        return parsed_time;
    }

    public void setParsed_time(String parsed_time) {
        this.parsed_time = parsed_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getReputation_score() {
        return reputation_score;
    }

    public void setReputation_score(int reputation_score) {
        this.reputation_score = reputation_score;
    }

    public String getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(String question_number) {
        this.question_number = question_number;
    }

    public int getMarked() {
        return marked;
    }

    public void setMarked(int marked) {
        this.marked = marked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
