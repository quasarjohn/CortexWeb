package io.cortex.cortexweb.repository;

import io.cortex.cortexweb.model.Answer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    @Query(value = "select * from answers where question_number = ?1", nativeQuery = true)
    Iterable<Answer> findAnswersByQuestion_number(int question_number);

    @Modifying
    @Transactional
    @Query(value = "update answers set marked = 1 where question_number = ?1 and answer = ?2", nativeQuery = true)
    void updateMarked(int question_number, String answer);
}
