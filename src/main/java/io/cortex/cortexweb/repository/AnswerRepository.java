package io.cortex.cortexweb.repository;

import io.cortex.cortexweb.model.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    @Query(value = "select * from answers where question_number = ?1", nativeQuery = true)
    Iterable<Answer> findAnswersByQuestion_number(int question_number);
}
