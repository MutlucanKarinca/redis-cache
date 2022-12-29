package com.mutlucankarinca.productmanagement.middleware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class HelperMethods
{
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObject){
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObject);

        return new ResponseEntity<Object>(map,status);
    }
}
