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
    public Iterable<CommunityQuestion> displayAllQuestions() {
        return communityQuestionRepository.findAll();
    }
}
