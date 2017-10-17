package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.CommunityQuestion;
import org.springframework.stereotype.Service;

@Service
public interface CommunityQuestionService {
    CommunityQuestion postQuestion(CommunityQuestion communityQuestion);

    Iterable<CommunityQuestion> findAllQuestions();

    Iterable<CommunityQuestion> findAllUserQuestions(String username);

    int showQuestionNumber();

    void changePicture(String PICTURE_PATH, String email);

    void changeUsername(String username, String email);

    void updateMarked(int question_number);

    CommunityQuestion findOne(Integer number);
}