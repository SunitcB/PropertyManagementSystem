package com.miu.waafinalproject.controller;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "Welcome to login screen";
    }

    @GetMapping("/homepage")
    public String homepage(Principal principal) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = token.getAccount().getKeycloakSecurityContext().getToken();
        return "Welcome to homepage, " + accessToken.getPreferredUsername() + " successfully logged in";
    }
}
