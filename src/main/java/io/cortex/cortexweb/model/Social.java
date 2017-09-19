package io.cortex.cortexweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Social")
public class Social {
    @Id
    @Column(name = "SEQUENCE_OF_EVENTS")
    private int SEQUENCE_OF_EVENTS;

    @Column(name = "following_email")
    private String followingEmail;

    @Column(name = "following_username")
    private String followingUsername;

    @Column(name = "following_reputation_score")
    private int followingReputationScore;

    @Column(name = "follower_email")
    private String followerEmail;

    @Column(name = "follower_username")
    private String followerUsername;

    @Column(name = "follower_reputation_score")
    private int followerReputationScore;

    public String getFollowerEmail() {
        return followerEmail;
    }

    public void setFollowerEmail(String followerEmail) {
        this.followerEmail = followerEmail;
    }

    public String getFollowerUsername() {
        return followerUsername;
    }

    public void setFollowerUsername(String followerUsername) {
        this.followerUsername = followerUsername;
    }

    public int getFollowerReputationScore() {
        return followerReputationScore;
    }

    public void setFollowerReputationScore(int followerReputationScore) {
        this.followerReputationScore = followerReputationScore;
    }

    public int getSEQUENCE_OF_EVENTS() {
        return SEQUENCE_OF_EVENTS;
    }

    public void setSEQUENCE_OF_EVENTS(int SEQUENCE_OF_EVENTS) {
        this.SEQUENCE_OF_EVENTS = SEQUENCE_OF_EVENTS;
    }

    public String getFollowingEmail() {
        return followingEmail;
    }

    public void setFollowingEmail(String followingEmail) {
        this.followingEmail = followingEmail;
    }

    public String getFollowingUsername() {
        return followingUsername;
    }

    public void setFollowingUsername(String followingUsername) {
        this.followingUsername = followingUsername;
    }

    public int getFollowingReputationScore() {
        return followingReputationScore;
    }

    public void setFollowingReputationScore(int followingReputationScore) {
        this.followingReputationScore = followingReputationScore;
    }
}
