package hello.core.order;

public interface OrderSerivce {

    Order createOrder(Long memberId, String ItemName, int itemPrice);

}
