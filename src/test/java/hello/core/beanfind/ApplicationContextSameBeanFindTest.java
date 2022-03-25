package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
    
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생")
    public void findBeanByTypeDuplicate() throws Exception{
        //given
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, ()-> ac.getBean(MemberRepository.class));
        // spring 입장에서는 난 뭘 선택해야 하지 하고 예외가 터진다
        //when
        
        //then
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, bean 이름으로 지정하면 된다")
    public void findBeanByName() throws Exception{
        //given
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);

        //when

        //then
    }

    @Test
    @DisplayName("특정타입 모두 조회하기")
    public void findBeanByType() throws Exception{
        //given
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = "+beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
        //when

        //then
    }

    @Configuration
    static class SameBeanConfig{
        // 파라미터가 다를 수 있기 때문에 instance type이 같을 수 있음
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
