package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.Answer;
import io.cortex.cortexweb.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;

    @Autowired
    private void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public Iterable<Answer> findAnswersByQuestion_number(int question_number) {
        return answerRepository.findAnswersByQuestion_number(question_number);
    }

    @Override
    public void updateMarked(int question_number, String answer) {
        answerRepository.updateMarked(question_number, answer);
    }
}
