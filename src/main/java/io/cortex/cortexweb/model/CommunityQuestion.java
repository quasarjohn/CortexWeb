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

    //TODO I may have to move this code to a Utils class for reusability
    public String parseEpochDate() {
        long current_time = System.currentTimeMillis();
        long time_difference = current_time - time_stamp;
        //23 hours and 59 minutes
        long limit = 86328000;

        if (time_difference <= limit) {
            return "asked " + parseToAgo(time_difference);
        } else {
            return "asked " + parseToDate();
        }
    }

    private String parseToDate() {
        SimpleDateFormat f = new SimpleDateFormat("MMM dd, yyyy");
        SimpleDateFormat g = new SimpleDateFormat("hh:mm a");
        return f.format(new Date(time_stamp)) + " at " + g.format(new Date(time_stamp));
    }

    private String parseToAgo(long time_difference) {
        long min = 60 * 1000;
        long hour = 60 * 60 * 1000;
        SimpleDateFormat h = new SimpleDateFormat("h");
        SimpleDateFormat m = new SimpleDateFormat("m");
        SimpleDateFormat s = new SimpleDateFormat("s");

        if (time_difference >= hour)
            return h.format(new Date(time_difference)) + " hour(s) ago";
        else if (time_difference >= min)
            return m.format(new Date(time_difference)) + " minute(s) ago";
        else
            return s.format(new Date(time_difference)) + " second(s) ago";
    }
}
