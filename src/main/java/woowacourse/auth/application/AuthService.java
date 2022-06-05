package woowacourse.auth.application;

import org.springframework.stereotype.Service;
import woowacourse.auth.dto.TokenResponse;
import woowacourse.auth.support.JwtTokenProvider;
import woowacourse.common.exception.LoginException;
import woowacourse.common.exception.dto.ErrorResponse;
import woowacourse.shoppingcart.dao.CustomerDao;
import woowacourse.shoppingcart.domain.customer.Customer;

@Service
public class AuthService {
    private final CustomerDao customerDao;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(CustomerDao customerDao, JwtTokenProvider jwtTokenProvider) {
        this.customerDao = customerDao;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public TokenResponse login(String email, String password) {
        final Customer customer = customerDao.getByEmail(email);
        if (customer.isDifferentPassword(password)) {
            throw new LoginException("패스워드가 일치하지 않습니다.", ErrorResponse.LOGIN_FAIL);
        }

        final String token = jwtTokenProvider.createToken(customer.getEmail());
        return new TokenResponse(token);
    }
}