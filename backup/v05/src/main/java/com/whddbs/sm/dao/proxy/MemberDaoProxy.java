package com.whddbs.sm.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.whddbs.sm.dao.MemberDao;
import com.whddbs.sm.domain.Member;

public class MemberDaoProxy implements MemberDao {

  DaoProxyHelper daoProxyHelper;
  
  public MemberDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  public int insert(Member member) throws Exception {
    return (int)daoProxyHelper.request((in, out) -> {
      
      out.writeUTF("/member/add");
      
      out.writeObject(member);
      System.out.println(in.readUTF());
      
      return 1;  
    });
  }

  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/delete");
      
      out.writeInt(no);
      out.flush();
      
      String response = in.readUTF();
      
      if (response.equals("OK")) {
      } else {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  public List<Member> findAll() throws Exception {
    return (List<Member>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/list");
      out.writeObject("d");
      
      System.out.println(in.readUTF());
      
      return (List<Member>) in.readObject();
    });
  }

  public Member findByNo(int no) throws Exception {
    return (Member)daoProxyHelper.request((in, out) -> {
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
    });
  }
}