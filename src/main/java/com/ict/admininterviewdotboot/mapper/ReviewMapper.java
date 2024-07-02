package com.ict.admininterviewdotboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.admininterviewdotboot.vo.ReviewVO;
import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewVO> getReviewList();
    List<ReviewVO> getReviewDetail();
    int deleteReview(ReviewVO reviewVO);
}
