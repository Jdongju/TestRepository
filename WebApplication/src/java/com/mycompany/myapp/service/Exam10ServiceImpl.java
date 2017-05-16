package com.mycompany.myapp.service;

import com.mycompany.myapp.dao.Exam10Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Exam10ServiceImpl implements Exam10Service {
	@Autowired
	private Exam10Dao exam10dao;
	public void join() {
		System.out.println("join() 실행");
		exam10dao.insert();
	}

	public void login() {
		System.out.println("login() 실행");
		exam10dao.select();
	}

}
