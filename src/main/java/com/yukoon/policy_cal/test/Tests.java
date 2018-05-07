package com.yukoon.policy_cal.test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
public class Tests {

    public static final String token = "Bearer eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAB2LsQ7CIBRFf4UwlwYehdKuro4ujrSAoi0YgURj_Hdf3O65OedDW_HPZHdPZ2rdHhPt6K1GpHUJWk3SsqDAs2HinFnlAwswuQDaGCMHlKOtdBYKlNZC6hGPUrA-t3vOiZz8ek15y5c3OeS-I8fqeoxKW9ARuPzr8c81wAicf38tFoD_kQAAAA.-XAwqOevXXivhtzQXvkViEgS6NlZrQ-yORk46dgmBv4";

    public static final String requestUrl = "http://192.168.1.10:2700/api/goods/all";

    public static void main(String[] args) throws Exception {
        Page result = new Tests().getData();
        for (Goods goods : result.getDatas()) {
            System.out.println(goods.getSpell());
        }
        System.out.print(result);
    }

    public Page getData() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<Page> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, Page.class);
        Page p = response.getBody();
        return p;
    }

    public static class Goods implements java.io.Serializable{
        private String id;
        private String spell;
        private String units;
        private int spec;
        private BigDecimal sellingPrice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSpell() {
            return spell;
        }

        public void setSpell(String spell) {
            this.spell = spell;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        public int getSpec() {
            return spec;
        }

        public void setSpec(int spec) {
            this.spec = spec;
        }

        public BigDecimal getSellingPrice() {
            return sellingPrice;
        }

        public void setSellingPrice(BigDecimal sellingPrice) {
            this.sellingPrice = sellingPrice;
        }
    }

    public static class Page implements java.io.Serializable {

        private boolean success;
        private int total;
        private List<Goods> datas;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Goods> getDatas() {
            return datas;
        }

        public void setDatas(List<Goods> datas) {
            this.datas = datas;
        }
    }
}
