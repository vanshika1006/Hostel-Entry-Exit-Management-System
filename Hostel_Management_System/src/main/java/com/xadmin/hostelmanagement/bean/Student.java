package com.xadmin.hostelmanagement.bean;

import java.sql.Timestamp;

public class Student {
	int id;
	Timestamp time_in;
	Timestamp time_out;

	public Student(int id, Timestamp time_in, Timestamp time_out) {
		super();
		this.id = id;
		this.time_in = time_in;
		this.time_out = time_out;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getTime_in() {
		return time_in;
	}
	public void setTime_in(Timestamp time_in) {
		this.time_in = time_in;
	}
	public Timestamp getTime_out() {
		return time_out;
	}
	public void setTime_out(Timestamp time_out) {
		this.time_out = time_out;
	}

}
