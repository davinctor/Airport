package com.airport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


//@Component
public class SecurityContextAccessorImpl implements SecurityContextAccessor {

    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    public SecurityContextAccessorImpl(AuthenticationTrustResolver trustResolver) {
        this.authenticationTrustResolver = trustResolver;
    }

    @Override
    public boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication == null ?
                true :
                authenticationTrustResolver.isAnonymous(authentication);
    }
}
