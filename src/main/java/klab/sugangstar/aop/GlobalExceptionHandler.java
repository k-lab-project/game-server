package klab.sugangstar.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex, HttpServletRequest request) {
        // IP 주소 가져오기
        String clientIp = request.getRemoteAddr();

        // 요청 정보 가져오기
        String requestUrl = request.getRequestURI();
        String requestMethod = request.getMethod();

        // 로그 출력
        log.error("예외 발생 - Client IP: {}, Request URL: {}, Request Method: {}, Error: {}", clientIp, requestUrl, requestMethod, ex.getMessage(), ex);
    }
}
