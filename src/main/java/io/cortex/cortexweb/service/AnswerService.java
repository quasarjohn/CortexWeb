package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.Answer;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {
    void saveAnswer(Answer answer);

    Iterable<Answer> findAnswersByQuestion_number(int question_number);
}
