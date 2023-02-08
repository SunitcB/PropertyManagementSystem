package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.repository.UsersRepo;
import com.miu.waafinalproject.service.AdminService;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.enums.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final UsersRepo usersRepo;
    private ResponseModel responseModel;

    @Override
    public ResponseModel approveSignup(long userId) {
        responseModel = new ResponseModel();
        if (userService.checkIfCurrentUserHasRole(UserRoles.ADMIN.toString())) {
            Users userObj = usersRepo.findById(userId).get();
            userObj.setIsActive(true);
            usersRepo.save(userObj);
            responseModel.setStatus(HttpStatus.OK);
            responseModel.setMessage("User sign up has been approved");
        } else {
            responseModel.setStatus(HttpStatus.UNAUTHORIZED);
            responseModel.setMessage("You are not authorized to approve user sign up.");
        }
        return responseModel;
    }
}
