/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.qiku.usermgr.api.shiro;
import com.qiku.usermgr.store.dao.UMenuMapper;
import com.qiku.usermgr.store.dao.URoleMapper;
import com.qiku.usermgr.store.dao.UUserMapper;
import com.qiku.usermgr.store.dao.UUserRoleMapper;
import com.qiku.usermgr.store.model.UMenu;
import com.qiku.usermgr.store.model.URole;
import com.qiku.usermgr.store.model.UUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shiro-密码输入错误的状态下重试次数的匹配管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */

public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UUserMapper uUserMapper;
    @Resource
    private UMenuMapper menuMapper;
    @Resource
    private URoleMapper uRoleMapper;

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        UUser user = uUserMapper.findByName(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        //if (user.getStatus() != null && user.getStatus()) {
        //    throw new LockedAccountException("帐号已被锁定，禁止登录！");
       // }

        // principal参数使用用户Id，方便动态刷新用户权限
        return new SimpleAuthenticationInfo(
                user.getuId(),
                user.getPwd(),
                ByteSource.Util.bytes(username),
                getName()
        );
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();

        // 赋予角色
        List<URole> roleList = uRoleMapper.getRoleListByUid(userId);
        for (URole role : roleList) {
            info.addRole(role.getRoleName());
        }

        // 赋予权限
        List<UMenu> menuList = null;
        UUser user = uUserMapper.selectByPrimaryKey(userId);
        if (null == user) {
            return info;
        }

        menuList = menuMapper.findMenuByUid(userId);
        if (!CollectionUtils.isEmpty(menuList)) {
            Set<String> permissionSet = new HashSet<>();
            for (UMenu menus : menuList) {
                String permission = null;
                if (!StringUtils.isEmpty(permission = menus.getmPerms())) {
                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
                }
            }
            info.setStringPermissions(permissionSet);
        }
        return info;
    }

}
