package com.whddbs.sm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.domain.Board;

public class BoardAddCommand implements Command {

  Scanner input;
  BoardDao boardDao;
  
  public BoardAddCommand(BoardDao boardDao, Scanner input) {
    this.boardDao = boardDao;
    this.input= input;
  }

  public void execute() {
    try {
      int no = 1;
      
      Board board = new Board();
      
      System.out.print("제목 : ");
      String title = input.nextLine();
      board.setTitle(title);
      
      System.out.print("내용 : ");
      String contents = input.nextLine();
      board.setContents(contents);
      
      boardDao.insert(board);
      
    } catch (Exception e) {
      System.out.println("게시판 추가 오류 : " + e.getMessage());
    }
  }
}