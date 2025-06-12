package cn.edu.cdut.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@Component
//继承这个基类handlerInterceptor
public class TokenInterceptor implements HandlerInterceptor {
    public static HashSet<String> tokenSet = new HashSet<String>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.放行登录界面
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        if (method.equals("OPTIONS")) {
            return true;
        }
        if(requestURI.contains("/user/login")){
            return true;
        }
        String token = request.getHeader("token");
        if(!tokenSet.contains(token)||!StringUtils.hasLength(token)){
            System.out.println(token);
            throw new RuntimeException("403,token无效或token不存在");
        }
//        System.out.println(token);
        return true;
    }
}
