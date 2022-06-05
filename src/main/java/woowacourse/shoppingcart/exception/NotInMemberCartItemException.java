package woowacourse.shoppingcart.exception;

public class NotInMemberCartItemException extends RuntimeException {
    public NotInMemberCartItemException() {
        this("장바구니 아이템이 없습니다.");
    }

    public NotInMemberCartItemException(final String msg) {
        super(msg);
    }
}
