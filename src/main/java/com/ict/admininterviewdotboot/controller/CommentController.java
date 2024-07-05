package com.ict.admininterviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.admininterviewdotboot.service.CommentsService;
import com.ict.admininterviewdotboot.vo.CommentVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentsService commentsService;
    // 면접 후기 댓글
    @GetMapping("/comment")
    public List<CommentVO> getComments(String r_idx) {
        List<CommentVO> list = commentsService.getComments(r_idx);   
        System.out.println(list);
        return list;
    } 

    @PostMapping("/deletecomment")
    public int deleteComment(@RequestBody CommentVO commentVO) {
        commentVO.setActive("1");
        return commentsService.deleteComment(commentVO);
    }
}
