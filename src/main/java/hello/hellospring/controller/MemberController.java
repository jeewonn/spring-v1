package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// @Controller => 스프링 컨테이너가 MemberController 객체를 생성하고 스프링에 넣어두고, 스프링이 이걸 관리함.
@Controller
//@AllArgsConstructor
public class MemberController {

    // 스프링이 관리를 하면 스프링 컨테이너가 관리하고 컨테이너에서 받아서 쓸 수 있도록 만들어야됨.
    private final MemberService memberService;

    // 스프링 컨테이너에 있는 memberService를 가져다가 연결을 시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
