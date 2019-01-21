package com.digicap.dcblock.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digicap.dcblock.admin.dao.UserDAO;
import com.digicap.dcblock.admin.model.UserDetail;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;
	
	public int getUserTotalPages() {
		int retVal = 0;
		
		int totalPages = userDao.selectUserTotalPages();
		if(totalPages > 0) {
			retVal = (totalPages + 10) / 10;
		}
		
		return retVal;
	}
	
	public int insertUserCheck(UserDetail userDetail) {
		int retVal = 0;
			
			if(userDetail.getEmail() == null || userDetail.getRfid() == null) {
				retVal = -100;
			}else {
				try {
					retVal = userDao.insertUser(userDetail);	
				}catch(Exception e) {
					retVal = -200;
				}
			}
		
		return retVal;
	}
}
