package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    @DisplayName("상태가 있는 ")
    public void statefulServiceSingleton() throws Exception{
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        //when

        //ThreadA : A사용자가 10000원 주문
        statefulService1.order("UserA", 10000);

        //ThreadB : B사용자가 20000원 주문
        statefulService2.order("UserB", 20000);

        //ThreadA : A사용자가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        // 웹에서 요청이 오게되면 각각에 thread 할당

       //then
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000); // 따라서 무조건 무상태로 만들어주어야 한다
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}
