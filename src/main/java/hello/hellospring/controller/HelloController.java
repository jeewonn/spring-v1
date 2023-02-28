package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적 방식
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; // resource/templates/hello.html (hello 파일면과 같은거)
    }

    // MVC 방식
    @GetMapping("hello-mvc")
        public String helloMvc(@RequestParam(value = "name", required = false) String name,Model model){
            model.addAttribute("name", name);
            return "hello-template";
    }

    // API 방식
    @GetMapping("hello-string")
    @ResponseBody // http의 body에 return 내용을 직접 넣겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //hello ${name}
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}

