package com.yael.springboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 스프링 컨테이너의 초기화 작업은 .refresh() 메소드가 호출될 때 수행됨.
 * @Configuration (구성정보를 가진 클래스) 어노테이션이 붙은 클래스에는 스프링이 @Bean 메서드가 붙은 팩토리 메소드가 있겠구나 알고 생성해줌.
 *
 * 스프링 컨테이너에 @Component 어노테이션이 붙은 모든 bean 메서드들을 등록해준다.
 * 편리하기떄문에 많이 사용되지만, bean의 갯수가 많아지면 어떤 bean이 등록되었는지 알기 힘들 수 있음.
 * 패키지를 잘 구성한다면 컴포넌트 스캔 방식이 굉장히 좋은 방법이 될 수 있음.
 */
@Configuration
@ComponentScan
public class TobyApplication {
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = getBean(DispatcherServlet.class);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet
                    ).addMapping("/*");
                });

                webServer.start();
            }
        };

        applicationContext.register(TobyApplication.class);
        applicationContext.refresh();
    }
}
