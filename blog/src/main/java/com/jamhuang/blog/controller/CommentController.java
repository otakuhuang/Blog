package com.jamhuang.blog.controller;

import com.jamhuang.blog.entity.Comment;
import com.jamhuang.blog.entity.User;
import com.jamhuang.blog.service.BlogService;
import com.jamhuang.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.guestAvatar}")
    private String guestAvatar;

    @GetMapping("/comments/{blogId}")
    public String getComments(@PathVariable Long blogId, Model model) {

        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments/post")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(guestAvatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
