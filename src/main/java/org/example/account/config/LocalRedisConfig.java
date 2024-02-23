package org.example.account.config;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Configuration
public class LocalRedisConfig {

    @Value("${spring.data.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis(){ // 메서드 이름은 상관 없음
        if(isArmArchitecture()){
            redisServer = new RedisServer(Objects.requireNonNull(getRedisServerExecutable()), redisPort);
        } else {
            redisServer = RedisServer.builder().port(redisPort).setting("maxmemory 128M").build();
        }

        try{
            redisServer.start();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @PreDestroy
    public void stopRedis(){ // 메서드 이름은 상관 없음
        if(redisServer != null){
            redisServer.stop();
        }
    }

    /**
     *
     * MAC redis embedded 에러로 인한 추가
     */
    private File getRedisServerExecutable() {
        try {
            return new File("src/main/resources/redis/redis-server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isArmArchitecture() {
        return System.getProperty("os.arch").contains("aarch64");
    }
}
