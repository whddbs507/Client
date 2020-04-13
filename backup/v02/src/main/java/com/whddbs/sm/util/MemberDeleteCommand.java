package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import com.whddbs.sm.domain.Member;

public class MemberDeleteCommand implements Command {

  Scanner input;
  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Scanner input) {
    this.input = input;
    this.out = out;
    this.in = in;
  }

  public void execute() {
    try {
      out.writeUTF("/member/delete");
      
      System.out.print("삭제할 번호 :");
      int no = input.nextInt();
      input.nextLine();
      
      out.writeInt(no);
      out.flush();
      
      String response = in.readUTF();
      
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
      } else {
        System.out.println(in.readUTF());
      }
    } catch (Exception e) {
      
    }

  }

}
