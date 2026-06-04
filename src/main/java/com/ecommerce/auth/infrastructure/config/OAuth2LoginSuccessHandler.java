package com.ecommerce.auth.infrastructure.config;

import com.ecommerce.auth.domain.model.AuthUser;
import com.ecommerce.auth.domain.port.AuthInputPort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthInputPort googleLoginUseCase;
    private final String frontendUrl;

    public OAuth2LoginSuccessHandler(@Qualifier("googleLoginUseCase") AuthInputPort googleLoginUseCase,
                                     @Value("${app.frontend-url:http://localhost:4200}") String frontendUrl) {
        this.googleLoginUseCase = googleLoginUseCase;
        this.frontendUrl = frontendUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String email = oauthToken.getPrincipal().getAttribute("email");
        String name = oauthToken.getPrincipal().getAttribute("name");
        String googleId = oauthToken.getPrincipal().getAttribute("sub");

        AuthUser authUser = googleLoginUseCase.loginWithGoogle(email, name, googleId);

        response.sendRedirect(frontendUrl + "/auth/callback?token=" + authUser.getToken());
    }
}
