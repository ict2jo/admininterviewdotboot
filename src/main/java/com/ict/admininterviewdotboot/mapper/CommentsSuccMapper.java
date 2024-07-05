package com.ict.admininterviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.admininterviewdotboot.vo.CommentsSuccVO;

@Mapper
public interface CommentsSuccMapper {
    List<CommentsSuccVO> getComments(String s_idx);
    int deleteComment(CommentsSuccVO commentsSuccVO);
}
