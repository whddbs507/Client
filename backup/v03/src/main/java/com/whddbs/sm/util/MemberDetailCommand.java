package com.whddbs.sm.util;

import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Member;

public class MemberDetailCommand implements Command {

  MemberDao memberDao; 
  Scanner input;

  public MemberDetailCommand(MemberDao memberDao, Scanner input) {
    this.input = input;
    this.memberDao = memberDao;
  }

  public void execute() {
    try {
      System.out.print("확인할 번호 : ");
      int no = input.nextInt();
      input.nextLine();

      Member member = memberDao.findByNo(no);

      System.out.println("번호 : " + member.getNo());
      System.out.println("이름 : " + member.getName());
      System.out.println("이메일 : " + member.getEmail());
      System.out.println("비밀번호 : " + member.getPw());
    } catch (Exception e) {

    }
  }
}