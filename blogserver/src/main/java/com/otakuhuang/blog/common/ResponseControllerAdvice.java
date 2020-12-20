package com.otakuhuang.blog.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 要加上需要扫描的包
 */
@RestControllerAdvice(basePackages = {"com.otakuhuang.blog.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是 ResultVO，那就没必要进行额外的操作，返回 false
        return !methodParameter.getParameterType().equals(ResultVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String 类型不能直接包装，所以要进行些特别的处理
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在 ResultVO 里后，再转换为 json 字符串响应给前端
                return objectMapper.writeValueAsString(new ResultVO<>(o));
            } catch (JsonProcessingException e) {
                throw new APIException(1005, "返回 String 类型错误");
            }
        }
        // 将原本的数据包装在 ResultVO 里
        return new ResultVO<>(o);
    }
}
