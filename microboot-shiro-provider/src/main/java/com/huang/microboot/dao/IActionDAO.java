package com.huang.microboot.dao;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IActionDAO {
	public Set<String> findAllActionByMid(String mid);
}
