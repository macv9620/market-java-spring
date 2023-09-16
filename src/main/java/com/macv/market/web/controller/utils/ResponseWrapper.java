package com.macv.market.web.controller.utils;

import com.macv.market.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResponseWrapper<T> {
    private String message;
    private List<T> data;

    public ResponseWrapper(String message, List<T> data) {
        this.message = message;
        this.data = data;
    }

    public ResponseWrapper(String message, Optional<T> data){
        ArrayList<T> dataList = new ArrayList<>();
        data.map(p -> dataList.add(p));
        this.message = message;
        this.data = dataList;
    }

    public ResponseWrapper(String message, T data) {
        ArrayList<T> dataList = new ArrayList<>();
        dataList.add(data);
        this.message = message;
        this.data = dataList;
    }

    public ResponseWrapper(String message) {
        this.message = message;
        this.data = null;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
