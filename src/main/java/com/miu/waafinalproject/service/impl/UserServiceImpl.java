package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.config.PropertyUserDetails;
import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.UserRequestModel;
import com.miu.waafinalproject.model.responseDTO.UserDetailsModel;
import com.miu.waafinalproject.model.responseDTO.UserResponseModel;
import com.miu.waafinalproject.repository.RoleRepo;
import com.miu.waafinalproject.repository.UsersRepo;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.enums.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UsersRepo usersRepo;
    private final RoleRepo roleRepo;
    private ResponseModel responseModel;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
                        userObj.getRoles().stream().findFirst().get().getRoleName(),
                        userObj.getUsername()
                )
        );
        return responseModel;
    }

    @Override
    public ResponseModel getAll(Object filters) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        List<UserResponseModel> responseObj = new ArrayList<>();
        usersRepo.findAll();
        return null;
    }

    @Override
    public ResponseModel getById(Long id) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        Users user = usersRepo.findById(id).get();
        if (user.getIsDeleted()) {
            responseModel.setStatus(HttpStatus.BAD_REQUEST);
            responseModel.setMessage("User does not exist");
            return responseModel;
        }
        responseModel.setData(new UserResponseModel(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getAddress(),
                user.getUsername(),
                user.getPhone()));
        return responseModel;
    }

    @Override
    public ResponseModel save(UserRequestModel requestModel) {
        responseModel = new ResponseModel();
        Users user = new Users();
        user.setEmail(requestModel.getEmail());
        user.setFirstName(requestModel.getFirstName());
        user.setLastName(requestModel.getLastName());
        user.setMiddleName(requestModel.getMiddleName());
        user.setAddress(requestModel.getAddress());
        user.setPhone(requestModel.getPhone());
        user.setIsActive(requestModel.getUserRole().equals(UserRoles.CUSTOMER.toString()));
        user.setRoles(roleRepo.findAllByRoleName(requestModel.getUserRole()));
        user.setUsername(requestModel.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(requestModel.getPassword()));
        usersRepo.save(user);

        responseModel.setStatus(HttpStatus.OK);
        responseModel.setMessage("User has been saved successfully");
        return responseModel;
    }

    @Override
    public ResponseModel update(Long id, UserRequestModel requestModel) {
        responseModel = new ResponseModel();
        //Users user = new Users();
        Users user = usersRepo.findById(id).get();
        //user.setId(id);
        user.setEmail(requestModel.getEmail());
        user.setAddress(requestModel.getAddress());
        user.setPhone(requestModel.getPhone());
        user.setFirstName(requestModel.getFirstName());
        user.setLastName(requestModel.getLastName());
        user.setMiddleName(requestModel.getMiddleName());
        //user.setPassword(new BCryptPasswordEncoder().encode(requestModel.getPassword()));
        usersRepo.save(user);
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setMessage("User has been updated successfully");
        return responseModel;
    }

    @Override
    public ResponseModel delete(Long id) {
        responseModel = new ResponseModel();
        if (getLoggedInUser().getRoles().stream().findFirst().get().getRoleName().equals(UserRoles.ADMIN.toString())) {
            //usersRepo.deleteById(id);
            Users user = usersRepo.findById(id).get();
            user.setIsDeleted(true);
            usersRepo.save(user);
            responseModel.setMessage("User has been deleted successfully.");
            responseModel.setStatus(HttpStatus.OK);
        } else {
            responseModel.setStatus(HttpStatus.UNAUTHORIZED);
            responseModel.setMessage("You are not authorized to delete the users.");
        }
        return responseModel;
    }
}
