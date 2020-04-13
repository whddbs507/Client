package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import com.whddbs.sm.domain.Member;

public class MemberDetailCommand implements Command {
  
  ObjectOutputStream out;
  ObjectInputStream in; 
  Scanner input;
  
  public MemberDetailCommand(ObjectOutputStream out, ObjectInputStream in, Scanner input) {
    this.input = input;
    this.out = out;
    this.in = in;
  }
  
  public void execute() {
    try {
      out.writeUTF("/member/detail");
      
      System.out.print("확인할 번호 : ");
      int no = input.nextInt();
      input.nextLine();
      
      out.writeInt(no);
      out.flush();
      
      String respond = in.readUTF();
      
      if (respond.equals("OK")) {
        Member member = (Member) in.readObject();
        
        System.out.println("번호 : " + member.getNo());
        System.out.println("이름 : " + member.getName());
        System.out.println("이메일 : " + member.getEmail());
        System.out.println("비밀번호 : " + member.getPw());
      } else {
        System.out.println(in.readUTF());
      }
    } catch (Exception e) {
      
    }
  }
}
