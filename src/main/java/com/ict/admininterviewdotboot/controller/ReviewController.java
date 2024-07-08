package com.ict.admininterviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.admininterviewdotboot.service.ReviewService;
import com.ict.admininterviewdotboot.vo.ReviewVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    
    @GetMapping("/reviewlist")
    public List<ReviewVO> getReviewList() {
        return reviewService.getReviewList();
    }
    @PostMapping("/reviewdetail")
    public List<ReviewVO> getReviewDetail(){
        return reviewService.getReviewDetail();
    }
    // @PostMapping("/deletereview")
    // public ResponseEntity<String> deleteReview(@RequestBody ReviewVO rvo) {
    //     boolean res = reviewService.deleteReview(rvo);
    //     if(res){
    //         return ResponseEntity.ok("성공");
    //     }else{
    //         return ResponseEntity.status(400).body("실패");
    //     }
        
    // }

    @PostMapping("/deletereview")
        public int deleteReview(@RequestBody ReviewVO reviewVO) {
        reviewVO.setActive("1");
        return reviewService.deleteReview(reviewVO);
    }
    
    
}
