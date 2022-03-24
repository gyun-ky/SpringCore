package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberSerivceImpl;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderSerivce;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){

        return new MemberSerivceImpl(memberRepository());
    }// MemoryMemeberRepository를 생성하고 그 참조값을 MemberSErviceImpl에 전달 -> 주입

    @Bean // option으로 name 으로 bean key 값 설정 가능
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 만약 repository를 바꾼다면 해당 코드 부분만 바꾸면 된다
    }

    @Bean
    public OrderSerivce orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    // 구성 영역에만 있는 코드들만 손대면 된다
    // client 코드를 전혀 변경하지 않고도 application 기능을 확장 가능

}
