package com.huang.microboot.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.huang.microboot.vo.Dept;
@Mapper
public interface IDeptDAO {
	public List<String> findAll();
	public int doCreate(Dept vo);
}
