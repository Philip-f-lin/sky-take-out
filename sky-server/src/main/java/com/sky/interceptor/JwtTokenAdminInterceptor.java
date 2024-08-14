package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt 令牌校驗的攔截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校驗jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判斷目前攔截到的是 Controller 的方法還是其他資源
        if (!(handler instanceof HandlerMethod)) {
            //目前攔截到的不是動態方法，直接放行
            return true;
        }

        //1、從請求頭中取得Token
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、驗證令牌
        try {
            log.info("jwt驗證:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("現任員工id：", empId);
            BaseContext.setCurrentId(empId);
            //3、通過，放行
            return true;
        } catch (Exception ex) {
            //4、不通過，響應 401 狀態碼
            response.setStatus(401);
            return false;
        }
    }
}
