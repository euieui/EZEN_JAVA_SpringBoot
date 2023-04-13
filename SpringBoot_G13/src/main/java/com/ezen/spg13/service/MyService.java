package com.ezen.spg13.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.ezen.spg13.dao.ITransactionDao1;
import com.ezen.spg13.dao.ITransactionDao2;

@Service
public class MyService {
	
	@Autowired
	ITransactionDao1 td1;
	@Autowired
	ITransactionDao2 td2;
	@Autowired
	PlatformTransactionManager ptm;
	@Autowired
	TransactionDefinition td;
	
	public int buy(String id, String amount, String error) {
		
		int n=0;
		
		// 트랜젝션의 시작
		
		
		TransactionStatus status = ptm.getTransaction(td);
		
		try {
			td1.buy(id,amount,error);
			if(error.equals("1")) n = 10 / 0; // 전달된 error 값이 1이면 강제 에러 발생
			td2.buy(id,amount,error);
			System.out.println("에러없이 둘다 싱행되었어요.");
			ptm.commit(status); // 영역 안의 모든 데이터베이스 작업의 실행적용 
			return 1; // 트랜잭션의 끝
		} catch(Exception e){
			//System.out.println("중간에 애러나서 하나만 실행 되었어요.");
			System.out.println("중간에 애러나서 둘다 실행 안됬어요");
			ptm.rollback(status); // 영역안의 모든 데이터 베이스 작업의 취소
			return 0; // 트랜잭션의 끝 (return 있는 곳이 끝임)
			
			// 이거 생각보다 유용하겠어
		}
		
	}
}
