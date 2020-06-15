package com.example.controller;

import com.example.entity.MyBook;
import com.example.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mybook")
public class MyBookController {

    @Autowired // Autowiredを設定すると自動で実行時にnewしてくれる。いわゆるDI
    private MyBookRepository repo;

    @RequestMapping("")
    String index() {
        return "Hello MyBookController!";
    }

    //http://localhost:8080/mybook/get
    @RequestMapping("/get")
    String get() {
        // IDで検索
        Optional<MyBook> myBook = this.repo.findById(2);
        System.out.println("★IDで検索");
        myBook.ifPresent(o -> System.out.println(o)); // myBookがnull出ない場合のみ実行される

        // 全検索
        List<MyBook> myBookList = this.repo.findAll();
        System.out.println("★全検索");
        myBookList.forEach(o -> System.out.println(o));

        // MyBookRepositoryに実装したfindByMaxIdを使用
        Optional<Integer> maxMyBook = this.repo.findByMaxId();
        System.out.println("★MyBookRepositoryに実装したfindByMaxIdを使用");
        maxMyBook.ifPresent(o -> System.out.println(o)); // myBookがnull出ない場合のみ実行される

        return myBookList.toString();
    }
}
