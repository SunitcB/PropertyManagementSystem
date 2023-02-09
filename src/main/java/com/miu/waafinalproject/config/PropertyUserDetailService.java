package com.miu.waafinalproject.config;

import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyUserDetailService implements UserDetailsService {
    private final UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userObj = usersRepo.findByUsername(username);
        PropertyUserDetails userDetails = new PropertyUserDetails(userObj.getUsername(), userObj.getPassword(), userObj.getRoles(), (userObj.getIsActive() && userObj.getIsVerified()));
        return userDetails;
    }
}
