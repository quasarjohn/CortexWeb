package io.cortex.cortexweb.repository;

import io.cortex.cortexweb.model.CommunityQuestion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommunityQuestionRepository extends CrudRepository<CommunityQuestion, Integer> {
    @Query(value = "select * from questions where username = ?1", nativeQuery = true)
    Iterable<CommunityQuestion> findAllUserQuestions(String username);

    @Query(value = "select count(*) from questions", nativeQuery = true)
    int showQuestionNumber();

    @Modifying
    @Transactional
    @Query(value = "update questions set PICTURE_PATH = ?1 where email = ?2", nativeQuery = true)
    void changePicture(String PICTURE_PATH, String email);

    @Modifying
    @Transactional
    @Query(value = "update questions set username = ?1 where email = ?2", nativeQuery = true)
    void changeUsername(String username, String email);

    @Modifying
    @Transactional
    @Query(value = "update questions set marked = 1 where question_number = ?1", nativeQuery = true)
    void updateMarked(int question_number);

    @Override
    CommunityQuestion findOne(Integer s);
}
