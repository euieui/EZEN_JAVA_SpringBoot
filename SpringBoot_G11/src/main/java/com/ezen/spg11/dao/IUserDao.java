package com.ezen.spg11.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg11.dto.UserDto;

@Mapper
public interface IUserDao {
   
   public List<UserDto> list(); //추상 메서드.어디로 오버라이 되는가?
}
