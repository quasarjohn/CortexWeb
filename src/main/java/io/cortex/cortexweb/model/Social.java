package io.cortex.cortexweb.model;

import javax.persistence.*;

@Entity
@Table(name = "Social")
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SEQUENCE_OF_EVENTS")
    private int SEQUENCE_OF_EVENTS;

    @Column(name = "following_email")
    private String followingEmail;

    @Column(name = "following_username")
    private String followingUsername;

    @Column(name = "following_reputation_score")
    private int followingReputationScore;

    @Column(name = "following_img_url")
    private String followingImgUrl;

    @Column(name = "following_picture_path")
    private String following_PICTURE_PATH;

    @Column(name = "follower_email")
    private String followerEmail;

    @Column(name = "follower_username")
    private String followerUsername;

    @Column(name = "follower_reputation_score")
    private int followerReputationScore;

    @Column(name = "follower_img_url")
    private String followerImgUrl;

    @Column(name = "follower_picture_path")
    private String follower_PICTURE_PATH;

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

    public String getFollowingImgUrl() {
        return followingImgUrl;
    }

    public void setFollowingImgUrl(String followingImgUrl) {
        this.followingImgUrl = followingImgUrl;
    }

    public String getFollowing_PICTURE_PATH() {
        return following_PICTURE_PATH;
    }

    public void setFollowing_PICTURE_PATH(String following_PICTURE_PATH) {
        this.following_PICTURE_PATH = following_PICTURE_PATH;
    }

    public String getFollowerImgUrl() {
        return followerImgUrl;
    }

    public void setFollowerImgUrl(String followerImgUrl) {
        this.followerImgUrl = followerImgUrl;
    }

    public String getFollower_PICTURE_PATH() {
        return follower_PICTURE_PATH;
    }

    public void setFollower_PICTURE_PATH(String follower_PICTURE_PATH) {
        this.follower_PICTURE_PATH = follower_PICTURE_PATH;
    }
}
