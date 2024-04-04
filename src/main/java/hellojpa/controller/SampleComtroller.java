package hellojpa.controller;

import hellojpa.entity.Member;
import hellojpa.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleComtroller {
    @Autowired
    MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        memberRepository.save(new Member("hello1", 1));
        memberRepository.save(new Member("hello2", 2));
        memberRepository.save(new Member("hello3", 3));
        memberRepository.save(new Member("hello4", 4));
        memberRepository.save(new Member("hello5", 5));
    }

    @GetMapping("/hello")
    public Member member() {
        return memberRepository.findByName("hello1");
    }

    @GetMapping("/search")
    Page<Member> search() {
        PageRequest request = PageRequest.of(0, 10);
        return memberRepository.findByName("hello1", request);
    }
}
