package com.adwyxx.springboot.redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Redisson Manager
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-29 16:41
 */
@Component
public class RedissonManager {
    private static final Logger logger = LoggerFactory.getLogger(RedissonManager.class);
    private static Config config = new Config();
    private static Redisson redisson;

    static {
        try {
            config = Config.fromYAML(RedissonManager.class.getClassLoader().getResource("redisson-config.yml"));
            //创建Redisson实例
            redisson = (Redisson)Redisson.create(config);
            logger.error("初始化Redisson成功");
        } catch (IOException e) {
            logger.error("加载Redisson配置文件redisson-config.yml失败",e);
        }
    }

    public Redisson getRedisson(){
        return redisson;
    }
}
