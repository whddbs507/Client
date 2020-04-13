package com.whddbs.sm;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

  public static void main(String[] args) throws Exception {
    System.out.println("[서버]관리시스템");
    
    Socket socket = new Socket("localhost", 7777);
    
    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());
    
    out.println("안녕하세여 서버님");
    
    String str = in.nextLine();
    System.out.printf("Server : %s\n", str);
  }
}
