package com.yukoon.policy_cal.utils;

import com.yukoon.policy_cal.entities.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class GoodsUtil {

    public static final String token = "Bearer eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAB2LsQ7CIBRFf4UwlwYehdKuro4ujrSAoi0YgURj_Hdf3O65OedDW_HPZHdPZ2rdHhPt6K1GpHUJWk3SsqDAs2HinFnlAwswuQDaGCMHlKOtdBYKlNZC6hGPUrA-t3vOiZz8ek15y5c3OeS-I8fqeoxKW9ARuPzr8c81wAicf38tFoD_kQAAAA.-XAwqOevXXivhtzQXvkViEgS6NlZrQ-yORk46dgmBv4";

    public static final String requestUrl = "http://192.168.1.10:2700/api/goods/all";

    public static Page getData() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<Page> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, Page.class);
        Page p = response.getBody();
        return p;
    }
}
