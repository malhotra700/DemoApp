package com.example.demoapp;

public class Profile {
    private String username;
    private String content;
    private String profilepic;

    public Profile() {
    }

    public Profile(String username, String content,String profilepic) {
        this.username = username;
        this.content = content;
        this.profilepic=profilepic;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
