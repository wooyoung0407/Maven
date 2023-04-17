package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = Container.scanner;
        List<Article> articles = new ArrayList<>();
        int articleLastId = 0;


        Connection conn = null;
        PreparedStatement pstmt = null;


        try{
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";

            conn = DriverManager.getConnection(url, "root", "");

            String sql = "INSERT INTO article";
            sql += " SET regDate = NOW()";
            sql += ", updateDate = NOW()";
            sql += ", title = 'test3'";
            sql += ", content = 'test4'";

            pstmt = conn.prepareStatement(sql);

            int affectRows = pstmt.executeUpdate();

            System.out.println("affectRows = " + affectRows);
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }

        while (true) {
            System.out.printf("명령어)");
            String cmd = sc.nextLine();

            if (cmd.equals("등록")) {
                System.out.println("== 게시물 등록 ==\n");
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String content = sc.nextLine();

                int id = ++articleLastId;

                Article article = new Article(id, title, content);
                articles.add(article);

                System.out.printf("등록 성공 %d번\n", id);
            } else if (cmd.equals("목록")) {
                if (articles.isEmpty()) {
                    System.out.println("게시물이 존재하지 않습니다.\n");
                    continue;
                }
                System.out.println("번호 / 제목 / 내용\n");

                for (Article article : articles) {
                    System.out.printf("%d / %s / %s\n", article.id, article.title,article.content);
                }
                System.out.println("== 게시물 목록 ==\n");

            } else if (cmd.equals("종료")) {
                System.out.println("== 시스템 종료 ==\n");
                break;

            } else {
                System.out.println("명령어를 확인해 주세요\n");
            }
//            sc.close();
        }
    }
}
