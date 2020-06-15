package com.example.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mybook")
@Data //(lombok)getter, setter, toString などのメソッドを自動生成
@AllArgsConstructor //(lombok)全てのフィールドを引数に受取るコンストラクタを自動生成
@NoArgsConstructor //(lombok)デフォルトコンストラクタ(引数なし)を自動生成。これがないとJPA的にエラーになる
public class MyBook {
    @Id @GeneratedValue
    private Integer id;
    private String name;
}