package com.diwakar.QuizApp.dao;


import com.diwakar.QuizApp.entities.Question;
import com.diwakar.QuizApp.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
