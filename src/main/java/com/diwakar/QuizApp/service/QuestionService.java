package com.diwakar.QuizApp.service;

import com.diwakar.QuizApp.entities.Question;
import com.diwakar.QuizApp.dao.QuestionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = Optional.of(questionDao.findAll()).orElse(Collections.emptyList());
            return questions.isEmpty()
                    ? new ResponseEntity<>(questions,HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(questions,HttpStatus.OK);
        } catch (Exception e) {
            log.error("An error occurred while retrieving the questions "+e.getMessage());
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByProgrammingLang(String programmingLang) {
        try {
            List<Question> questions = Optional.of(questionDao.findByProgrammingLang(programmingLang)).orElse(Collections.emptyList());
            return questions.isEmpty()
                    ? new ResponseEntity<>(questions,HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(questions,HttpStatus.OK);
        } catch (Exception e) {
            log.error("An error occurred while retrieving the questions "+e);
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByDifficultyLevel(String difficultyLevel) {
        try {
            List<Question> questions = Optional.of(questionDao.findByDiffcultyLevel(difficultyLevel)).orElse(Collections.emptyList());
            return questions.isEmpty()
                    ? new ResponseEntity<>(questions,HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            log.error("An error occurred while retrieving the questions "+e);
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            Question savedQuestion = questionDao.save(question);
            return new ResponseEntity<>("Question added with ID : " + savedQuestion.getQId(), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            log.error("Data Integrity violation while adding a question {}", e.getMessage());
            return new ResponseEntity<>("Data integrity violation", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("An unexpected error occurred while adding a question {}", e.getMessage());
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
