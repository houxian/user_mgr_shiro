package com.qiku.usermgr.api.service;

import com.qiku.usermgr.core.page.MybatisPageHelper;
import com.qiku.usermgr.core.page.PageRequest;
import com.qiku.usermgr.core.page.PageResult;
import com.qiku.usermgr.core.service.CurdService;
import com.qiku.usermgr.store.dao.UMenuMapper;
import com.qiku.usermgr.store.dao.URoleMapper;
import com.qiku.usermgr.store.dao.UUserMapper;
import com.qiku.usermgr.store.dao.UUserRoleMapper;
import com.qiku.usermgr.store.model.UMenu;
import com.qiku.usermgr.store.model.URole;
import com.qiku.usermgr.store.model.UUser;
import com.qiku.usermgr.store.model.UUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * @author houxianyong
 * @date 2020-2-26
 */
@Service
public class UserService implements CurdService<UUser> {

	@Autowired
	private UUserMapper userMapper;
	@Autowired
	private UUserRoleMapper userRoleMapper;
	@Autowired
	private URoleMapper roleMapper;
	@Autowired
	private UMenuMapper uMenuMapper;

	/**
	 * 添加+编辑
	 * @param record
	 * @return
	 */
	@Transactional
	@Override
	public int save(UUser record) {
		Long uid = null;
		if(record.getuId() == null || record.getuId() == 0) {
			// 新增用户
            userMapper.insertSelective(record);
			uid = record.getuId();
		} else {
			// 更新用户信息
            userMapper.updateByPrimaryKeySelective(record);
		}
		// 更新用户角色
		if(uid != null && uid == 0) {
			return 1;
		}
		if(uid != null) {
			for(UUserRole uUserRole:record.getUserRoles()) {
				uUserRole.setuId(uid);
			}
		} else {
            userRoleMapper.deleteByPrimaryKey(record.getuId());
		}
		for(UUserRole uUserRole:record.getUserRoles()) {
            userRoleMapper.insertSelective(uUserRole);
		}
		return 1;
	}

	/**
	 * 删除用户
	 * @param uUser
	 * @return
	 */
	@Override
	public int delete(UUser uUser) {
		return userMapper.deleteByPrimaryKey(uUser.getuId());
	}

	/**
	 * 批量删除
	 * @param records
	 * @return
	 */
	@Override
	public int delete(List<UUser> records) {
		for(UUser record:records) {
			delete(record);
		}
		return 1;
	}

	/**
	 * 根据用户id
	 * @param uid
	 * @return
	 */
	@Override
	public UUser findById(Long uid) {
		return userMapper.selectByPrimaryKey(uid);
	}

	/**
	 * 获取用户信息
	 * @param name
	 * @return
	 */
	public UUser findByName(String name) {
		return userMapper.findByName(name);
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		PageResult pageResult = null;
		Object name = pageRequest.getParam("name");
		Object email = pageRequest.getParam("email");
		if(name != null) {
			if(email != null) {
				pageResult = MybatisPageHelper.findPage(pageRequest, userMapper, "findPageByNameAndEmail", name, email);
			} else {
				pageResult = MybatisPageHelper.findPage(pageRequest, userMapper, "findPageByName", name);
			}
		} else {
			pageResult = MybatisPageHelper.findPage(pageRequest, userMapper);
		}
		// 加载用户角色信息
		findUserRoles(pageResult);
		return pageResult;
	}
	
	/**
	 * 加载用户角色
	 * @param pageResult
	 */
	private void findUserRoles(PageResult pageResult) {
		List<?> content = pageResult.getContent();
		for(Object object:content) {
			UUser sysUser = (UUser) object;
			List<UUserRole> userRoles = findUserRoles(sysUser.getId());
			sysUser.setUserRoles(userRoles);
			sysUser.setRoleNames(getRoleNames(userRoles));
		}
	}

	private String getRoleNames(List<UUserRole> userRoles) {

		StringBuilder sb = new StringBuilder();
		for(Iterator<UUserRole> iter=userRoles.iterator(); iter.hasNext();) {
			UUserRole userRole = iter.next();
			URole uRole = roleMapper.selectByPrimaryKey(userRole.getRoleId());
			if(uRole == null) {
				continue ;
			}
			sb.append(uRole.getRoleName());
			if(iter.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	/**
	 * 根据用户获取用户的权限
	 * @param userName
	 * @return
	 */
	public Set<String> findPermissions(String userName) {

		Set<String> perms = new HashSet<>();
		List<UMenu> uMenus = uMenuMapper.findByUserName(userName);
		for(UMenu uMenu:uMenus) {
			if(uMenu.getmPerms() != null && !"".equals(uMenu.getmPerms())) {
				perms.add(uMenu.getmPerms());
			}
		}
		return perms;
	}


	public List<UUserRole> findUserRoles(Long userId) {
		return userRoleMapper.findUserRoles(userId);
	}

}
