package com.huang.microboot.dao;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IRoleDAO {
	public Set<String> findAllRoleByMid(String mid); 
}
