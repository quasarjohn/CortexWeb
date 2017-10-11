package io.cortex.cortexweb.model;

import io.cortex.cortexweb.utils.Utils;

public class Answer {

    private int question_number;

    private String username;
    private String body;
    private long time_stamp;

    private String parsed_time;

    public String getParsed_time() {
        return parsed_time;
    }

    public void setParsed_time(String parsed_time) {
        this.parsed_time = parsed_time;
    }

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
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
}
