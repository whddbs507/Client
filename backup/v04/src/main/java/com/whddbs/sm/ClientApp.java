package com.whddbs.sm;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.dao.proxy.BoardDaoProxy;
import com.whddbs.sm.dao.proxy.DaoProxyHelper;
import com.whddbs.sm.dao.proxy.MemberDaoProxy;
import com.whddbs.sm.util.BoardAddCommand;
import com.whddbs.sm.util.BoardDeleteCommand;
import com.whddbs.sm.util.BoardDetailCommand;
import com.whddbs.sm.util.BoardListCommand;
import com.whddbs.sm.util.Command;
import com.whddbs.sm.util.MemberAddCommand;
import com.whddbs.sm.util.MemberDeleteCommand;
import com.whddbs.sm.util.MemberDetailCommand;
import com.whddbs.sm.util.MemberListCommand;

public class ClientApp {

  static Scanner keyboard = new Scanner(System.in);
  static Map<String, Command> commandMap = new HashMap<>();

  public ClientApp() {
    String host = "localhost";
    int port = 7777;
    
    DaoProxyHelper daoProxyHelper = new DaoProxyHelper(host, port);
    
    BoardDao boardDao = new BoardDaoProxy(daoProxyHelper);
    MemberDao memberDao = new MemberDaoProxy(daoProxyHelper);

    commandMap.put("/board/add", new BoardAddCommand(boardDao, keyboard));
    commandMap.put("/board/list", new BoardListCommand(boardDao));
    commandMap.put("/board/detail", new BoardDetailCommand(boardDao, keyboard));
    commandMap.put("/board/delete", new BoardDeleteCommand(boardDao, keyboard));
    commandMap.put("/member/add", new MemberAddCommand(memberDao, keyboard));
    commandMap.put("/member/list", new MemberListCommand(memberDao));
    commandMap.put("/member/detail", new MemberDetailCommand(memberDao, keyboard));
    commandMap.put("/member/delete", new MemberDeleteCommand(memberDao, keyboard));
  }

  public void service() {
    while (true) {
      System.out.print("명령어 > ");
      String command = keyboard.nextLine();

      if (command.equals("quit")) {
        break;
      }
      processCommand(command);
    }
  }

  public void processCommand(String command) {

    if (command != null) {
      Command commandHandler = commandMap.get(command);
      commandHandler.execute();
    }
  }

  public static void main(String[] args) {
    System.out.println("[클라이언트] 접속");

    ClientApp app = new ClientApp();

    app.service();
  }
}