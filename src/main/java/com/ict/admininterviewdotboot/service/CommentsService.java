package com.ict.admininterviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.CommentsMapper;
import com.ict.admininterviewdotboot.vo.CommentVO;

@Service
public class CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;

    public List<CommentVO> getComments(String r_idx) {
        return commentsMapper.getComments(r_idx);
    }
     
    public int deleteComment(CommentVO commentVO){
        return commentsMapper.deleteComment(commentVO);
    }
}
