package com.green.Shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//프로젝트 시작과 동시에 실행되는 컨트롤러
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "redirect:/item/main";
    }

}
