package com.sun.amr.dao.abs;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public abstract class AbstractDAO {
	@Resource
	private SqlSessionFactory factory;
	public SqlSessionFactory getFactory() {
		return factory;
	}
	public SqlSession getSession() {
		return this.factory.openSession();
	}
}
