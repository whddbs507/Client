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

  public void processCommand(ObjectOutputStream out, ObjectInputStream in) {
    try {
      BoardDao boardDao = new BoardDaoProxy(out, in);
      MemberDao memberDao = new MemberDaoProxy(out, in);
      
      commandMap.put("/board/add", new BoardAddCommand(boardDao, keyboard));
      commandMap.put("/board/list", new BoardListCommand(boardDao));
      commandMap.put("/board/detail", new BoardDetailCommand(boardDao, keyboard));
      commandMap.put("/board/delete", new BoardDeleteCommand(boardDao, keyboard));
      commandMap.put("/member/add", new MemberAddCommand(memberDao, keyboard));
      commandMap.put("/member/list", new MemberListCommand(memberDao));
      commandMap.put("/member/detail", new MemberDetailCommand(memberDao, keyboard));
      commandMap.put("/member/delete", new MemberDeleteCommand(memberDao, keyboard));
      while (true) {
        System.out.print("명령어 > ");
        String command = keyboard.nextLine();
        if (command.equals("quit")) {
          out.writeUTF(command);
          out.flush();
          
          System.out.println("서버 : " + in.readUTF());
          
          break;
          
        } else if (!command.equals(null)) {
          Command commandHandler = commandMap.get(command);
          commandHandler.execute();
        }
      }
    } catch (Exception e) {
      System.out.println("실행 오류 : " + e.getMessage());
    }
  }

  public void service() {
    try (Socket socket = new Socket("localhost", 7777);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      
      processCommand(out, in);
      
    } catch (Exception e) {
      System.out.println("소켓 오류 : " + e.getMessage());
    }

  }

  public static void main(String[] args) {
    System.out.println("[클라이언트] 접속");

    ClientApp app = new ClientApp();

    app.service();

  }
}