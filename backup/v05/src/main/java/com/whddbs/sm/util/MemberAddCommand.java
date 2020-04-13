package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Member;

public class MemberAddCommand implements Command {
  
  MemberDao memberDao;
  Scanner input;
  
  public MemberAddCommand(MemberDao memberDao, Scanner input) {
    this.memberDao = memberDao;
    this.input = input;
  }
    
  public void execute() {
    try {
      Member member = new Member();
      
      System.out.print("번호 : ");
      String no = input.nextLine();
      
      System.out.print("이름 : ");
      String name = input.nextLine();
      
      System.out.print("이메일 : ");
      String email = input.nextLine();
      
      System.out.print("비밀번호 : ");
      String pw = input.nextLine();
      
      System.out.print("비밀번호 재확인 : ");
      String pwRe = input.nextLine();
      
      member.setNo(Integer.parseInt(no));
      member.setName(name);
      member.setEmail(email);
      member.setPw(pw);
      member.setPwRe(pwRe);
      
      memberDao.insert(member);
      
    } catch (Exception e) {
      System.out.println("멤버 추가 오류 : "+ e.getMessage());
    }
  }
}
