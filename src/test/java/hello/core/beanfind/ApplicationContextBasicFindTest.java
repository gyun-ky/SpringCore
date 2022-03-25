package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberSerivceImpl;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    public void findBeanByName() throws Exception{
        //given
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberSerivce.getClass() = " + memberService.getClass());
        //when

        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberSerivceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    public void findBeanByType() throws Exception{
        //given
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberSerivce.getClass() = " + memberService.getClass());
        //when

        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberSerivceImpl.class);
    }

    @Test
    @DisplayName("구체 타입 이름으로 조회") // 해당은 역할에 의존해야 하는데 구현에 의존한 것으로 좋지는 않다
    public void findBeanByName2() throws Exception{
        //given
        MemberService memberService = ac.getBean("memberService", MemberSerivceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberSerivce.getClass() = " + memberService.getClass());
        //when

        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberSerivceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X") // 해당은 역할에 의존해야 하는데 구현에 의존한 것으로 좋지는 않다
    public void findBeanByNameX() throws Exception{
        //given
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }


}
