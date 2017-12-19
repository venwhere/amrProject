package com.huang.microboot.dao;
import org.apache.ibatis.annotations.Mapper;
import com.huang.microboot.vo.Member;
@Mapper
public interface IMemberDAO {
	public Member findById(String mid);
}
