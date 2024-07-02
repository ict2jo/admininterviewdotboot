package com.ict.admininterviewdotboot.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ict.admininterviewdotboot.mapper.TossMapper;
import com.ict.admininterviewdotboot.vo.TossVO;

@Service
public class TossService {
    @Autowired
    private TossMapper tossMapper;

    @Value("${toss.api.url}")
    private String tossApiUrl;

    @Value("${toss.secret.key}")
    private String tossSecretKey;
    
    public List<TossVO> payList() {
        return tossMapper.payList();
    }

    public boolean cancelPayment(String paymentKey, String t_idx, String authorizationHeader, String cancelReason, String a_id) {
            try {
                System.out.println("페이먼츠키22"+paymentKey);
                System.out.println("티티티아이디나오낭ㅇㅇㅇ" + t_idx);

    
                RestTemplate restTemplate = new RestTemplate();
                // UTF-8 인코딩 설정 추가
                restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", authorizationHeader);
                headers.set("Content-Type", "application/json; charset=UTF-8");
                
                String requestJson = String.format(
                "{\"paymentKey\":\"%s\",\"t_idx\":\"%s\",\"cancelReason\":\"%s\",\"a_id\":\"%s\"}",
                        paymentKey, t_idx, cancelReason, a_id
                );
                
                HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
                System.out.println("환불엔티티 "+entity);
    
                ResponseEntity<String> response = restTemplate.exchange(
                    "https://api.tosspayments.com/v1/payments/"+paymentKey+"/cancel",
                    HttpMethod.POST,
                    entity,
                    String.class
                );
                System.out.println("환불Response: " + response); // UTF-8로 변환된 응답 출력
    
                // JSON 응답 파싱
                ObjectMapper objectMapper = new ObjectMapper();
                TossVO tvo = objectMapper.readValue(response.getBody(), TossVO.class);
                System.out.println("환불파싱된 응답: " + tvo);
                tvo.setT_idx(t_idx);
    
                // 이용권 횟수
                tvo.setStatusCount(tvo.getTotalAmount()/1000); 
                
                if (tvo.getCancels() != null && !tvo.getCancels().isEmpty()) {
                    TossVO.Cancel cancel = tvo.getCancels().get(0);
                    String canceledAt = cancel.getCanceledAt();
                    int cancelAmount = cancel.getCancelAmount();
    
                    LocalDateTime canceledAtDateTime = LocalDateTime.parse(canceledAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    String formattedCanceledAt = canceledAtDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    
                    tvo.setCanceledAt(formattedCanceledAt);
                    
                    int res = tossMapper.cancelPayment(tvo);
                    int res2 = tossMapper.userPayCount2(tvo);
                }
            } catch (Exception e) {
                System.out.println("취소오류 : "+e);
            }
            return true;
        }
}

