package com.whddbs.sm.domain;

import java.io.Serializable;

public class Member implements Serializable {
  
  private static final long serialVersionUID = 20200326L;
  
  private int no;
  private String name;
  private String email;
  private String pw;
  private String pwRe;

  public void setNo(int no) {
    this.no = no;
  }

  public int getNo() {
    return no;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }
  public void setPw(String pw) {
    this.pw = pw;
  }

  public String getPw() {
    return pw;
  }
  
  public void setPwRe(String pwRe) {
    this.pwRe = pwRe;
  }
}