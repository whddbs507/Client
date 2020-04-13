package com.whddbs.sm.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.domain.Board;

public class BoardDaoImpl implements BoardDao {
  
  Connection con;
  
  public BoardDaoImpl(Connection con) {
    this.con = con;
  }
  
  public int insert(Board board) throws Exception {
    
    Statement stmt = con.createStatement();
    
    return stmt.executeUpdate("insert into board(titl, idx) values('" + board.getTitle() + "', '" + board.getContents() + "')");
  }
  
  public List<Board> findAll() throws Exception {
    
    Statement stmt = con.createStatement();
    
    ResultSet rs = stmt.executeQuery("select no, titl, idx from board");
    
    List<Board> boardList = new ArrayList<>();
    
    while (rs.next()) {
      Board b = new Board();
      
      b.setNo(rs.getInt("no"));
      b.setTitle(rs.getString("titl"));
      b.setContents(rs.getString("idx"));
      
      boardList.add(b);
    }
    
    return boardList;
  }

  public Board findByNo(int no) throws Exception {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select no, titl, idx from board where no=" + no);
    
    if (rs.next()) {
      Board b = new Board();
      
      b.setNo(rs.getInt("no"));
      b.setTitle(rs.getString("titl"));
      b.setContents(rs.getString("idx"));
      
      return b;
    }
    return null;
  }
  
  public int delete(int no) throws Exception {
    Statement stmt = con.createStatement();
    return stmt.executeUpdate("delete from board where no=" + no);
  }
}