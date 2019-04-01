package com.example.es.repository;


import com.example.es.domain.Board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;
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
        for( int i = 1; i <=200 ; i++){
            Board board = new Board();
            board.setTitle("제목 : "+ i);
            board.setContent("내용 : "+ i + "채우기");
            board.setWriter("user0"+ ( i%10 ) );
            boardRepository.save(board);
        }
    }

    @Test
    public void findByTitleTest(){

        boardRepository.findBoardByTitle("제목 : 1")
                .forEach(System.out::println);



    }
    @Test
    public void findByWriterTest(){

        boardRepository.findBoardByWriter("user00")
                .forEach(System.out::println);



    }

    @Test
    public void testIdOrderByPagin(){
        //
        Pageable paging = new PageRequest(0,10);

        Collection<Board> results = boardRepository.findByIdGreaterThanOrderByIdDesc(0L,paging);

        results.forEach(System.out::println);

    }


    @Test
    public void testIdPagingSort(){

        Pageable paging = new PageRequest(0,10, Sort.Direction.ASC, "Id");


        // Page<T> 를 이용하면 spring MVC와 연동할 때 편리하다 .
        Page<Board> results = boardRepository.findByIdGreaterThan(0L,paging);

        System.out.println("Page Size : "+ results.getSize() );
        System.out.println("total pages : "+ results.getTotalPages() );
        System.out.println("total count : "+ results.getTotalElements() );
        System.out.println("next : "+ results.nextPageable() );

        List<Board> list = results.getContent();

        list.forEach(System.out::println);


    }










}
