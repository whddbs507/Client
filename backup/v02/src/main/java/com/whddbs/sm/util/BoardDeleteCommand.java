package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.domain.Board;

public class BoardDeleteCommand implements Command {
  
  ObjectOutputStream out;
  ObjectInputStream in; 
  Scanner input;
  
  public BoardDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Scanner input) {
    this.out = out;
    this.in = in;
    this.input = input;
  }
  
  public void execute() {
    try {
      out.writeUTF("/board/delete");
      
      System.out.print("삭제할 번호 : ");
      int no = input.nextInt();
      input.nextLine();
      
      out.writeInt(no);
      out.flush();
      
      System.out.println(in.readUTF());
      
    } catch (Exception e) {
      
    }

  }
}
