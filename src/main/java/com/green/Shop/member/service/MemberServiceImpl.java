package com.green.Shop.member.service;

import com.green.Shop.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final SqlSessionTemplate sqlSession;

    //회원가입
    @Override
    public int insertMember(MemberVO memberVO) {
        return sqlSession.insert("memberMapper.insertMember", memberVO);
    }

    //로그인
    @Override
    public MemberVO selectLogin(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.selectLogin", memberVO);
    }

    //중복 아이디 검사
    @Override
    public boolean selectId(String memberId) {
        String selectedMemberId = sqlSession.selectOne("memberMapper.selectId", memberId);
        return selectedMemberId == null;
    }
}
