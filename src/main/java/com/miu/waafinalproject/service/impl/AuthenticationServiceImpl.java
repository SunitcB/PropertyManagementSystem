package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.config.PropertyUserDetailService;
import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.LoginRequestModel;
import com.miu.waafinalproject.model.responseDTO.LoginResponseModel;
import com.miu.waafinalproject.repository.UsersRepo;
import com.miu.waafinalproject.service.AuthenticationService;
import com.miu.waafinalproject.utils.EmailSenderUtil;
import com.miu.waafinalproject.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authManager;
    private final PropertyUserDetailService propertyUserDetailService;
    private final UsersRepo usersRepo;
    private final JwtUtil jwtUtil;
    private ResponseModel responseModel;
    private final EmailSenderUtil emailSenderUtil;

    @Override
    public ResponseModel authenticateUser(LoginRequestModel loginRequestModel) {
        Authentication authentication = null;
        responseModel = new ResponseModel();
        System.out.println(loginRequestModel);
        try {
            authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestModel.getUsername(), loginRequestModel.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException(ex.getMessage());
        }

        UserDetails userDetails = propertyUserDetailService.loadUserByUsername(loginRequestModel.getUsername());
        String accessToken = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        LoginResponseModel loginResponseObj = new LoginResponseModel(accessToken, refreshToken);
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setData(loginResponseObj);
        return responseModel;
    }

    @Override
    public ResponseModel forgotPassword(String userName) {
        responseModel = new ResponseModel();
        Users userObj = usersRepo.findByUsername(userName);
        if (userObj == null) {
            responseModel.setStatus(HttpStatus.UNAUTHORIZED);
            responseModel.setMessage("The user doesn't exist in our database.");
        } else {
            responseModel.setData(jwtUtil.generateForgotToken(userName));
            responseModel.setStatus(HttpStatus.OK);
            emailSenderUtil.sendSimpleEmail(userObj.getEmail(), "Reset password on SRNA property portal", "Dear " + userObj.getFirstName() + ",\n" +
                    "To reset your password click the link below:\n" +
                    "http://localhost:3000/reset-password/" + responseModel.getData() + "\n" +
                    "\n" +
                    "If you did not request a password reset from SRNA portal, you can safely ignore this email.\n" +
                    "\n" +
                    "Yours truly,\n" +
                    "The SRNA team");
        }

        return responseModel;
    }

    @Override
    public ResponseModel resetPassword(LoginRequestModel forgotModel, String token) {
        responseModel = new ResponseModel();
        String userName = jwtUtil.extractUsername(token);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(userName);
        Users userObj = usersRepo.findByUsername(userName);
        if (userObj != null) {
            userObj.setPassword(new BCryptPasswordEncoder().encode(forgotModel.getPassword()));
            usersRepo.save(userObj);
            responseModel.setStatus(HttpStatus.OK);
            responseModel.setMessage("Your password has been reset successfully");
        } else {
            responseModel.setStatus(HttpStatus.UNAUTHORIZED);
            responseModel.setMessage("The user doesn't exist in our database.");
        }
        return responseModel;
    }
}
