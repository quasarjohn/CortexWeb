package io.cortex.cortexweb.repository;

import io.cortex.cortexweb.model.CommunityQuestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CommunityQuestionRepository extends CrudRepository<CommunityQuestion, String> {
    @Query(value = "select * from questions where email = ?1", nativeQuery = true)
    Iterable<CommunityQuestion> findAllUserQuestions(String email);

    @Query(value = "select count(*) from questions", nativeQuery = true)
    int showQuestionNumber();
}
