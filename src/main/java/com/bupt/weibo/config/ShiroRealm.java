package com.bupt.weibo.config;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class ShiroRealm extends AuthenticatingRealm {
    @Autowired
    private UserService userService;

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
        String email = upToken.getUsername();
        // 查询数据库，是否查询到用户名和密码的用户
        User user = userService.getUserByEmail(email);

        if(user != null) {
            // 如果查询到了，封装查询结果，返回给我们的调用
            Object principal =  user.getEmail();
            Object credentials = user.getPassword();

            // 获取盐值，即用户名
            ByteSource salt = ByteSource.Util.bytes(email);
            String realmName = this.getName();
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            info = new SimpleAuthenticationInfo(principal, credentials, salt,realmName);
        }else {
            // 如果没有查询到，抛出一个异常
            throw new AuthenticationException();
        }

        return info;
    }
}
