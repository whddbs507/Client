package com.whddbs.sm;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

  static Scanner keyboard = new Scanner(System.in);

  public ClientApp() throws Exception {
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
    String host = null;
    int port = 7777;
    String servletPath = null;
    
    try {
      
      if (!command.startsWith("bitcamp://")) {
        throw new Exception("명령어가 옳지 않습니다.");
      }
      
      String url = command.substring(10);
      int index = url.indexOf("/");
      
      String[] str = url.substring(0, index).split(":");
      host = str[0];
      
      if (str.length == 2) {
        port = Integer.parseInt(str[1]);
      }
     
      servletPath = url.substring(index);
    } catch (Exception e) {
      System.out.println("processCommand 오류 : " + e.getMessage());
    }
    
    try (Socket socket = new Socket(host, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream()))
    {
      out.println(servletPath);
      out.flush();
      
      while (true) {
        String response = in.nextLine();
        if (response.equals("!end!")) {
          break;
        }
        System.out.println(response);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("[클라이언트] 접속");

    ClientApp app = new ClientApp();

    app.service();
  }
}