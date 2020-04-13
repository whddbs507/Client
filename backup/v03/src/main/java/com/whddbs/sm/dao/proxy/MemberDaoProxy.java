package com.whddbs.sm.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Member;

public class MemberDaoProxy implements MemberDao {

  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberDaoProxy(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  public int insert(Member member) throws Exception {
    out.writeUTF("/member/add");

    out.writeObject(member);
    System.out.println(in.readUTF());

    return 1;  
  }

  public int delete(int no) throws Exception {
    out.writeUTF("/member/delete");

    out.writeInt(no);
    out.flush();
    
    String response = in.readUTF();
    
    if (response.equals("OK")) {
    } else {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  public List<Member> findAll() throws Exception {
    out.writeUTF("/member/list");
    out.writeObject("d");

    System.out.println(in.readUTF());

    return (List<Member>) in.readObject();
  }

  public Member findByNo(int no) throws Exception {
    out.writeUTF("/member/detail");

    out.writeInt(no);
    out.flush();

    String respond = in.readUTF();

    if (respond.equals("OK")) {
      return (Member) in.readObject();
    } else {
      System.out.println(in.readUTF());
      return null;
    }
  }
}