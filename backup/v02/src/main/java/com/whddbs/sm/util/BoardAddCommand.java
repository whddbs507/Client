package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.domain.Board;

public class BoardAddCommand implements Command {

  int no;
  Scanner input;
  ObjectOutputStream out;
  ObjectInputStream in;
  List<Board> boardList = new ArrayList<>();
  
  public BoardAddCommand(ObjectOutputStream out, ObjectInputStream in, Scanner input) {
    this.out = out;
    this.in = in;
    this.input= input;
  }

  public void execute() {
    try {
      out.writeUTF("/board/add");
      out.flush();
      boardList = (List<Board>) in.readObject();
      
      for (Board b : boardList) {
        no = b.getNo();
      }
      
      Board board = new Board();
      
      board.setNo(++no);
      
      System.out.print("제목 : ");
      String title = input.nextLine();
      board.setTitle(title);
      
      System.out.print("내용 : ");
      String contents = input.nextLine();
      board.setContents(contents);
      
      out.writeObject(board);
      
      System.out.println(in.readUTF());
      
    } catch (Exception e) {
      System.out.println("게시판 추가 오류 : " + e.getMessage());
    }
  }
}