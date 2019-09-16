package com.ytf.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ytf.demo.entity.User;
import com.ytf.demo.service.RemoteService.UserDataFeignClientFallbackFactory;

import feign.hystrix.FallbackFactory;

/**
 * @FeignClient 注解为feign客户端 <br/>
 *              name：微服务实例名称，不区分大小写 <br/>
 *              fallbackFactory：熔断机制 <br/>
 *              fallback：熔断实现类
 *
 */
@FeignClient(name = "demo-service", fallbackFactory = UserDataFeignClientFallbackFactory.class)
public interface RemoteService {
    /**
     * 注意：返回类型必须要有无参构造器 此类注解 @RequestParam 不能少
     * 
     * @PostMapping 需要访问的微服务的api
     * @RequestParam 参数映射根据实际情况改变，也可以是 @PathVariable 等
     * @Cacheable 给此方法添加本地缓存， <br/>
     *            unless：为过滤逻辑， "#result==null":返回结果为null则不缓存，sync=true 与 unless不兼容
     *            value：application.properties配置文件中的spring.cache.cache-names属性的值
     *            keyGenerator：com.xqx.business.config.CacheConfig.wiselyKeyGenerator()方法名
     */
    @GetMapping("/getMe")
    User getMe(@RequestParam("param") String param);

    @Component
    public static class UserDataFeignClientFallbackFactory implements FallbackFactory<RemoteService> {
        private static final Logger logger = LoggerFactory.getLogger(UserDataFeignClientFallbackFactory.class);

        @Override
        public RemoteService create(Throwable cause) {
            logger.info("访问失败，服务降级,{}", cause.getMessage());
            return new RemoteServiceImpl();
        }
    }
}
