package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.config.PropertyUserDetails;
import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.repository.UsersRepo;
import com.miu.waafinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepo usersRepo;

    @Override
    public Users getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users loggedInUser = usersRepo.findByUsername(((PropertyUserDetails) authentication.getPrincipal()).getUsername());
        return loggedInUser;
    }
}
