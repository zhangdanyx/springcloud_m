package zd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = { "com.ls.zd" })
@EnableEurekaServer
public class ServerAppMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerAppMain.class, args);
    }
}
