package com.huang.microboot.service;
import java.util.List;
import com.huang.microboot.vo.Dept;
public interface IDeptService {
	public List<String> list();
	public boolean add(Dept vo);
}
