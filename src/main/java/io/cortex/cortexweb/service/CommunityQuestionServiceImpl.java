package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.CommunityQuestion;
import io.cortex.cortexweb.repository.CommunityQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityQuestionServiceImpl implements CommunityQuestionService {
    private CommunityQuestionRepository communityQuestionRepository;

    @Autowired
    public void setCommunityQuestionRepository(CommunityQuestionRepository communityQuestionRepository) {
        this.communityQuestionRepository = communityQuestionRepository;
    }

    @Override
    public CommunityQuestion postQuestion(CommunityQuestion communityQuestion) {
        return communityQuestionRepository.save(communityQuestion);
    }

    @Override
    public Iterable<CommunityQuestion> findAllQuestions() {
        return communityQuestionRepository.findAll();
    }

    @Override
    public Iterable<CommunityQuestion> findAllUserQuestions(String username) {
        return communityQuestionRepository.findAllUserQuestions(username);
    }

    @Override
    public int showQuestionNumber() {
        return communityQuestionRepository.showQuestionNumber();
    }

    @Override
    public void changePicture(String PICTURE_PATH, String email) {
        communityQuestionRepository.changePicture(PICTURE_PATH, email);
    }

    @Override
    public void changeUsername(String username, String email) {
        communityQuestionRepository.changeUsername(username, email);
    }

    @Override
    public void updateMarked(int question_number) {
        communityQuestionRepository.updateMarked(question_number);
    }

    @Override
    public CommunityQuestion findOne(Integer number) {
        return communityQuestionRepository.findOne(number);
    }


}
