package com.ezen.spg12.dao;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg12.dto.BbsDto;

@Mapper
public interface IBbsDao {

	public List<BbsDto> list();
	public BbsDto view(int Id);
	// public void write(String writer, String title, String content);
	public void write(@Valid BbsDto bbsdto);
	public void update(@Valid BbsDto bbsdto);
	public void delete(int id);
	
	
	
}
