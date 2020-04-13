package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import com.whddbs.sm.dao.BoardDao;

public class BoardDeleteCommand implements Command {
  
  BoardDao boardDao;
  Scanner input;
  
  public BoardDeleteCommand(BoardDao boardDao, Scanner input) {
    this.boardDao = boardDao;
    this.input = input;
  }
  
  public void execute() {
    try {
      System.out.print("삭제할 번호 : ");
      int no = input.nextInt();
      input.nextLine();

      boardDao.delete(no);
      
    } catch (Exception e) {
      
    }
  }
}
