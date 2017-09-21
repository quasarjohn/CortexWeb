package io.cortex.cortexweb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public String parseEpochDate(long time_stamp) {
        long current_time = System.currentTimeMillis();
        long time_difference = current_time - time_stamp;
        //23 hours and 59 minutes
        long limit = 86328000;

        if (time_difference <= limit) {
            return "asked " + parseToAgo(time_difference);
        } else {
            return "asked " + parseToDate(time_stamp);
        }
    }

    private String parseToDate(long time_stamp) {
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
