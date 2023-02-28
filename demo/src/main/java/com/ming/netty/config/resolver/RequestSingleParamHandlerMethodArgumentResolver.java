package com.ming.netty.config.resolver;

import com.alibaba.fastjson.JSONObject;
import com.ming.netty.preminary.annotation.RequestSingleParam;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class RequestSingleParamHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestSingleParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestSingleParam requestSingleParam=parameter.getParameterAnnotation(RequestSingleParam.class);
        HttpServletRequest request=webRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader bufferedReader=request.getReader();
        StringBuilder sb=new StringBuilder();
        char []buf=new char[1024];
        int rd;
        while ((rd=bufferedReader.read(buf))!=-1)
        {
            sb.append(buf,0,rd);
        }
        // test
        System.out.println(sb);

        JSONObject jsonObject=JSONObject.parseObject(sb.toString());
        String value=requestSingleParam.value();
        return  jsonObject.get(value);
    }
}
