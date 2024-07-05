package com.ict.admininterviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.admininterviewdotboot.vo.CommentVO;

@Mapper
public interface CommentsMapper {
    List<CommentVO> getComments(String r_idx);
    int deleteComment(CommentVO commentVO);
}
