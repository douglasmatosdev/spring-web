package com.douglasmatosdev.springweb.services.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douglasmatosdev.springweb.services.CookieService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.ModelAndView;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        try {
            if (CookieService.getCookie(request, "userId") != null) {
                return true;
            }
        } catch (Exception erro) {
        }

        response.sendRedirect("/login");
        return false;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response,
//                           Object handler, ModelAndView modelAndView) throws Exception {
//
//        System.out.println("Post Handle method is Calling");
//    }
//
//    @Override
//    public void afterCompletion
//            (HttpServletRequest request, HttpServletResponse response, Object
//                    handler, Exception exception) throws Exception {
//
//        System.out.println("Request and Response is completed");
//    }
}