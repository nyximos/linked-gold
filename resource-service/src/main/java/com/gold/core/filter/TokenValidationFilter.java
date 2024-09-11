package com.gold.core.filter;

import com.gold.client.AuthClient;
import com.gold.core.handler.RequestHandler;
import com.gold.core.wrapper.TokenUser;
import com.nyximos.auth.Auth;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.gold.core.constant.ResourceConstants.HEADER_AUTHORIZE_TOKEN;
import static com.gold.core.constant.ResourceConstants.TOKEN_PREFIX;

@Component
@RequiredArgsConstructor
public class TokenValidationFilter implements Filter {

    private final AuthClient authClient;
    private final RequestHandler requestHandler;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (requestHandler.requiresAuthorization()) {
            String token = httpServletRequest.getHeader(HEADER_AUTHORIZE_TOKEN);

            if (token != null && token.startsWith(TOKEN_PREFIX + StringUtils.SPACE)) {
                try {
                    Auth.ValidateTokenResponse validateResponse = authClient.validateToken(token);
                    TokenUser tokenUser = new TokenUser(validateResponse.getId(), validateResponse.getUsername());
                    httpServletRequest.setAttribute("TokenUser", tokenUser);
                } catch (Exception e) {
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰 검증에 실패했습니다.");
                    return;
                }
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization 헤더가 없거나 유효하지 않습니다.");
                return;
            }
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}