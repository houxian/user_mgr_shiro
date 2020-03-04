package com.qiku.usermgr.api.service;

import com.qiku.usermgr.core.page.MybatisPageHelper;
import com.qiku.usermgr.core.page.PageRequest;
import com.qiku.usermgr.core.page.PageResult;
import com.qiku.usermgr.core.service.CurdService;
import com.qiku.usermgr.store.dao.MenuMapper;
import com.qiku.usermgr.store.dao.RoleMapper;
import com.qiku.usermgr.store.dao.UserMapper;
import com.qiku.usermgr.store.dao.UserRoleMapper;
import com.qiku.usermgr.store.model.Menu;
import com.qiku.usermgr.store.model.Role;
import com.qiku.usermgr.store.model.User;
import com.qiku.usermgr.store.model.User;
import com.qiku.usermgr.store.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * @author houxianyong
 * @date 2020-2-26
 */
@Service
public class UserService implements CurdService<User> {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private MenuMapper uMenuMapper;

	/**
	 * 添加+编辑
	 * @param record
	 * @return
	 */
	@Transactional
	@Override
	public int save(User record) {
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
			for(UserRole userRole :record.getUserRoles()) {
				userRole.setuId(uid);
			}
		} else {
            userRoleMapper.deleteByPrimaryKey(record.getuId());
		}
		for(UserRole userRole :record.getUserRoles()) {
            userRoleMapper.insertSelective(userRole);
		}
		return 1;
	}

	/**
	 * 删除用户
	 * @param uUser
	 * @return
	 */
	@Override
	public int delete(User uUser) {
		return userMapper.deleteByPrimaryKey(uUser.getuId());
	}

	/**
	 * 批量删除
	 * @param records
	 * @return
	 */
	@Override
	public int delete(List<User> records) {
		for(User record:records) {
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
	public User findById(Long uid) {
		return userMapper.selectByPrimaryKey(uid);
	}

	/**
	 * 获取用户信息
	 * @param name
	 * @return
	 */
	public User findByName(String name) {
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
			User sysUser = (User) object;
			List<UserRole> userRoles = findUserRoles(sysUser.getId());
			sysUser.setUserRoles(userRoles);
			sysUser.setRoleNames(getRoleNames(userRoles));
		}
	}

	private String getRoleNames(List<UserRole> userRoles) {

		StringBuilder sb = new StringBuilder();
		for(Iterator<UserRole> iter = userRoles.iterator(); iter.hasNext();) {
			UserRole userRole = iter.next();
			Role uRole = roleMapper.selectByPrimaryKey(userRole.getRoleId());
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
		List<Menu> menus = uMenuMapper.findByUserName(userName);
		for(Menu menu : menus) {
			if(menu.getmPerms() != null && !"".equals(menu.getmPerms())) {
				perms.add(menu.getmPerms());
			}
		}
		return perms;
	}


	public List<UserRole> findUserRoles(Long userId) {
		return userRoleMapper.findUserRoles(userId);
	}

}
