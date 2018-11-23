package com.digicap.dcblock.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digicap.dcblock.admin.dao.CategoryDAO;
import com.digicap.dcblock.admin.model.Category;
import com.digicap.dcblock.admin.model.CategoryDetail;

@Deprecated
@Service
public class CategoryService {

	@Autowired
	private CategoryDAO categoryDao;

	public int modifyCategory(Category category) {
		int retVal = 0;
		
		List<CategoryDetail> categoryDetailList = category.getCategoryDetail();
		for(CategoryDetail categoryDetail : categoryDetailList) {
			
			CategoryDetail categoryDetailByCode = categoryDao.selectCategoryByCode(categoryDetail.getCode());
			
			if(categoryDetailByCode != null) {
				retVal = categoryDao.updateCategory(categoryDetail);
			} else {
				retVal = categoryDao.insertCategory(categoryDetail);
			}
		}
		
		return retVal;
	}

}
