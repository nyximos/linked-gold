package com.gold.core.handler;

import com.gold.core.constant.ResourceConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RequestHandler {

    private final HttpServletRequest request;

    public boolean requiresAuthorization() {
        return Objects.nonNull(this.request.getHeader(ResourceConstants.HEADER_AUTHORIZE_TOKEN));
    }

}
