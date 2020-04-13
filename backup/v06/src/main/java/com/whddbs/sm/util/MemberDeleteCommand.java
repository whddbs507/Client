package com.whddbs.sm.util;

import java.util.Scanner;
import com.whddbs.sm.dao.MemberDao;

public class MemberDeleteCommand implements Command {

  Scanner input;
  MemberDao memberDao;

  public MemberDeleteCommand(MemberDao memberDao, Scanner input) {
    this.input = input;
    this.memberDao = memberDao;
  }

  public void execute() {
    try {
      System.out.print("삭제할 번호 :");
      int no = input.nextInt();
      input.nextLine();
      
      memberDao.delete(no);
      
    } catch (Exception e) {
      
    }
  }
}
