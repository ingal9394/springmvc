package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 원래 controller 로 어노테이션하면 viewresolver 가 돌면서 viewname으로반환되는데 restcontroller는 body에  반환값을 팍 넣음
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());//slf4j 로 추가해야함

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);
        log.trace(" trace log = {}",name); //레벨임 application.properties 에서 logging 설정시  어디까지 보여질것인가  trace(개발)/debug(개발)/info(운영) 보통 이렇게설정
        log.debug(" debug log = {}",name);
        log.info(" info log = {}",name);
        log.warn(" warn log = {}",name);
        log.error(" error log = {}",name);

        return  "ok";

    }
}
