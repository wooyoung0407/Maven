DROP DATABASE IF EXISTS text_board;

CREATE DATABASE text_board;

USE text_board;

CREATE TABLE article(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL
    );

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = 'test1',
content = 'test2'

SELECT * FROM article;