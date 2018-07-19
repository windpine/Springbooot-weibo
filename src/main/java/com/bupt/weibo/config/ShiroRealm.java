package com.bupt.weibo.config;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
import com.bupt.weibo.entity.Permission;
import com.bupt.weibo.entity.Role;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.repository.PermissionRepository;
import com.bupt.weibo.repository.RoleRepository;
import com.bupt.weibo.repository.UserRepository;
import com.bupt.weibo.shiro.ShiroSessionDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ShiroSessionDao shiroSessionDao;
    private SimpleAuthenticationInfo info = null;

    /**
     * 1.doGetAuthenticationInfo，获取认证消息，如果数据库中没有数，返回null，如果得到了正确的用户名和密码，
     * 返回指定类型的对象
     *
     * 2.AuthenticationInfo 可以使用SimpleAuthenticationInfo实现类，封装正确的用户名和密码。
     *
     * 3.token参数 就是我们需要认证的token
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 将token装换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        // 获取用户名即可
        String username = upToken.getUsername();
        // 查询数据库，是否查询到用户名和密码的用户
        User user = userRepository.findByUsername(username);
        if(user != null) {
            // 如果查询到了，封装查询结果，返回给我们的调用
            Object principal =  user.getEmail();
            Object credentials = user.getPassword();
            //登陆成功
            //设置session
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("uid",user.getUid());
            // 获取盐值，即用户名
            ByteSource salt = ByteSource.Util.bytes(username);
            String realmName = this.getName();
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            info = new SimpleAuthenticationInfo(principal, credentials, salt,realmName);
        }else {
            // 如果没有查询到，抛出一个异常
            throw new AuthenticationException();
        }

        return info;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户
        //UserDto user = convertToDto(userDao.findUserByUsername((String)principalCollection.getPrimaryPrincipal()));
        //User currentUser = userDao.findUserByUsername((String)principalCollection.getPrimaryPrincipal());
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");

        //把principals放session中，key=userId value=principals
        SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getUid()),SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        for(Role role:user.getRoles()){
            info.addRole(role.getName());
        }
        //赋予权限
        for(Permission permission:user.getPermissions()){
            //System.out.println(permission.getName());
            info.addStringPermission(permission.getName());
        }
        return info;
    }

}
