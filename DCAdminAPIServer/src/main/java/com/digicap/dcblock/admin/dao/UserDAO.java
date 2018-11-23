package com.digicap.dcblock.admin.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digicap.dcblock.admin.model.UserDetail;

@Component
public class UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public UserDetail selectUserByRfid(String rfid) {
		return this.sqlSession.selectOne("selectUserByRfid", rfid);
	}
	
	public List<UserDetail> selectUserAll(){
		return this.sqlSession.selectList("selectUserAll");
	}
	
	public UserDetail selectUserByEmail(String email) {
		return this.sqlSession.selectOne("selectUserByEmail", email);
	}
	
	public int insertUser(UserDetail userDetail) {
		return this.sqlSession.insert("insertUser", userDetail);
	}
	
	public List<UserDetail> selectUserPaging(int offset) {
		return this.sqlSession.selectList("selectUserPaging", offset);
	}
	
	public int updateUser(UserDetail userDetail) {
		return this.sqlSession.update("updateUser", userDetail);
	}
	
	public int deleteUser(BigInteger index) {
		return this.sqlSession.delete("deleteUser", index);
	}
}
