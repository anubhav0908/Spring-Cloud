package com.quiz.service.impl;

import com.quiz.entities.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuestionClient;
import com.quiz.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;




    private QuestionClient questionClient;

    QuizServiceImpl(QuizRepository quizRepository,QuestionClient questionClient){
        this.quizRepository=quizRepository;
        this.questionClient=questionClient;
    }




    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {
        List<Quiz>quizzes=quizRepository.findAll();

        List<Quiz>newQuizList=quizzes.stream().map(quiz ->{
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            return quiz;
        }).collect(Collectors.toUnmodifiableList());
        return newQuizList;
    }

    @Override
    public Quiz get(Long id) {
       Quiz quiz =quizRepository.findById(id).orElseThrow(() -> new RuntimeException("quiz not found"));
       quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
       return quiz;
    }
}
