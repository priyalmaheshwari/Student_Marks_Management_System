package com.maheshwari.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentPassword {
	@Id
	private int id;
	private String pass;
//	public StudentPassword(int id, String pass) {
//		super();
//		this.id = id;
//		this.pass = pass;
//	}
//	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "StudentPassword [id=" + id + ", pass=" + pass + "]";
	}
}
