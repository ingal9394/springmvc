package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // 원래 controller 로 어노테이션하면 반환값이 string 이면 viewresolver 가 돌면서 viewname으로반환되어 뷰가 렌더링됨
// restcontroller는 http body에  반환값을 넣음 = 실행결과로 홈페이지에 ok표시됨
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass());//slf4j 로 추가해야함(추가하면 어노테이션에 자동으로 포함되어있는거 lombok)

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);
        //log.trace(" trace log ="+name); 이렇게 써놓고 레벨을 debug로 두고 로깅하면 trace는 안나오지만 
        //                                java문법상 출력은안되도  name에 대입 해서 결과값은 가지고있게되기에 리소스를먹어서 이렇게 쓰면 안됨 밑에것 처럼 써야함 {} 함수처럼
        log.trace(" trace log = {}",name); //레벨임 application.properties 에서 logging 설정시  어디까지 보여질것인가  trace(개발)/debug(개발)/info(운영) 보통 이렇게설정
        log.debug(" debug log = {}",name);
        log.info(" info log = {}",name);
        log.warn(" warn log = {}",name);
        log.error(" error log = {}",name);

        return  "ok";

    }
}
