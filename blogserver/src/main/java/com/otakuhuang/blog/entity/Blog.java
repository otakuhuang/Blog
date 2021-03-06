package com.otakuhuang.blog.entity;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1364684376907970518L;

    private Long id;
    @Size(min = 1, message = "标题不能为空")
    @NotNull(message = "标题不能为空")
    private String title;
    @Size(min = 1, message = "副标题不能为空")
    @NotNull(message = "副标题不能为空")
    private String subTitle;
    @Size(min = 1, message = "标志不能为空")
    @NotNull(message = "标志不能为空")
    private String flag;
    @Size(min = 1, message = "首图不能为空")
    @NotNull(message = "首图不能为空")
    private String firstPicture;
    @Size(min = 1, message = "内容不能为空")
    @NotNull(message = "内容不能为空")
    private String content;
    private boolean appreciation;
    private boolean published;
    private boolean commentabled;
    private boolean recommend;
    private boolean shareStatement;
    private Long views;
    @NotNull(message = "分类不能为空")
    private Long typeId;
    //    private List<Tag> tags = new ArrayList<>();
    @NotNull(message = "用户不能为空")
    private Long userId;
    //    private List<Comment> comments = new ArrayList<>();
//    private String tagIds;
    private boolean isSave;
    private boolean isDelete;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
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

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", flag='" + flag + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", content='" + content + '\'' +
                ", appreciation=" + appreciation +
                ", published=" + published +
                ", commentabled=" + commentabled +
                ", recommend=" + recommend +
                ", shareStatement=" + shareStatement +
                ", views=" + views +
                ", typeId=" + typeId +
                ", userId=" + userId +
                ", isSave=" + isSave +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
