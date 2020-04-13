package com.whddbs.sm.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.domain.Board;

public class BoardDaoProxy implements BoardDao {

  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardDaoProxy(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  public int insert(Board board) throws Exception {
    int no = 0;

    out.writeUTF("/board/add");
    out.flush();

    List<Board> boardList = (List<Board>) in.readObject();

    out.writeObject(board);

    System.out.println(in.readUTF());

    return no;
  }


  public int delete(int no) throws Exception {
    out.writeUTF("/board/delete");

    out.writeInt(no);
    out.flush(); 

    System.out.println(in.readUTF());

    return 1;
  }

  public List<Board> findAll() throws Exception {
    out.writeUTF("/board/list");
    out.flush();
    System.out.println(in.readUTF());

    return (List<Board>) in.readObject();
  }

  public Board findByNo(int no) throws Exception {
    out.writeUTF("/board/detail");

    out.writeInt(no);
    out.flush();
    String str = in.readUTF();

    if (str.equals("OK")) {
      return (Board)in.readObject();
    } else {
      throw new Exception(in.readUTF());
    }
  }
}
