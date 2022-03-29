package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody  //restcontroller랑 같은효과  return을 body에 넣음
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username={},age={}", memberName, memberAge);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, // http파라미터 이름이 변수명과 같으면 생략가능
                                 @RequestParam int age) {
        log.info("username={},age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, // String, int , INteger 등의 단순 타입이면 @requestparam도  생략가능
                                 int age) {
        log.info("username={},age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, //required = true는  해당 파라미터가 필수값이라 없이넘어오면 오류임
            @RequestParam(required = false) int age) {  //integer = null 오류안남 Integer가 객체라 , int = null은 오류남
        log.info("username={},age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, //defaultValue는 빈문자까지 처리함
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={},age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap) {
        log.info("username={},age={}", paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }
}
