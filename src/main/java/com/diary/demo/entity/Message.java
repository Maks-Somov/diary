package com.diary.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private String username;
    private String msg;
    private String email;
}