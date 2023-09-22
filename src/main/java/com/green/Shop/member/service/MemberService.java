package com.green.Shop.member.service;

import com.green.Shop.member.vo.MemberVO;

public interface MemberService {

    //회원가입
    public int insertMember(MemberVO memberVO);

    //로그인
    public MemberVO selectLogin(MemberVO memberVO);

    //중복 아이디
    public boolean selectId(String memberId);

}
