package org.example;

public class Article {
    int id;
    String title;
    String content;

    String regDate;

    String updateDate;

    public Article(int id, String regDate, String updateDate, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }
}
