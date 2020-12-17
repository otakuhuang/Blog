package com.jamhuang.blog.dao;

import com.jamhuang.blog.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long BlogId, Sort sort);

    @Transactional
    void deleteByBlogId(Long blogId);
}
