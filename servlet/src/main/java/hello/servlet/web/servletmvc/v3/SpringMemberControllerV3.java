package hello.servlet.web.servletmvc.v3;


import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);
        return new ModelAndView("save-result").addObject("member",member);
    }


    @RequestMapping(method = RequestMethod.GET)
    public String process(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "members"; // 뷰의 논리 이름을 반환할 수 있다
    }

}