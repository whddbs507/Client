package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.common.reflection.qual.GetMethod;
import com.whddbs.sm.domain.Member;

public class MemberListCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  List<Member> memberList = new ArrayList<>();
  
  public MemberListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  public void execute() {
    try {
      out.writeUTF("/member/list");
      out.writeObject("d");
      System.out.println(in.readUTF());
      memberList = (List<Member>) in.readObject();
      
      for (Member member : memberList) {
        System.out.printf("%d %s %s %s\n", member.getNo()
            , member.getName(), member.getEmail(), member.getPw());
      }
    } catch (Exception e) {
      System.out.println("멤버 리스트 호출 오류 : " + e.getMessage());
    }
  }
}
