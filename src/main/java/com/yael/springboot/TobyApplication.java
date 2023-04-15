package com.yael.springboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 스프링 컨테이너의 초기화 작업은 .refresh() 메소드가 호출될 때 수행됨.
 * @Configuration (구성정보를 가진 클래스) 어노테이션이 붙은 클래스에는 스프링이 @Bean 메서드가 붙은 팩토리 메소드가 있겠구나 알고 생성해줌.
 */
@Configuration
public class TobyApplication {
    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }
    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this)
                    ).addMapping("/*");
                });

                webServer.start();
            }
        };

        applicationContext.register(TobyApplication.class);
        applicationContext.refresh();
    }
}
