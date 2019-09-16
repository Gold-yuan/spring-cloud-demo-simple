# spring-cloud-demo-simple
导入eclipse时选择普通导入，导入成功右键项目转为gradle

配置文件以下为必填项：
注册中心
```yml
spring:
  application:
    name: discovery-service
eureka:
  client:
    register-with-eureka: false # 不注册eureka服务
    fetch-registry: false # 不同步注册表
```
非注册中心
```yml
spring:
  application:
    name: discovery-service
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```
@SpringCloudApplication 表明程序启动入口，包含如下
  <ul>
  <li>@SpringBootApplication 表明该类为程序启动类</li>
  <li>@EnableDiscoveryClient 表明开启服务发现，Eureka</li>
  <li>@EnableCircuitBreaker 表明开启Hystrix</li>
  </ul>
  
  
 ## 远程调用接口
 1、添加配置
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
2、使用RestTemplate调用，下面仅是举例，还有其他。
    @Autowired
    private RestTemplate restTemplate;
    restTemplate.getForObject("http://DEMO-SERVICE/getMe", String.class);
    
3、熔断机制使用：
在service加上@HystrixCommand
```java

    @Override
    @HystrixCommand(fallbackMethod = "error")
    public String getMe(String param) {
        return restTemplate.getForObject("http://DEMO-SERVICE/getMe", String.class);
    }
    // 此处Throwable throwable可选
    public String error(String param, Throwable throwable) {
        
        return "error " + param;
    }
```
