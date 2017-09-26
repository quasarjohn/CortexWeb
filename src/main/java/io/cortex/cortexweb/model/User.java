package io.cortex.cortexweb.model;

import javafx.beans.DefaultProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    @Email(message = "Please provide a valid email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "reputation_score")
    private int reputationScore;

    @Column(name = "bio")
    private String bio;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "first_name")
    @NotBlank(message = "Please provide your first name")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Please provide your last name")
    private String lastName;

    @Column(name = "password")
    @NotEmpty
    @Size(min = 8, max = 40)
    private String password;

    @Column
    private String api_key;

    @Column String img_url;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setReputationScore(int reputationScore) {
        this.reputationScore = reputationScore;
    }

    public int getReputationScore() {
        return reputationScore;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}