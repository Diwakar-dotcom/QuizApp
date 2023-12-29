package com.diwakar.QuizApp.dao;

import com.diwakar.QuizApp.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    // Automatically handled by Spring Data Jpa
    public List<Question> findByProgrammingLang(String programmingLang);
    public List<Question> findByDiffcultyLevel(String difficultyLevel);
//    Native Query - JPQL
    @Query(value = "SELECT * FROM question q WHERE q.programming_lang = :language ORDER BY RANDOM() LIMIT :noQ", nativeQuery = true)
    List<Question> findRandomQuestionsByProgrammingLang(String language, int noQ);
}
