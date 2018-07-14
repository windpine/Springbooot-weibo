package com.bupt.weibo.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;


/**
 * Created by shusesshou on 2017/9/22.
 */
@Slf4j
public class ShiroSessionFactory implements SessionFactory{ ;

    @Override
    public Session createSession(SessionContext sessionContext) {
        ShiroSession session = new ShiroSession();
        return session;
    }
}