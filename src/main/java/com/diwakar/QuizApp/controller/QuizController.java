package com.diwakar.QuizApp.controller;

import com.diwakar.QuizApp.entities.Question;
import com.diwakar.QuizApp.entities.QuestionWrapper;
import com.diwakar.QuizApp.entities.Response;
import com.diwakar.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz (@RequestParam String language, @RequestParam int noQ, @RequestParam String quizTitle) {
        return quizService.createQuiz(language,noQ,quizTitle);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions (@PathVariable Integer id) {
        return quizService.getQuizQuestions (id);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId, @RequestBody List<Response> responses) {
        return quizService.calculateResult(quizId,responses);
    }
}
