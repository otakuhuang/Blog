package com.jamhuang.blog.service;

import com.jamhuang.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    void deleteCommentByBlogId(Long blogId);
}
