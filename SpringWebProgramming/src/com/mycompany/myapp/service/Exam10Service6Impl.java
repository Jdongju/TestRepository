package com.mycompany.myapp.service;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam10Dao3;

@Component
public class Exam10Service6Impl implements Exam10Service6 {

//	@Autowired - Type(Exam10Dao3)을 기준으로 autowired가 된다.따라서 한 interface에 impl이 2개이면 2개가 중복되어 어느것을 실행할지 모르기 때문에 에러.
	@Resource(name="exam10Dao3ImplA")
	private Exam10Dao3 exam10Dao;
	@Override
	public void join() {
		System.out.println("Exam10Service5Impl - join() 실행");
		exam10Dao.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service5Impl - login() 실행");
		exam10Dao.select();
	}
}
