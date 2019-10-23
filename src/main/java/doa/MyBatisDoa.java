package doa;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import doa.mybatisWappers.IPersonWapper;
import domin.Person;

@Repository
@Scope
public class MyBatisDoa implements IMyBatisDoa {
	
	private SqlSessionFactory sessionFactory;
	
	public MyBatisDoa() {
		this.sessionFactory = null;
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	public boolean insertPerson(Person person) {
		// TODO Auto-generated method stub
		try {
			  SqlSession session = sessionFactory.openSession();
			  IPersonWapper mapper = session.getMapper(IPersonWapper.class);
			  boolean res = mapper.insertPerson(person) != 0 ? true : false;
			  session.commit();
			  session.close();
			  return res;
		} catch (Exception ex) {
			
		}
		return false;
	}
	public String check(String name) {
		try {
			  SqlSession session = sessionFactory.openSession();
			  IPersonWapper mapper = session.getMapper(IPersonWapper.class);
			  String result = mapper.getPersonSexByName(name);
			  session.commit();
			  session.close();
		      return result;
		} catch (Exception ex) {
			
		}
		return "";
	}

	public boolean deletePerson(String name) {
		// TODO Auto-generated method stub
		try {
			  SqlSession session = sessionFactory.openSession();
			  IPersonWapper mapper = session.getMapper(IPersonWapper.class);
			  boolean res = mapper.deletePerson(name) != 0 ? true : false;
			  session.commit();
			  session.close();
			  return res; 
		} catch (Exception ex) {
			
		}
		return false;
	}

	public String getPersonSexByName(String name) {
		// TODO Auto-generated method stub
		try {
			  SqlSession session = sessionFactory.openSession();
			  IPersonWapper mapper = session.getMapper(IPersonWapper.class);
			  String res = mapper.getPersonSexByName(name);
			  session.commit();
			  session.close();
			  return res;
		} catch (Exception ex) {
			
		}
		return "err";
	}

	public String setPersonName(String oldName, String newName) {
		// TODO Auto-generated method stub
		try {
			  SqlSession session = sessionFactory.openSession();
			  IPersonWapper mapper = session.getMapper(IPersonWapper.class);
			  String res = mapper.setPersonName(oldName, newName) != 0 ? newName : oldName;
			  session.commit();
			  session.close();
			  return res;
		} catch (Exception ex) {
			
		}
        return "err";
	}

	public String getNameById(Integer id) {
		// TODO Auto-generated method stub
		try {
			  SqlSession session = sessionFactory.openSession();
			  IPersonWapper mapper = session.getMapper(IPersonWapper.class);
			  String res = mapper.getNameById(id);
			  session.commit();
			  session.close();
			  return res;
		} catch (Exception ex) {
			
		}
      return "err";
	}
}
