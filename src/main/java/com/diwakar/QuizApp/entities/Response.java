package com.diwakar.QuizApp.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private Integer qId;
    private String response;
}
