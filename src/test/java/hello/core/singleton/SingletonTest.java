package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링이 없는 순수한 DI 컨테이너")
    public void pureContainer() throws Exception{
        //given
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();
        //when

        //then
        System.out.println("memberservice1 = " + memberService1);
        System.out.println("memberservice2 = " + memberService2);

        // 서로 다른 객체가 생성 -> JVM 메모리에 계속 Stack 이 쌓임 -> 객체가 repository까지 4개가 생성됨

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }


    public static void main(String[] args){
//        SingletonService singletonService1 = new SingletonService(); // 이렇게 임의로 생성하는 것을 막기 위해서 private 생성자를 사용
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void SingletonServiceTest() throws Exception{
        //given
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //when

        //then
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        // same - 객체 인스턴스 비교
        // equl - equals 메소드 오버라이딩 된것 비교
    }

    // spring container를 쓰면 객체를 모두 싱글톤으로 만들어 관리해줌

    @Test
    @DisplayName("스프링 컨테이너와 테스트")
    public void springContainer() throws Exception{
        //given
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = annotationConfigApplicationContext.getBean("memberService", MemberService.class);
        MemberService memberService2 = annotationConfigApplicationContext.getBean("memberService", MemberService.class);

        //when
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //then

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

}
