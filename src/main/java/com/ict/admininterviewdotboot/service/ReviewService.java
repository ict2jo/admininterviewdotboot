package com.ict.admininterviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.ReviewMapper;
import com.ict.admininterviewdotboot.vo.ReviewVO;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public List<ReviewVO> getReviewList(){
        return reviewMapper.getReviewList();
    }

    public List<ReviewVO> getReviewDetail(){
        return reviewMapper.getReviewDetail();
    }

    public int deleteReview(ReviewVO reviewVO){
        return reviewMapper.deleteReview(reviewVO);
    }
    
} 
