package io.cortex.cortexweb.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Questions")
public class CommunityQuestion {
    @Id
    @GeneratedValue
    @Column(name = "question_number")
    private int QUESTION_NUMBER;

    @Column(name = "email")
    //@NotNull
    private String user_id;

    @Column(name = "title")
    //@NotNull
    private String title;

    @Column(name = "content")
    //@NotNull
    private String body;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getQUESTION_NUMBER() {
        return QUESTION_NUMBER;
    }

    public void setQUESTION_NUMBER(int QUESTION_NUMBER) {
        this.QUESTION_NUMBER = QUESTION_NUMBER;
    }
}
