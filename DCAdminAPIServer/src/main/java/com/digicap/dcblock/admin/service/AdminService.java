package com.digicap.dcblock.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digicap.dcblock.admin.dao.AdminDAO;
import com.digicap.dcblock.admin.model.AdminDetail;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO adminDao;
	
	public int checkLogin(AdminDetail adminDetail) {
		int retVal = 0;
		AdminDetail checkAdminInfo = new AdminDetail();
		checkAdminInfo = adminDao.selectAdminById(adminDetail.getId());
		
		if(checkAdminInfo == null) {
			retVal = 0;
		} else {
			if(adminDetail.getPassword().equals(checkAdminInfo.getPassword())) {
				retVal = 1;
			}else {
				retVal = 0;
			}
		}
		
		return retVal;
	}
}
