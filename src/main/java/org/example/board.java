package org.example;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class board {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(true) {
            String cmd = scan.nextLine();

            if(cmd.equals("list")) {
                // board db의 article table에서 데이터를 꺼내와 출력

                // 자동임포트 : alt + enter
                Connection conn = null; // DB 접속하는 객체
                Statement stmt = null; // SQL 전송하는 객체
                ResultSet rs = null; // 결과 받아오는 객체

                String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
                String user = "root";
                String pass = "";


                try {
                    // 1. 드라이버 세팅
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 2. Connection 획득
                    conn = DriverManager.getConnection(url, user, pass);

                    //3. Statement 생성
                    stmt = conn.createStatement();

//            System.out.println("3번까지 문제 없이 실행");

                    //4. SQL 처리하고 결과 ResultSet에 받아오기
                    String sql = "SELECT * FROM article";
                    rs = stmt.executeQuery(sql);

                    while(rs.next()) {
                        System.out.println(rs.getString("title"));
                        System.out.println(rs.getString("content")); // 문자열로 리턴
                        System.out.println(rs.getInt("id")); //
                        System.out.println("========================");
                    }

                } catch(Exception e) {
                    System.out.println("접속 시도중 문제 발생!!");
                }
            } else if(cmd.equals("add")) {

                Connection conn = null; // DB 접속하는 객체
                Statement stmt = null; // SQL 전송하는 객체
                ResultSet rs = null; // 결과 받아오는 객체

                String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
                String user = "root";
                String pass = "";

                try {
                    // 1. 드라이버 세팅
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 2. Connection 획득
                    conn = DriverManager.getConnection(url, user, pass);

                    //3. Statement 생성
                    stmt = conn.createStatement();

                    System.out.println("제목을 입력해주세요 : ");
                    String title = scan.nextLine();
                    System.out.println("내용을 입력해주세요 : ");
                    String content = scan.nextLine();

                    //4. SQL 처리하고 결과 ResultSet에 받아오기
                    String sql = "INSERT INTO article SET title = '" + title + "', content = '" + content + "'";
                    stmt.executeUpdate(sql);
                    System.out.println("게시물 등록이 완료되었습니다.");
                    // 조회 결과 있는 거 -> executeQuery(sql);
                    // 조회 결과 없는 거 -> executeUpdate(sql);

                } catch(Exception e) {
                    System.out.println("접속 시도중 문제 발생!!");
                }
            } else if(cmd.equals("exit")) {
                System.out.println("프로그램 종료.");
                break;
            }
        }

    }
}
