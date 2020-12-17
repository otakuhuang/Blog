package com.jamhuang.blog.service.impl;

import com.jamhuang.blog.dao.CommentRepository;
import com.jamhuang.blog.entity.Comment;
import com.jamhuang.blog.service.CommentService;
import com.jamhuang.blog.utils.CommentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {

        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return CommentUtils.eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {

        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteCommentByBlogId(Long blogId) {
        commentRepository.deleteByBlogId(blogId);
    }
}
