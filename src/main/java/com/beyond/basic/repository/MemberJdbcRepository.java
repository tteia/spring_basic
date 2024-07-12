//package com.beyond.basic.repository;
//
//import com.beyond.basic.domain.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class MemberJdbcRepository implements MemberRepository{
//    // Datasource 는 DB 와 JDBC 에서 사용하는 DB 연결 드라이버 객체.
//    // application.yml 에서 설정한 DB 정보가 자동으로 주입.
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    public Member save(Member member) {
//        try{
//            Connection connection = dataSource.getConnection();
//            String sql = "insert into member(name, email, password) values(?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, member.getName());
//            preparedStatement.setString(2, member.getEmail());
//            preparedStatement.setString(3, member.getPassword());
//            // 추가, 수정의 경우 executeUpdate. 조회의 경우 executeQuery.
//            preparedStatement.executeUpdate();
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<Member> findAll() {
//        List<Member> memberList = new ArrayList<>();
//
//        try {
//            Connection connection = dataSource.getConnection();
//            String sql = "SELECT * FROM member";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery(); // 쿼리를 날려주기.
//            // return 값은 resultSet 객체 !
//
//            while (resultSet.next()){ // 핵심 동작 원리는 cursor.
//                Long id = resultSet.getLong("id");
//                String name = resultSet.getString("name");
//                String email = resultSet.getString("email");
////                String password = resultSet.getString("password");
//
//                Member member = new Member();
//                member.setId(id);
//                member.setName(name);
//                member.setEmail(email);
////                member.setPassword(password);
//                memberList.add(member);
//            }
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//
//        return memberList;
//    }
//
//    @Override
//    public Optional<Member> findById(Long inputId) {
//        Member member = new Member();
//        try {
//            Connection connection = dataSource.getConnection();
//            String sql = "SELECT * FROM member WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setLong(1,inputId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            resultSet.next();
//            Long id = resultSet.getLong("id");
//            String name = resultSet.getString("name");
//            String email = resultSet.getString("email");
//            String password = resultSet.getString("password");
//
//            member.setId(id);
//            member.setName(name);
//            member.setEmail(email);
//            member.setPassword(password);
//
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        return Optional.ofNullable(member);
//    }
//}
