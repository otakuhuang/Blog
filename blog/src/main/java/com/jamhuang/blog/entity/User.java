package com.jamhuang.blog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String email;
    private String type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();

    public User() {
    }

    public User(Long id, String username, String password, String avatar, String email, String type, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.type = type;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", blogs=" + blogs +
                '}';
    }
}
