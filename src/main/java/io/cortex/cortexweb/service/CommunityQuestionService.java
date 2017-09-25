package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.CommunityQuestion;
import org.springframework.stereotype.Service;

@Service
public interface CommunityQuestionService {
    CommunityQuestion postQuestion(CommunityQuestion communityQuestion);

    Iterable<CommunityQuestion> findAllQuestions();

    Iterable<CommunityQuestion> findAllUserQuestions(String username);

    int showQuestionNumber();
}