package com.airport.security;

/**
 * Strategy for accessing useful information about the current security context.
 */
public interface SecurityContextAccessor {
    boolean isCurrentAuthenticationAnonymous();
}
