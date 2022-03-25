package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류 발생")
    public void findBeanByParentTypeDuplicate() throws Exception{
        //given
        assertThrows(NoUniqueBeanDefinitionException.class, ()->ac.getBean(DiscountPolicy.class));
        //when
        
        //then
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 빈 이름을 지정하면 된다")
    public void findBeanByParentTypeBeanName() throws Exception{
        //given
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
        //when

        //then
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 하위 타입 즉 구체 타입으로 바로 지정")
    public void findBeanBySubType() throws Exception{
        //given
        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
        //when

        //then
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    public void findBeanByParentType() throws Exception{
        //given
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String s : beansOfType.keySet()) {
            System.out.println("key = " + s + "value = " + beansOfType.get(s));
        }
        //when

        //then
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    public void findBeanByObjectType() throws Exception{
        //given
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("key = " + s + "value = " + beansOfType.get(s));
        }

        //when

        //then
    }


        
    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
