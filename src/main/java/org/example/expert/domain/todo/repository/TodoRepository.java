package org.example.expert.domain.todo.repository;

import lombok.NonNull;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // LEFT JOIN FETCH (FETCH JOIN)을 활용하여 Todo를 조회할떄 그에 연관된 user도 같이 조회
    // @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")

    //  EntityGraph를 활용하여 Todo를 조회할떄 그에 연관된 user도 같이 조회
    @EntityGraph(attributePaths = {"user"})
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    // LEFT JOIN FETCH (FETCH JOIN)을 활용하여 Todo를 조회할떄 그에 연관된 user도 같이 조회
    // @Query("SELECT t FROM Todo t " +
    //         "LEFT JOIN FETCH t.user " +
    //         "WHERE t.id = :todoId")

    // EntityGraph를 활용하여 Todo를 조회할떄 그에 연관된 user도 같이 조회
    @EntityGraph(attributePaths = {"user"})
    @NonNull
    //  Not annotated method overrides method annotated with @NonNullApi 경고로 인한 추가
    // Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);
    Optional<Todo> findById(@NonNull @Param("todoId") Long todoId); // Cannot resolve property 'withUser'로 인한 이름 변경

    int countById(Long todoId);
}
