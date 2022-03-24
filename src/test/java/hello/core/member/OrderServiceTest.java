package hello.core.member;

import hello.core.AppConfig;
import hello.core.order.Order;
import hello.core.order.OrderSerivce;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderSerivce orderSerivce;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderSerivce = appConfig.orderService();
    }

    @Test // 단위 테스트 - spring 없이 순수하게 java code로 test
    public void createOrder() throws Exception{
        //given
        long memberId = 1L;
        Member member = new Member(memberId, "A", Grade.VIP);
        memberService.join(member);
        //when
        Order order = orderSerivce.createOrder(memberId, "itemA", 10000);
        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
