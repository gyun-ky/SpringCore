package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderSerivce {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 실제로 할인 정책을 변경하기 위해서는 해당 코드를 수정해야함
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // !!!DIP 위반!!! - 인터페이스에만 의존해라
    // 사실상 보면 OrderServiceImpl은 discountPolicy 뿐만 아니라 FixDiscountPolicy() 라는 구체 클래스에도 의존중이다
    // => discountPolicy를 바꾸는 순간 OrderServiceImpl class를 변경해야 함 -> OCP 위반 (변경하지 않고 확장 가능)

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 인터페이스로 의존 -> 하지만 null point exception 발생 -> 어떻게 해결할 수 있을까??????

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //test용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
