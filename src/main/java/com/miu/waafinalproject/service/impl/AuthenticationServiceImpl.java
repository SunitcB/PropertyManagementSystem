package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.config.PropertyUserDetailService;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.LoginRequestModel;
import com.miu.waafinalproject.model.responseDTO.LoginResponseModel;
import com.miu.waafinalproject.service.AuthenticationService;
import com.miu.waafinalproject.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authManager;
    private final PropertyUserDetailService propertyUserDetailService;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseModel authenticateUser(LoginRequestModel loginRequestModel) {
        Authentication authentication = null;
        ResponseModel responseModel = new ResponseModel();
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
}
