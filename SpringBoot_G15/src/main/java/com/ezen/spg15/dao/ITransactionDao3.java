package com.ezen.spg15.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITransactionDao3 {

	public void buy(String id, int amount);
}
