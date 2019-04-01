package com.example.es.repository;


import com.example.es.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board,Long> {

    public List<Board> findBoardByTitle(String title);

    public Collection<Board> findBoardByWriter(String Writer);
}
