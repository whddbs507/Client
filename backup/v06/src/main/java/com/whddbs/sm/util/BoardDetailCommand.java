package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.domain.Board;

public class BoardDetailCommand implements Command {
  
  BoardDao boardDao;
  Scanner input;
  
  public BoardDetailCommand(BoardDao boardDao, Scanner input) {
    this.boardDao = boardDao;
    this.input = input;
  }
  
  public void execute() {
    try {
      System.out.print("확인할 번호 : ");
      int no = input.nextInt();
      input.nextLine();
      
      Board board = boardDao.findByNo(no);
      
        System.out.println("번호 : " + board.getNo());
        System.out.println("제목 : " + board.getTitle());
        System.out.println("내용 : " + board.getContents());
    } catch (Exception e) {
      System.out.println("삭제 실패");
    }
    
  }
}
