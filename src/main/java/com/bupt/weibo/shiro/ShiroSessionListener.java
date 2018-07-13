package com.bupt.weibo.shiro;

/**
 * @anthor tanshangou
 * @time 2018/7/12
 * @description
 */
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by shusesshou on 2017/9/22.
 */
@Slf4j
public class ShiroSessionListener implements SessionListener {


    @Autowired
    private ShiroSessionDao shiroSessionDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void onStart(Session session) {
        log.debug("session {} onStart",session.getId());
    }

    @Override
    public void onStop(Session session) {
        shiroSessionDao.delete(session);
        Jedis jedis = jedisPool.getResource();
        jedis.publish("shiro.session.uncache",(String) session.getId());
        log.debug("session {} onStop", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        shiroSessionDao.delete(session);
        Jedis jedis = jedisPool.getResource();
        jedis.publish("shiro.session.uncache",(String) session.getId());
        log.debug("session {} onExpiration", session.getId());
    }
}