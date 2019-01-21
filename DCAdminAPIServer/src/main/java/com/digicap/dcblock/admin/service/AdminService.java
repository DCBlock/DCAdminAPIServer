package com.digicap.dcblock.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digicap.dcblock.admin.dao.AdminDAO;
import com.digicap.dcblock.admin.model.AdminDetail;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO adminDao;
	
	public HashMap<String, Object> checkLogin(AdminDetail adminDetail) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String> grantList = new ArrayList<String>();

		AdminDetail checkAdminInfo = new AdminDetail();
		checkAdminInfo = adminDao.selectAdminById(adminDetail.getId());
		
		if(checkAdminInfo == null) {
			map = null;
		} else {
			if(adminDetail.getPassword().equals(checkAdminInfo.getPassword())) {
				
				/* Deprecated
				if(checkAdminInfo.getPermission() == 1000) {
					map.put("permission", "ADMIN");	
				} else if(checkAdminInfo.getPermission() == 2000) {
					map.put("permission", "OPERATOR");
				} else if(checkAdminInfo.getPermission() == 3000) {
					map.put("permission", "USER");
				} else {
					map.put("permission", "Unverified Roles");
				}
				*/
				map.put("scope", checkAdminInfo.getScope());
				map.put("company", checkAdminInfo.getCompany());
				
				if(checkAdminInfo.isManageuser())
					grantList.add("ROLE_MANAGEUSER");
				
				if(checkAdminInfo.isManagemenu())
					grantList.add("ROLE_MANAGEMENT");
				
				if(checkAdminInfo.isViewstatistics())
					grantList.add("ROLE_VIEWSTATISTICS");
				
				if(checkAdminInfo.isManageadmin())
					grantList.add("ROLE_MANAGEADMIN");
				
				map.put("grant", grantList);
				
			}else {
				map = null;
			}
		}
		
		return map;
	}
	
	public int getAdminTotalPages() {
		int retVal = 0;
		
		int totalPages = adminDao.selectAdminTotalPages();
		if(totalPages > 0) {
			retVal = (totalPages + 10) / 10;
		}
		
		return retVal;
	}
}
