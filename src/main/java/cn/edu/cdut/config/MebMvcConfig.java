package cn.edu.cdut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MebMvcConfig implements WebMvcConfigurer {
//    //配置跨域
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")
//                .allowedHeaders("*");
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login");
//    }
}
