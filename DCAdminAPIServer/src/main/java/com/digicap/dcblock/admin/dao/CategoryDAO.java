package com.digicap.dcblock.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digicap.dcblock.admin.model.CategoryDetail;

@Deprecated
@Component
public class CategoryDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryDetail> selectAllCategory(){
		return this.sqlSession.selectList("selectAllCategory");
	}
	
	public int insertCategory(CategoryDetail categoryDetail) {
		return this.sqlSession.insert("insertCategory", categoryDetail);
	}
	
	public CategoryDetail selectCategoryByCode(int code) {
		return this.sqlSession.selectOne("selectCategoryByCode", code);
	}
	
	public int updateCategory(CategoryDetail categoryDetail) {
		return this.sqlSession.update("updateCategory", categoryDetail);
	}

}
