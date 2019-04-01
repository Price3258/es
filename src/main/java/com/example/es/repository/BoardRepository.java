package com.example.es.repository;


import com.example.es.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findBoardByTitle(String title);

    Collection<Board> findBoardByWriter(String Writer);

    List<Board> findByIdGreaterThanOrderByIdDesc(Long Id, Pageable paging);


    Page<Board> findByIdGreaterThan( Long Id, Pageable paging);


}
