package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message={}",messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message={}",messageBody);
        responseWriter.write("ok");
    }

    //메세지 바디정보를 직접 조회, 요청파라미터 조회하는 기능이랑은 관계없음
    //form 형식으로 날라온건 @requestparam @modelattribute 등으로 파라미터 꺼내는데  form 형식이 아니게 날라올때 쓰게됨
    //응답도가능함
    //헤더정보포함
    @PostMapping("/request-body-string-v3")
    public HttpEntity requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException { 
        
        String body = httpEntity.getBody();

        log.info("message={}",body);
        return new HttpEntity<>("ok");
    }
    // 위에걸 어노테이션으로 만든게 @requestbody  걍 넣으면 같은거임 body정보 쉽게 볼수있음 @requesthead 쓰면 헤드도 가져와지지
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("message={}",messageBody);
        return "ok";
    }
}
