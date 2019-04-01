package com.example.es.repository;


import com.example.es.domain.Board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void testInsert(){
        Board board = new Board();

        board.setTitle(" 제목 ");
        board.setContent(" 내용 ");
        board.setWriter("user01");

        boardRepository.save(board);

    }

    @Test
    public void testRead(){

        Optional<Board> board = boardRepository.findById(1L);


        System.out.println("read test :  " + board.get().getTitle() );
    }

    @Test
    public void testUpdate(){
        Optional<Board> board = boardRepository.findById(3L);


        System.out.println("read test :  " + board.toString() );
        board.get().setContent("3번째 내용 수정이다 이말이야");

        boardRepository.save(board.get());

    }

    @Test
    public void addDummy(){
        
    }
}
