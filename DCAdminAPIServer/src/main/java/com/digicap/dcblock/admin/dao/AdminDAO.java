package com.digicap.dcblock.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digicap.dcblock.admin.model.AdminDetail;

@Component
public class AdminDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public AdminDetail selectAdminById(String id) {
		return this.sqlSession.selectOne("selectAdminById", id);
	}
	
	public AdminDetail selectAdminLogin(AdminDetail adminDetail) {
		return this.sqlSession.selectOne("selectAdminLogin", adminDetail);
	}
		
	public List<AdminDetail> selectAdminAll() {
		return this.sqlSession.selectList("selectAdminAll");
	}
	
	public int insertAdmin(AdminDetail adminDetail) {
		return this.sqlSession.insert("insertAdmin", adminDetail);
	}
	
	public int updateAdmin(AdminDetail adminDetail) {
		return this.sqlSession.insert("updateAdmin", adminDetail);
	}
	
	public int deleteAdmin(String id) {
		return this.sqlSession.delete("deleteAdmin", id);
	}
	
	public int selectAdminTotalPages() {
		return this.sqlSession.selectOne("selectAdminTotalPages");
	}
}
