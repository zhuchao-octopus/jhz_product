package com.cy.service.user;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.dao.user.PermissionDao;
import com.cy.domain.line.JhzRole;
import com.cy.domain.line.LevelOne;
import com.cy.domain.line.TreeDemo;
import com.cy.utils.CyResult;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionDao permissionDao;

	@Override
	public CyResult loadAllPermission() {
		CyResult result = new CyResult();
		List<LevelOne> list = permissionDao.loadAllPermission();
		TreeDemo demo = new TreeDemo();
		demo.setTrees(list);
		result.setData(demo);
		result.setCode(0);
		result.setMsg("加载成功!");
		return result;
	}

	@Override
	public CyResult loadPermissionById(int kid) {
		CyResult result = new CyResult();
		List<LevelOne> allPermissionList = permissionDao.loadAllPermission();
		System.out.println("allPermissionList"+allPermissionList);
		JhzRole role = permissionDao.findPermissionByKid(kid);
		String permissionArr = role.getPermissionStr();

		String[] per = null;
		if (permissionArr != null) {
			try {
				per = permissionArr.split(",");
				System.out.println("per:" + Arrays.toString(per));
				for (String perm : per) {
					System.out.println("kk:" + perm);
					if ("jgb_seeDetails".equals(perm)) {
						allPermissionList.get(0).setChecked(true);
						allPermissionList.get(0).getList().get(0).setChecked(true);
						allPermissionList.get(0).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("jgb_edit".equals(perm)) {
						allPermissionList.get(0).setChecked(true);
						allPermissionList.get(0).getList().get(0).setChecked(true);
						allPermissionList.get(0).getList().get(0).getList().get(1).setChecked(true);
					}

					if ("ygb_seeDetails".equals(perm)) {
						allPermissionList.get(0).setChecked(true);
						allPermissionList.get(0).getList().get(1).setChecked(true);
						allPermissionList.get(0).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("ygb_edit".equals(perm)) {
						allPermissionList.get(0).setChecked(true);
						allPermissionList.get(0).getList().get(1).setChecked(true);
						allPermissionList.get(0).getList().get(1).getList().get(1).setChecked(true);
					}
					
					if ("LunBan_seeDetails".equals(perm)) {
						allPermissionList.get(0).setChecked(true);
						allPermissionList.get(0).getList().get(2).setChecked(true);
						allPermissionList.get(0).getList().get(2).getList().get(0).setChecked(true);
					}
					if ("LunBan_edit".equals(perm)) {
						allPermissionList.get(0).setChecked(true);
						allPermissionList.get(0).getList().get(2).setChecked(true);
						allPermissionList.get(0).getList().get(2).getList().get(1).setChecked(true);
					}
					
					if ("KaoQin_seeDetails".equals(perm)) {
						allPermissionList.get(0).setChecked(true);
						allPermissionList.get(0).getList().get(3).setChecked(true);
						allPermissionList.get(0).getList().get(3).getList().get(0).setChecked(true);
					}
					if ("KaoQin_edit".equals(perm)) {
						allPermissionList.get(0).setChecked(true);
						allPermissionList.get(0).getList().get(3).setChecked(true);
						allPermissionList.get(0).getList().get(3).getList().get(1).setChecked(true);
					}
				

					if ("cxb_seeDetails".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(0).setChecked(true);
						allPermissionList.get(1).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("cxb_edit".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(0).setChecked(true);
						allPermissionList.get(1).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("gwb_seeDetails".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(1).setChecked(true);
						allPermissionList.get(1).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("gwb_edit".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(1).setChecked(true);
						allPermissionList.get(1).getList().get(1).getList().get(1).setChecked(true);
					}
					
					if ("mbb_seeDetails".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(2).setChecked(true);
						allPermissionList.get(1).getList().get(2).getList().get(0).setChecked(true);
					}
					if ("mbb_edit".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(2).setChecked(true);
						allPermissionList.get(1).getList().get(2).getList().get(1).setChecked(true);
					}
					
					if ("gnb_seeDetails".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(3).setChecked(true);
						allPermissionList.get(1).getList().get(3).getList().get(0).setChecked(true);
					}
					if ("gnb_edit".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(3).setChecked(true);
						allPermissionList.get(1).getList().get(3).getList().get(1).setChecked(true);
					}
					if ("scb_seeDetails".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(4).setChecked(true);
						allPermissionList.get(1).getList().get(4).getList().get(0).setChecked(true);
					}
					if ("scb_edit".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(4).setChecked(true);
						allPermissionList.get(1).getList().get(4).getList().get(1).setChecked(true);
					}
					if ("csb1_seeDetails".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(5).setChecked(true);
						allPermissionList.get(1).getList().get(5).getList().get(0).setChecked(true);
					}
					if ("csb1_edit".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(5).setChecked(true);
						allPermissionList.get(1).getList().get(5).getList().get(1).setChecked(true);
					}
					if ("lhb_seeDetails".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(6).setChecked(true);
						allPermissionList.get(1).getList().get(6).getList().get(0).setChecked(true);
					}
					if ("lhb_edit".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(6).setChecked(true);
						allPermissionList.get(1).getList().get(6).getList().get(1).setChecked(true);
					}
					if ("wxb_seeDetails".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(7).setChecked(true);
						allPermissionList.get(1).getList().get(7).getList().get(0).setChecked(true);
					}
					if ("wxb_edit".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(7).setChecked(true);
						allPermissionList.get(1).getList().get(7).getList().get(1).setChecked(true);
					}
					if ("trb_seeDetails".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(8).setChecked(true);
						allPermissionList.get(1).getList().get(8).getList().get(0).setChecked(true);
					}
					if ("trb_edit".equals(perm)) {
						allPermissionList.get(1).setChecked(true);
						allPermissionList.get(1).getList().get(8).setChecked(true);
						allPermissionList.get(1).getList().get(8).getList().get(1).setChecked(true);
					}
					if ("xsb_seeDetails".equals(perm)) {
						allPermissionList.get(2).setChecked(true);
						allPermissionList.get(2).getList().get(0).setChecked(true);
						allPermissionList.get(2).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("xsb_edit".equals(perm)) {
						allPermissionList.get(2).setChecked(true);
						allPermissionList.get(2).getList().get(0).setChecked(true);
						allPermissionList.get(2).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("yxb_seeDetails".equals(perm)) {
						allPermissionList.get(2).setChecked(true);
						allPermissionList.get(2).getList().get(1).setChecked(true);
						allPermissionList.get(2).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("yxb_edit".equals(perm)) {
						allPermissionList.get(2).setChecked(true);
						allPermissionList.get(2).getList().get(1).setChecked(true);
						allPermissionList.get(2).getList().get(1).getList().get(1).setChecked(true);
					}
					if ("gsb_seeDetails".equals(perm)) {
						allPermissionList.get(2).setChecked(true);
						allPermissionList.get(2).getList().get(2).setChecked(true);
						allPermissionList.get(2).getList().get(2).getList().get(0).setChecked(true);
					}
					if ("gsb_edit".equals(perm)) {
						allPermissionList.get(2).setChecked(true);
						allPermissionList.get(2).getList().get(2).setChecked(true);
						allPermissionList.get(2).getList().get(2).getList().get(1).setChecked(true);
					}
					
					if ("xianmub_seeDetails".equals(perm)) {
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(0).setChecked(true);
						allPermissionList.get(3).getList().get(0).getList().get(0).setChecked(true);
					}
					if("xiangmub_edit".equals(perm)){
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(0).setChecked(true);
						allPermissionList.get(3).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("renwub_seeDetails".equals(perm)) {
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(1).setChecked(true);
						allPermissionList.get(3).getList().get(1).getList().get(0).setChecked(true);
					}
					if("renwub_edit".equals(perm)){
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(1).setChecked(true);
						allPermissionList.get(3).getList().get(1).getList().get(1).setChecked(true);
					}
					if ("jifengb_seeDetails".equals(perm)) {
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(2).setChecked(true);
						allPermissionList.get(3).getList().get(2).getList().get(0).setChecked(true);
					}
					if("jifengb_edit".equals(perm)){
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(2).setChecked(true);
						allPermissionList.get(3).getList().get(2).getList().get(1).setChecked(true);
					}
					if ("xiangmuzb_seeDetails".equals(perm)) {
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(3).setChecked(true);
						allPermissionList.get(3).getList().get(3).getList().get(0).setChecked(true);
					}
					if("xiangmuzb_edit".equals(perm)){
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(3).setChecked(true);
						allPermissionList.get(3).getList().get(3).getList().get(1).setChecked(true);
					}
					if ("zuyuanb_seeDetails".equals(perm)) {
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(4).setChecked(true);
						allPermissionList.get(3).getList().get(4).getList().get(0).setChecked(true);
					}
					if("zuyuanb_edit".equals(perm)){
						allPermissionList.get(3).setChecked(true);
						allPermissionList.get(3).getList().get(4).setChecked(true);
						allPermissionList.get(3).getList().get(4).getList().get(1).setChecked(true);
					}
					
					
					if ("dzc_seeDetails".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(0).setChecked(true);
						allPermissionList.get(4).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("dzc_edit".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(0).setChecked(true);
						allPermissionList.get(4).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("dzc_approval".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(0).setChecked(true);
						allPermissionList.get(4).getList().get(0).getList().get(2).setChecked(true);
					}
					if ("jgc_seeDetails".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(1).setChecked(true);
						allPermissionList.get(4).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("jgc_edit".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(1).setChecked(true);
						allPermissionList.get(4).getList().get(1).getList().get(1).setChecked(true);
					}
					if ("jgc_approval".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(1).setChecked(true);
						allPermissionList.get(4).getList().get(1).getList().get(2).setChecked(true);
					}
					if ("cpc_seeDetails".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(2).setChecked(true);
						allPermissionList.get(4).getList().get(2).getList().get(0).setChecked(true);
					}
					if ("cpc_edit".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(2).setChecked(true);
						allPermissionList.get(4).getList().get(2).getList().get(1).setChecked(true);
					}
					if ("cpc_approval".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(2).setChecked(true);
						allPermissionList.get(4).getList().get(2).getList().get(2).setChecked(true);
					}
					if ("bkc_seeDetails".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(3).setChecked(true);
						allPermissionList.get(4).getList().get(3).getList().get(0).setChecked(true);
					}
					if ("bkc_edit".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(3).setChecked(true);
						allPermissionList.get(4).getList().get(3).getList().get(1).setChecked(true);
					}
					if ("bkc_approval".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(3).setChecked(true);
						allPermissionList.get(4).getList().get(3).getList().get(2).setChecked(true);
					}

					if ("gzc_seeDetails".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(4).setChecked(true);
						allPermissionList.get(4).getList().get(4).getList().get(0).setChecked(true);
					}
					if ("gzc_edit".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(4).setChecked(true);
						allPermissionList.get(4).getList().get(4).getList().get(1).setChecked(true);
					}
					if ("gzc_approval".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(4).setChecked(true);
						allPermissionList.get(4).getList().get(4).getList().get(2).setChecked(true);
					}
					if ("plc_seeDetails".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(5).setChecked(true);
						allPermissionList.get(4).getList().get(5).getList().get(0).setChecked(true);
					}
					if ("plc_edit".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(5).setChecked(true);
						allPermissionList.get(4).getList().get(5).getList().get(1).setChecked(true);
					}
					if ("plc_approval".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(5).setChecked(true);
						allPermissionList.get(4).getList().get(5).getList().get(2).setChecked(true);
					}
					if ("zfc_seeDetails".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(6).setChecked(true);
						allPermissionList.get(4).getList().get(6).getList().get(0).setChecked(true);
					}
					if ("zfc_edit".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(6).setChecked(true);
						allPermissionList.get(4).getList().get(6).getList().get(1).setChecked(true);
					}
					if ("zfc_approval".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(6).setChecked(true);
						allPermissionList.get(4).getList().get(6).getList().get(2).setChecked(true);
					}
					if ("rjc_seeDetails".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(7).setChecked(true);
						allPermissionList.get(4).getList().get(7).getList().get(0).setChecked(true);
					}
					if ("rjc_edit".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(7).setChecked(true);
						allPermissionList.get(4).getList().get(7).getList().get(1).setChecked(true);
					}
					if ("rjc_approval".equals(perm)) {
						allPermissionList.get(4).setChecked(true);
						allPermissionList.get(4).getList().get(7).setChecked(true);
						allPermissionList.get(4).getList().get(7).getList().get(2).setChecked(true);
					}
					
					
					
					if ("jgb1_seeDetails".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(0).setChecked(true);
						allPermissionList.get(5).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("jgb1_edit".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(0).setChecked(true);
						allPermissionList.get(5).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("jdb_seeDetails".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(1).setChecked(true);
						allPermissionList.get(5).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("jdb_edit".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(1).setChecked(true);
						allPermissionList.get(5).getList().get(1).getList().get(1).setChecked(true);
					}
					if ("cgb_seeDetails".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(2).setChecked(true);
						allPermissionList.get(5).getList().get(2).getList().get(0).setChecked(true);
					}
					if ("cgb_edit".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(2).setChecked(true);
						allPermissionList.get(5).getList().get(2).getList().get(1).setChecked(true);
					}
					if ("cbb_seeDetails".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(3).setChecked(true);
						allPermissionList.get(5).getList().get(3).getList().get(0).setChecked(true);
					}
					if ("cbb_edit".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(3).setChecked(true);
						allPermissionList.get(5).getList().get(3).getList().get(1).setChecked(true);
					}
					if ("csb_seeDetails".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(4).setChecked(true);
						allPermissionList.get(5).getList().get(4).getList().get(0).setChecked(true);
					}
					if ("csb_edit".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(4).setChecked(true);
						allPermissionList.get(5).getList().get(4).getList().get(1).setChecked(true);
					}
					if ("yqb_seeDetails".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(5).setChecked(true);
						allPermissionList.get(5).getList().get(5).getList().get(0).setChecked(true);
					}
					if ("yqb_edit".equals(perm)) {
						allPermissionList.get(5).setChecked(true);
						allPermissionList.get(5).getList().get(5).setChecked(true);
						allPermissionList.get(5).getList().get(5).getList().get(1).setChecked(true);
					}

					if ("ddb_seeDetails".equals(perm)) {
						allPermissionList.get(6).setChecked(true);
						allPermissionList.get(6).getList().get(0).setChecked(true);
						allPermissionList.get(6).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("ddb_edit".equals(perm)) {
						allPermissionList.get(6).setChecked(true);
						allPermissionList.get(6).getList().get(0).setChecked(true);
						allPermissionList.get(6).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("qlb_seeDetails".equals(perm)) {
						allPermissionList.get(6).setChecked(true);
						allPermissionList.get(6).getList().get(1).setChecked(true);
						allPermissionList.get(6).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("qlb_edit".equals(perm)) {
						allPermissionList.get(6).setChecked(true);
						allPermissionList.get(6).getList().get(1).setChecked(true);
						allPermissionList.get(6).getList().get(1).getList().get(1).setChecked(true);
					}
					if ("jdb1_seeDetails".equals(perm)) {
						allPermissionList.get(6).setChecked(true);
						allPermissionList.get(6).getList().get(2).setChecked(true);
						allPermissionList.get(6).getList().get(2).getList().get(0).setChecked(true);
					}
					if ("jdb1_edit".equals(perm)) {
						allPermissionList.get(6).setChecked(true);
						allPermissionList.get(6).getList().get(2).setChecked(true);
						allPermissionList.get(6).getList().get(2).getList().get(1).setChecked(true);
					}
					if ("paidanb_seeDetails".equals(perm)) {
						allPermissionList.get(6).setChecked(true);
						allPermissionList.get(6).getList().get(3).setChecked(true);
						allPermissionList.get(6).getList().get(3).getList().get(0).setChecked(true);
					}
					if ("paidanb_edit".equals(perm)) {
						allPermissionList.get(6).setChecked(true);
						allPermissionList.get(6).getList().get(3).setChecked(true);
						allPermissionList.get(6).getList().get(3).getList().get(1).setChecked(true);
					}
					
					if ("cpb_seeDetails".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(0).setChecked(true);
						allPermissionList.get(7).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("cpb_edit".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(0).setChecked(true);
						allPermissionList.get(7).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("gzb_seeDetails".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(1).setChecked(true);
						allPermissionList.get(7).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("gzb_edit".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(1).setChecked(true);
						allPermissionList.get(7).getList().get(1).getList().get(1).setChecked(true);
					}
					if ("gzz_seeDetails".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(2).setChecked(true);
						allPermissionList.get(7).getList().get(2).getList().get(0).setChecked(true);
					}
					if ("gzz_edit".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(2).setChecked(true);
						allPermissionList.get(7).getList().get(2).getList().get(1).setChecked(true);
					}
					if ("tmb_seeDetails".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(3).setChecked(true);
						allPermissionList.get(7).getList().get(3).getList().get(0).setChecked(true);
					}
					if ("tmb_edit".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(3).setChecked(true);
						allPermissionList.get(7).getList().get(3).getList().get(1).setChecked(true);
					}
					if ("lhb1_seeDetails".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(4).setChecked(true);
						allPermissionList.get(7).getList().get(4).getList().get(0).setChecked(true);
					}
					if ("lhb1_edit".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(4).setChecked(true);
						allPermissionList.get(7).getList().get(4).getList().get(1).setChecked(true);
					}
					if ("bomb_seeDetails".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(5).setChecked(true);
						allPermissionList.get(7).getList().get(5).getList().get(0).setChecked(true);
					}
					if ("bomb_edit".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(5).setChecked(true);
						allPermissionList.get(7).getList().get(5).getList().get(1).setChecked(true);
					}
					if ("bmb_seeDetails".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(6).setChecked(true);
						allPermissionList.get(7).getList().get(6).getList().get(0).setChecked(true);
					}
					if ("bmb_edit".equals(perm)) {
						allPermissionList.get(7).setChecked(true);
						allPermissionList.get(7).getList().get(6).setChecked(true);
						allPermissionList.get(7).getList().get(6).getList().get(1).setChecked(true);
					}
					if ("iqc_seeDetails".equals(perm)) {
						allPermissionList.get(8).setChecked(true);
						allPermissionList.get(8).getList().get(0).setChecked(true);
						allPermissionList.get(8).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("iqc_edit".equals(perm)) {
						allPermissionList.get(8).setChecked(true);
						allPermissionList.get(8).getList().get(0).setChecked(true);
						allPermissionList.get(8).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("ipqc_seeDetails".equals(perm)) {
						allPermissionList.get(8).setChecked(true);
						allPermissionList.get(8).getList().get(1).setChecked(true);
						allPermissionList.get(8).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("ipqc_edit".equals(perm)) {
						allPermissionList.get(8).setChecked(true);
						allPermissionList.get(8).getList().get(1).setChecked(true);
						allPermissionList.get(8).getList().get(1).getList().get(1).setChecked(true);
					}
					if ("oqcb_seeDetails".equals(perm)) {
						allPermissionList.get(8).setChecked(true);
						allPermissionList.get(8).getList().get(2).setChecked(true);
						allPermissionList.get(8).getList().get(2).getList().get(0).setChecked(true);
					}
					if ("oqcb_edit".equals(perm)) {
						allPermissionList.get(8).setChecked(true);
						allPermissionList.get(8).getList().get(2).setChecked(true);
						allPermissionList.get(8).getList().get(2).getList().get(1).setChecked(true);
					}
					if ("qab_seeDetails".equals(perm)) {
						allPermissionList.get(8).setChecked(true);
						allPermissionList.get(8).getList().get(3).setChecked(true);
						allPermissionList.get(8).getList().get(3).getList().get(0).setChecked(true);
					}
					if ("qab_edit".equals(perm)) {
						allPermissionList.get(8).setChecked(true);
						allPermissionList.get(8).getList().get(3).setChecked(true);
						allPermissionList.get(8).getList().get(3).getList().get(1).setChecked(true);
					}
					if ("bjb_seeDetails".equals(perm)) {
						allPermissionList.get(9).setChecked(true);
						allPermissionList.get(9).getList().get(0).setChecked(true);
						allPermissionList.get(9).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("bjb_edit".equals(perm)) {
						allPermissionList.get(9).setChecked(true);
						allPermissionList.get(9).getList().get(0).setChecked(true);
						allPermissionList.get(9).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("kh_seeDetails".equals(perm)) {
						allPermissionList.get(9).setChecked(true);
						allPermissionList.get(9).getList().get(1).setChecked(true);
						allPermissionList.get(9).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("kh_edit".equals(perm)) {
						allPermissionList.get(9).setChecked(true);
						allPermissionList.get(9).getList().get(1).setChecked(true);
						allPermissionList.get(9).getList().get(1).getList().get(1).setChecked(true);
					}
					if ("yhb_seeDetails".equals(perm)) {
						allPermissionList.get(10).setChecked(true);
						allPermissionList.get(10).getList().get(0).setChecked(true);
						allPermissionList.get(10).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("yhb_edit".equals(perm)) {
						allPermissionList.get(10).setChecked(true);
						allPermissionList.get(10).getList().get(0).setChecked(true);
						allPermissionList.get(10).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("jsb_seeDetails".equals(perm)) {
						allPermissionList.get(11).setChecked(true);
						allPermissionList.get(11).getList().get(0).setChecked(true);
						allPermissionList.get(11).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("jsb_edit".equals(perm)) {
						allPermissionList.get(11).setChecked(true);
						allPermissionList.get(11).getList().get(0).setChecked(true);
						allPermissionList.get(11).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("shebei_seeDetails".equals(perm)) {
						allPermissionList.get(12).setChecked(true);
						allPermissionList.get(12).getList().get(0).setChecked(true);
						allPermissionList.get(12).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("shebei_edit".equals(perm)) {
						allPermissionList.get(12).setChecked(true);
						allPermissionList.get(12).getList().get(0).setChecked(true);
						allPermissionList.get(12).getList().get(0).getList().get(1).setChecked(true);
					}
					
					if ("software_seeDetails".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(0).setChecked(true);
						allPermissionList.get(13).getList().get(0).getList().get(0).setChecked(true);
					}
					if ("software_edit".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(0).setChecked(true);
						allPermissionList.get(13).getList().get(0).getList().get(1).setChecked(true);
					}
					if ("dzdgl_seeDetails".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(1).setChecked(true);
						allPermissionList.get(13).getList().get(1).getList().get(0).setChecked(true);
					}
					if ("dzdgl_edit".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(1).setChecked(true);
						allPermissionList.get(13).getList().get(1).getList().get(1).setChecked(true);
					}
					if ("aqsz_seeDetails".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(2).setChecked(true);
						allPermissionList.get(13).getList().get(2).getList().get(0).setChecked(true);
					}
					if ("aqsz_edit".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(2).setChecked(true);
						allPermissionList.get(13).getList().get(2).getList().get(1).setChecked(true);
					}
					if ("dxjksz_seeDetails".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(3).setChecked(true);
						allPermissionList.get(13).getList().get(3).getList().get(0).setChecked(true);
					}
					if ("dxjksz_edit".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(3).setChecked(true);
						allPermissionList.get(13).getList().get(3).getList().get(1).setChecked(true);
					}
					if ("xtrzgl_seeDetails".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(4).setChecked(true);
						allPermissionList.get(13).getList().get(4).getList().get(0).setChecked(true);
					}
					if ("xtrzgl_edit".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(4).setChecked(true);
						allPermissionList.get(13).getList().get(4).getList().get(1).setChecked(true);
					}
					if ("sqlmlhgj_seeDetails".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(5).setChecked(true);
						allPermissionList.get(13).getList().get(5).getList().get(0).setChecked(true);
					}
					if ("sqlmlhgj_edit".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(5).setChecked(true);
						allPermissionList.get(13).getList().get(5).getList().get(1).setChecked(true);
					}
					if ("fcjgl_seeDetails".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(6).setChecked(true);
						allPermissionList.get(13).getList().get(6).getList().get(0).setChecked(true);
					}
					if ("fcjgl_edit".equals(perm)) {
						allPermissionList.get(13).setChecked(true);
						allPermissionList.get(13).getList().get(6).setChecked(true);
						allPermissionList.get(13).getList().get(6).getList().get(1).setChecked(true);
					}
					
				}
				TreeDemo trees = new TreeDemo();
				trees.setTrees(allPermissionList);
				result.setData(trees);
				result.setCode(0);
				result.setMsg("success!");
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				result.setMsg("权限数据格式不对，请联系技术人员维护!");
				return result;
			}
		} else {
			TreeDemo trees = new TreeDemo();
			trees.setTrees(allPermissionList);
			result.setData(trees);
			result.setCode(0);
			result.setMsg("success!");
			return result;
		}

	}

}
