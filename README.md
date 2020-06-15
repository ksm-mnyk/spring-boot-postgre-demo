## DB準備
- create
```
create table mybook (
  id integer, 
  name varchar(10)
);
```
- insert
```
insert into mybook values (1, 'テスト1');
insert into mybook values (2, 'テスト2');
insert into mybook values (3, 'テスト3');
```
## 各ファイル説明

- src/main/resources/build.gradle  
jpaとpostgresql(jdbc?)を追加
```
  implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
  runtimeOnly 'org.postgresql:postgresql'
```

- src/main/resources/application.properties
  - DB接続情報を記載
  
## 参考

pureなSQL記載方法  
https://qiita.com/rennnosuke/items/2d3a06ac5a755c656d4b

Lombok  
https://qiita.com/opengl-8080/items/671ffd4bf84fe5e32557
