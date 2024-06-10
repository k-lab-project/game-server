package klab.sugangstar.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;

@Aspect
@Component
public class RequestLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;

    @Before("execution(* klab.sugangstar.controller.*.*(..))")
    public void logRequest(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String clientIp = request.getRemoteAddr();
            String requestUrl = getRequestUrlWithQueryString(request);
            String userId = "user123"; // 사용자 ID를 얻는 방법은 구현되어 있지 않으므로 예시로 user123을 사용
            String requestMethod = request.getMethod();
            String requestParams = request.getQueryString();

            // 요청의 JSON 데이터 추출
            String requestBody = null;
            try {
                requestBody = extractJsonFromRequest(request);
            } catch (IOException e) {
                // JSON 데이터 추출 중 오류가 발생한 경우 처리
                logger.error("Error extracting JSON from request", e);
            }

            logger.info("User ID: {}, Client IP: {}, Request URL: {}, Request Method: {}, Request Parameters: {}, Request Body: {}",
                    userId, clientIp, requestUrl, requestMethod, requestParams, requestBody);
        }
    }

    private String extractJsonFromRequest(HttpServletRequest request) throws IOException {
        // 요청의 InputStream에서 JSON 데이터를 읽어오는 코드 작성
        StringBuilder requestBodyBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBodyBuilder.append(line);
            }
        }

        // 읽어온 JSON 데이터 반환
        return requestBodyBuilder.toString();
    }

    private String getRequestUrlWithQueryString(HttpServletRequest request) {
        // 요청 URL과 쿼리 스트링을 합쳐서 반환
        StringBuilder requestUrlWithQueryString = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();
        if (queryString != null) {
            requestUrlWithQueryString.append('?').append(queryString);
        }
        return requestUrlWithQueryString.toString();
    }

    @AfterReturning(pointcut = "execution(* klab.sugangstar.controller.*.*(..))", returning = "result")
    public void logResponse(Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            Long startTime = (Long) attributes.getRequest().getAttribute("startTime");
            if (startTime != null) {
                long responseTime = System.currentTimeMillis() - startTime;
                logger.info("Response Time: {} ms", responseTime);
            }
        }
    }
}
