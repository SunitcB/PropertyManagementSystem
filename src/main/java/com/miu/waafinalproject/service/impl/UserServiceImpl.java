package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.config.PropertyUserDetails;
import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.responseDTO.UserDetailsModel;
import com.miu.waafinalproject.repository.UsersRepo;
import com.miu.waafinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepo usersRepo;
    private ResponseModel responseModel;

    @Override
    public Users getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users loggedInUser = usersRepo.findByUsername(((PropertyUserDetails) authentication.getPrincipal()).getUsername());
        return loggedInUser;
    }

    @Override
    public ResponseModel getLoggedInUserDetails() {
        responseModel = new ResponseModel();
        Users userObj = getLoggedInUser();
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setData(
                new UserDetailsModel(
                        userObj.getEmail(),
                        userObj.getFirstName(),
                        userObj.getLastName(),
                        userObj.getMiddleName(),
                        userObj.getAddress(),
                        userObj.getPhone(),
                        userObj.getRoles(),
                        userObj.getUsername()
                )
        );
        return responseModel;
    }
}
