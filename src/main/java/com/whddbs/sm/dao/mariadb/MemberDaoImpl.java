package com.whddbs.sm.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Member;

public class MemberDaoImpl implements MemberDao {
  
  Connection con;
  
  public MemberDaoImpl(Connection con) {
    this.con = con;
  }
  
  public int insert(Member member) throws Exception {
    Statement stmt = con.createStatement();
    
    return stmt.executeUpdate("insert into member(no,name,email,pw) values('" + member.getNo()+ "', '" + member.getName() + "', '" + member.getEmail() + "', '" + member.getPw() + "')");
  }
  
  public int delete(int no) throws Exception {
    Statement stmt = con.createStatement();
    
    return stmt.executeUpdate("delete from member where no = " + no);
  }

  public List<Member> findAll() throws Exception {
    Statement stmt = con.createStatement();
    
    ResultSet rs = stmt.executeQuery("select no,name,email,pw from member");
    
    List<Member> memberList = new ArrayList<>();
    
    while (rs.next()) {
      Member m = new Member();
      
      m.setNo(rs.getInt("no"));
      m.setName(rs.getString("name"));
      m.setEmail(rs.getString("email"));
      m.setPw(rs.getString("pw"));
      
      memberList.add(m);
    }
    
    return memberList;
  }

  public Member findByNo(int no) throws Exception {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select no,name,email,pw from member where no = " + no);
    if (rs.next()) {
      Member m = new Member();
      
      m.setNo(rs.getInt("no"));
      m.setName(rs.getString("name"));
      m.setEmail(rs.getString("email"));
      m.setPw(rs.getString("pw"));
      return m;
    }
    return null;
  }
}