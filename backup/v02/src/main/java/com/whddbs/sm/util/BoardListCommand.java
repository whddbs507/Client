package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.whddbs.sm.domain.Board;

public class BoardListCommand implements Command {
  
  ObjectOutputStream out;
  ObjectInputStream in;
  List<Board> boardList = new ArrayList<>();
  
  public BoardListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }
  
  public void execute() {
    try {
      out.writeUTF("/board/list");
      out.flush();
      System.out.println(in.readUTF());
      boardList = (List<Board>) in.readObject();
      
      for (Board board : boardList) {
        System.out.printf("%d %s %s\n", board.getNo()
            , board.getTitle(), board.getContents());
      }
      
    } catch (Exception e) {
      System.out.println("게시판 목록 오류 : " + e.getMessage());
    }
  }
}
