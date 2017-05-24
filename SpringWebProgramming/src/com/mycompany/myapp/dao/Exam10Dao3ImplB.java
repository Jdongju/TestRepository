package com.mycompany.myapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class Exam10Dao3ImplB implements Exam10Dao3{

	private static final Logger LOGGER=LoggerFactory.getLogger(Exam10Dao3ImplB.class);
	@Override
	public void insert() {
		LOGGER.info("회원 가입");
//		System.out.println("Exam10Dao3Impl- insert()실행");
	}

	@Override
	public void select() {
		LOGGER.info("회원 검색");
//		System.out.println("Exam10Dao1Impl- select()실행");
	}
}
