package com.example.es.repository;


import com.example.es.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface BoardRepository extends JpaRepository<Board,Long> {
}
