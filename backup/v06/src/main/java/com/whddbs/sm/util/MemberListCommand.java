package com.whddbs.sm.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Member;

public class MemberListCommand implements Command {

  MemberDao memberDao;
  
  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public void execute() {
    try {
      
      List<Member> memberList = memberDao.findAll();
      
      for (Member member : memberList) {
        System.out.printf("%d %s %s %s\n", member.getNo()
            , member.getName(), member.getEmail(), member.getPw());
      }
    } catch (Exception e) {
      System.out.println("멤버 리스트 호출 오류 : " + e.getMessage());
    }
  }
}
