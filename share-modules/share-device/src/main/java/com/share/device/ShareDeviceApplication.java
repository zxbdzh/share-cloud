package com.share.device;

import com.share.common.security.annotation.EnableCustomConfig;
import com.share.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 设备模块
 */

@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class ShareDeviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShareDeviceApplication.class);
        System.out.println("(♥◠‿◠)ﾉﾞ  设备模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                           " .-------.       ____     __        \n" +
                           " |  _ _   \\      \\   \\   /  /    \n" +
                           " | ( ' )  |       \\  _. /  '       \n" +
                           " |(_ o _) /        _( )_ .'         \n" +
                           " | (_,_).' __  ___(_ o _)'          \n" +
                           " |  |\\ \\  |  ||   |(_,_)'         \n" +
                           " |  | \\ `'   /|   `-'  /           \n" +
                           " |  |  \\    /  \\      /           \n" +
                           " ''-'   `'-'    `-..-'              ");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
