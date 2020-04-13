package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.domain.Board;

public class BoardDetailCommand implements Command {
  
  ObjectOutputStream out;
  ObjectInputStream in;
  Scanner input;
  
  public BoardDetailCommand(ObjectOutputStream out, ObjectInputStream in, Scanner input) {
    this.out = out;
    this.in = in;
    this.input = input;
  }
  
  public void execute() {
    try {
      out.writeUTF("/board/detail");
      
      System.out.print("확인할 번호 : ");
      int no = input.nextInt();
      input.nextLine();
      
      out.writeInt(no);
      out.flush();
      String str = in.readUTF();
      
      if (str.equals("OK")) {
        Board board = (Board) in.readObject();
        
        System.out.println("번호 : " + board.getNo());
        System.out.println("제목 : " + board.getTitle());
        System.out.println("내용 : " + board.getContents());
      } else if (str.equals("FAIL")) {
        System.out.println(in.readUTF());
      }
    } catch (Exception e) {
      
    }
    
  }
}
