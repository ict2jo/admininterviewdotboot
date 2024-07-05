package com.ict.admininterviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.CommentsSuccMapper;
import com.ict.admininterviewdotboot.vo.CommentsSuccVO;

@Service
public class CommentsSuccService {
    @Autowired
    private CommentsSuccMapper commentsSuccMapper;

    public List<CommentsSuccVO> getComments(String s_idx) {
        return commentsSuccMapper.getComments(s_idx);
    } 

    public int deleteComment(CommentsSuccVO commentsSuccVO){
        return commentsSuccMapper.deleteComment(commentsSuccVO);
    }
}
