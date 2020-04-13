package com.whddbs.sm.util;

import java.util.List;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.domain.Board;

public class BoardListCommand implements Command {
  
  BoardDao boardDao;
  
  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  public void execute() {
    try {
      
      List<Board> boardList = boardDao.findAll();  
      
      for (Board board : boardList) {
        System.out.printf("%d %s %s\n", board.getNo()
            , board.getTitle(), board.getContents());
      }
      
    } catch (Exception e) {
      System.out.println("게시판 목록 오류 : " + e.getMessage());
    }
  }
}
