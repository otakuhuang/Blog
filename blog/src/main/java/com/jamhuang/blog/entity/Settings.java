package com.jamhuang.blog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String value;
    private String remark;

    public Settings() {
    }

    public Settings(String name, String value, String remark) {
        this.name = name;
        this.value = value;
        this.remark = remark;
    }

    public Settings(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
