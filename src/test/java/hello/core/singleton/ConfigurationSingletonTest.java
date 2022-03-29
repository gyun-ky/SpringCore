package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberSerivceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    
    @Test
    public void ConfigurationTest() throws Exception{
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberSerivceImpl memberService = ac.getBean("memberService", MemberSerivceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository3 = ac.getBean(MemberRepository.class);
        //when
        MemberRepository memberRepository = memberService.getMemberRepository();
        MemberRepository memberRepository1 = orderService.getMemberRepository();

        //then
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberRepository1 = " + memberRepository1); // 둘다 똑같다!!!!
        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isEqualTo(memberRepository3);

        // appConfig에다가 sout을 찍어보면 

    }
    
    @Test
    public void configurationDeep() throws Exception{
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class); // 자식 클래스를 임의로 spring이 만들어서 부모 AppConfig로 가져온다
        //when
        
        //then
        System.out.println("bean = " + bean.getClass());
        // $$EnhancerBySpringCGLIB
    }
        
        
}
