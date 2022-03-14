package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.example.demo.model.MemberVO;
import com.example.demo.repository.MemberRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    
    @GetMapping("/stream")
    public void getStream() {
        System.out.println("Request Method : getStream");

        List<String> myList = Arrays.asList("a1","a2","b1","b2","c2","c1");

        myList.stream().filter(s -> s.startsWith("c"));

        Stream.of("a1","a2","a3").map(s->s.substring(1)).mapToInt(Integer::parseInt).max().ifPresent(System.out::println);

    }

    @GetMapping("/member")
    public ResponseEntity<?> getMember() {
        System.out.println("Request Method : GetMember");
        List<MemberVO> memberVO = memberRepository.findAll();
        System.out.println("mem: " + memberVO);

        return ResponseEntity.ok().body(memberVO);
    }
    
    @GetMapping("/member/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable("id") String id) {
        System.out.println("Request Method : GetMemberById");
        System.out.println("id: " + id);
        // MemberVO memberVO = memberRepository.getById(id);
        //MemberVO memberVO = memberRepository.findById(id).orElse(new MemberVO());
        Optional<MemberVO> memberVO = memberRepository.findById(id);
        System.out.println("mem: " + memberVO);

        return ResponseEntity.ok().body(memberVO);
    }


    @GetMapping("/memberFindByName/{name}")
    public ResponseEntity<?> getMemberByName(@PathVariable("name") String name) {
        System.out.println("Request Method : GetMemberByName");
        System.out.println("name: " + name);
        // MemberVO memberVO = memberRepository.getById(id);
        Optional<MemberVO> memberVO = memberRepository.findByName(name);
        System.out.println("mem: " + memberVO);

        return ResponseEntity.ok().body(memberVO);
    }


    @GetMapping("/memberFindByNameLike/{name}")
    public ResponseEntity<?> memberFindByNameLike(@PathVariable("name") String name) {
        System.out.println("Request Method : GetMemberByName");
        System.out.println("name: " + name);
        // MemberVO memberVO = memberRepository.getById(id);
        List<MemberVO> memberVO = memberRepository.findByNameLike2(name);
        System.out.println("mem: " + memberVO);

        return ResponseEntity.ok().body(memberVO);
    }


    @GetMapping("/memberFindByNameLike2/{name}")
    public ResponseEntity<?> memberFindByNameLike2(@PathVariable("name") String name) {
        System.out.println("Request Method : GetMemberByName");
        System.out.println("name: " + name);
        // MemberVO memberVO = memberRepository.getById(id);
        List<MemberVO> memberVO = memberRepository.findByNameLike2(name);
        System.out.println("mem: " + memberVO);

        return ResponseEntity.ok().body(memberVO);
    }

    @GetMapping("/findByNameStartsWith/{name}")
    public ResponseEntity<?> findByNameStartsWith(@PathVariable("name") String name) {
        System.out.println("Request Method : GetMemberByName");
        System.out.println("name: " + name);
        // MemberVO memberVO = memberRepository.getById(id);
        List<MemberVO> memberVO = memberRepository.findByNameStartsWith(name);
        System.out.println("mem: " + memberVO);

        return ResponseEntity.ok().body(memberVO);
    }

    @GetMapping("/memberFindByOrName/{keyword}")
    public ResponseEntity<?> findByIdOrName(@PathVariable("keyword") String keyword) {
        System.out.println("Request Method : FindByIdOrName");
        System.out.println("keyword: " + keyword);
        // MemberVO memberVO = memberRepository.getById(id);
        Optional<MemberVO> memberVO = memberRepository.findByIdOrName(keyword, keyword);
        System.out.println("mem: " + memberVO);

        return ResponseEntity.ok().body(memberVO);
    }
    
    @PostMapping("/member")
    public ResponseEntity<?> resisterMember(@RequestBody MemberVO memberVO ) {
        System.out.println("Request Method : ResisterMember");
        System.out.println("mem: " + memberVO.toString());
        memberRepository.save(memberVO);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/member")
    public ResponseEntity<?> updateMember(@RequestBody MemberVO memberVO ) {
        System.out.println("Request Method : ResisterMember");
        System.out.println("mem: " + memberVO.toString());
        memberRepository.save(memberVO);

        return ResponseEntity.ok().build();
    }
}
