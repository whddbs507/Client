package com.whddbs.sm.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.whddbs.sm.dao.BoardDao;
import com.whddbs.sm.domain.Board;

public class BoardDaoProxy implements BoardDao {

  DaoProxyHelper daoProxyHelper;

  public BoardDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Board board) throws Exception {

    class InsertWorker implements Worker {

      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out)  throws Exception {
        int no = 1;

        out.writeUTF("/board/add");
        out.flush();

        List<Board> boardList = (List<Board>) in.readObject();

        for (int i = 0; i < boardList.size(); i++) {
          int no2 = boardList.get(i).getNo();
          no = no2 + 1;
        }

        board.setNo(no);

        out.writeObject(board);

        System.out.println(in.readUTF());
        return 1;
      }
    }

    InsertWorker worker = new InsertWorker();
    int resultState = (int) daoProxyHelper.request(worker);
    return resultState;
  }

  public int delete(int no) throws Exception {
    Worker worker = new Worker() {

      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {

        out.writeUTF("/board/delete");

        out.writeInt(no);
        out.flush(); 

        System.out.println(in.readUTF());

        return 1;
      }
    };

    int result = (int)daoProxyHelper.request(worker);
    return result;
  }


  public List<Board> findAll() throws Exception {
    return (List<Board>)daoProxyHelper.request((in, out) -> {
      out.writeUTF("/board/list");
      out.flush();
      System.out.println(in.readUTF());

      return (List<Board>) in.readObject();
    });
  }


  public Board findByNo(int no) throws Exception {
    return (Board) daoProxyHelper.request((in, out) -> {
      
      out.writeUTF("/board/detail");
      
      out.writeInt(no);
      out.flush();
      String str = in.readUTF();
      
      if (str.equals("OK")) {
        return (Board)in.readObject();
      } else {
        throw new Exception(in.readUTF());
      }
    });
    
  }
}
