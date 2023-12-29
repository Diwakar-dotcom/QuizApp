package com.diwakar.QuizApp.controller;

import com.diwakar.QuizApp.entities.Question;
import com.diwakar.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("language/{lang}")
    public ResponseEntity<List<Question>> getQuestionByProgrammingLang(@PathVariable("lang") String programmingLang) {
        return questionService.getQuestionByProgrammingLang(programmingLang);
    }
    @GetMapping("difficultyLevel/{level}")
    public ResponseEntity<List<Question>> getQuestionByDifficultyLevel(@PathVariable("level") String difficultyLevel) {
        return questionService.getQuestionByDifficultyLevel(difficultyLevel);
    }
    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

}
