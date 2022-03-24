package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberSerivceImpl;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderSerivce;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){

        return new MemberSerivceImpl(memberRepository());
    }// MemoryMemeberRepository를 생성하고 그 참조값을 MemberSErviceImpl에 전달 -> 주입

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 만약 repository를 바꾼다면 해당 코드 부분만 바꾸면 된다
    }


    public OrderSerivce orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
