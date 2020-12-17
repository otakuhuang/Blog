package com.jamhuang.blog.utils;

import com.jamhuang.blog.entity.Comment;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentUtils {

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments
     * @return
     */
    public static List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        comBineChildren(commentsView);
        return commentsView;
    }

    /**
     * 合并评论的各层子代到第一级子代集合里
     *
     * @param comments root 根节点，blog 不为空的对象集合
     */
    private static void comBineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                // 循环迭代，找出子代，存放在 tempReplys 中
                recursively(reply);
            }
            // 修改顶级节点的 reply 集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            // 清除临时存放区
            tempReplys = new ArrayList<>();

        }
    }

    // 存放迭代找出的所有子代的集合
    private static List<Comment> tempReplys = new ArrayList<>();

    /**
     * 递归迭代，剥洋葱
     *
     * @param comment 被迭代的对象
     */
    private static void recursively(Comment comment) {
        // 顶节点添加到临时存放集合
        tempReplys.add(comment);
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }
}
