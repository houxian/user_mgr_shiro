package com.qiku.usermgr.api.controller;

import com.qiku.usermgr.api.service.UserService;
import com.qiku.usermgr.api.utils.SHA1Util;
import com.qiku.usermgr.api.utils.VerifyCodeUtils;
import com.qiku.usermgr.core.http.BaseResponse;
import com.qiku.usermgr.core.page.PageRequest;
import com.qiku.usermgr.core.page.PageResult;
import com.qiku.usermgr.store.model.User;
import com.qiku.usermgr.store.model.UserRole;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 用户控制器
 * @author Louis
 * @date Jan 13, 2019
 */
@RestController
@RequestMapping("/user")
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 添加用户和编辑应用
	 * @param record
	 * @return
	 */
	@PostMapping(value="/save")
	public BaseResponse save(@RequestBody User record) {

		User user = userService.findById(record.getuId());
		if(record.getPwd() != null) {
			String salt = VerifyCodeUtils.generateVerifyCode(6);
			if(user == null) {
				// 新增用户
				if(userService.findByName(record.getName()) != null) {
					return BaseResponse.Fail("用户名已存在!");
				}
				String pwd = record.getPwd();
				String spwd = SHA1Util.shaEncrypt(pwd + salt);
				record.setSalt(salt);
				record.setPwd(spwd);
			}else {
				// 修改用户时不允许修改密码
				record.setPwd(null);
			}
			userService.save(record);
		}
		return BaseResponse.Ok();
	}

	/**
	 * 根据用户name 查看
	 * @param name
	 * @return
	 */
	@GetMapping(value="/findByName")
	public BaseResponse findByUserName(@RequestParam String name) {
		User user = null;
		try {
			user = userService.findByName(name);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return BaseResponse.Ok().putData(user);
	}

	/**
	 * 获取用户的权限
	 * @param name
	 * @return
	 */
	@GetMapping(value="/findPermissions")
	public BaseResponse findPermissions(@RequestParam String name) {
		Set<String> userPermission =null;
		try {
			userService.findPermissions(name);
		}catch (Exception ex){
			log.error(ex.getMessage());
		}

		return BaseResponse.Ok().putData(userPermission);
	}

	/**
	 * 获取用户角色列表
	 * @param userId
	 * @return
	 */
	@GetMapping(value="/findUserRoles")
	public BaseResponse findUserRoles(@RequestParam Long userId) {

		List<UserRole> userRoles = null;
		try {
			userRoles =  userService.findUserRoles(userId);
		}catch (Exception ex){
			log.error(ex.getMessage());
		}
		return BaseResponse.Ok().putData(userRoles);
	}

	/**
	 * 分页获取用户数据
	 * @param pageRequest
	 * @return
	 */
    @RequiresPermissions("sys:user:view")
	@PostMapping(value="/findPage")
	public BaseResponse findPage(@RequestBody PageRequest pageRequest) {
		log.info("findPage"+"info");
		PageResult pageResult  = null;
		try {
			pageResult = userService.findPage(pageRequest);
		}catch (Exception ex){
			log.error(ex.getMessage());
		}
		return BaseResponse.Ok().putData(pageResult);
	}
	
}
