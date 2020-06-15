package com.example.repository;

import com.example.entity.MyBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// SpringBootではEntityManagerを使用しないでJpaRepositoryを使用するらしい
public interface MyBookRepository extends JpaRepository<MyBook, Integer> {

    @Query(value="SELECT MAX(id) AS id FROM mybook", nativeQuery = true)
    Optional<Integer> findByMaxId();
}
