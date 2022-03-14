package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.MemberVO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<MemberVO, String>{

    public Optional<MemberVO> findById(String id);
    public Optional<MemberVO> findByName(String name);
    public List<MemberVO> findByNameLike(String name);
    public List<MemberVO> findByNameStartsWith(String name);
    public Optional<MemberVO> findByIdOrName(String id, String name);

    @Query("SELECT m FROM member m WHERE m.name like %:name% ")
    List<MemberVO> findByNameLike2(@Param("name") String name);
}
