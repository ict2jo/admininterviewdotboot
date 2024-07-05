package com.ict.admininterviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.admininterviewdotboot.service.CommentsSuccService;
import com.ict.admininterviewdotboot.vo.CommentsSuccVO;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/commentsucc")
public class SuccCommentController {
    
    @Autowired
    private CommentsSuccService commentsSuccService;

    @GetMapping("/comment")
    public List<CommentsSuccVO> getComments(String s_idx) {
        List<CommentsSuccVO> list = commentsSuccService.getComments(s_idx);
        return list;
    }
    
    @PostMapping("/deletecomment")
    public int deleteComment(@RequestBody CommentsSuccVO commentsSuccVO) {
        commentsSuccVO.setActive("1");
        return commentsSuccService.deleteComment(commentsSuccVO);
    }
}
