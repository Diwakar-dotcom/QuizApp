package com.diwakar.QuizApp.service;

import com.diwakar.QuizApp.dao.QuestionDao;
import com.diwakar.QuizApp.dao.QuizDao;
import com.diwakar.QuizApp.entities.Question;
import com.diwakar.QuizApp.entities.QuestionWrapper;
import com.diwakar.QuizApp.entities.Quiz;
import com.diwakar.QuizApp.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String language, int noQ, String quizTitle) {

        List<Question> questions = questionDao.findRandomQuestionsByProgrammingLang(language, noQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(quizTitle);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question q : questionsFromDB) {
            questionsForUser.add(new QuestionWrapper(q.getQId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4()));
        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer quizId, List<Response> responses) {
        Optional<Quiz>  quiz = quizDao.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getCorrectAnswer())) right++;
            i++;
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
