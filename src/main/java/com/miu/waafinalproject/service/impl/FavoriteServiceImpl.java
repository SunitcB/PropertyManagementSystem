package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.domain.Favorite;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.responseDTO.AddressResponseModel;
import com.miu.waafinalproject.model.responseDTO.FavoriteListResponseModel;
import com.miu.waafinalproject.model.responseDTO.PropertyListResponseModel;
import com.miu.waafinalproject.repository.FavoriteRepo;
import com.miu.waafinalproject.repository.PropertyRepo;
import com.miu.waafinalproject.service.FavoriteService;
import com.miu.waafinalproject.service.FileStorageService;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.PropertyImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private ResponseModel responseModel;
    private final UserService userService;
    private final FavoriteRepo favoriteRepo;
    private final FileStorageService storageService;
    private final PropertyRepo propertyRepo;
    private final PropertyImageUtil imageUtil;

    @Override
    public ResponseModel getAll() {
        responseModel = new ResponseModel();
        List<PropertyListResponseModel> responseObj = new ArrayList<>();

        favoriteRepo.findAllByUsers(userService.getLoggedInUser()).forEach(x -> {
            try {
                responseObj.add(
                        new FavoriteListResponseModel(
                                new PropertyListResponseModel(
                                        x.getProperties().getId(),
                                        x.getProperties().getTitle(),
                                        x.getProperties().getPropertyDetail().getDescription(),
                                        x.getProperties().getPrice(),
                                        new AddressResponseModel(x.getProperties().getAddress()).toString(),
                                        imageUtil.imageToBase64(storageService.load(x.getId().toString()).getFile()),
                                        x.getProperties().getPropertyOption().getType(),
                                        x.getProperties().getPropertyDetail().getBed(),
                                        x.getProperties().getPropertyDetail().getBath(),
                                        x.getProperties().getBuiltYear(),
                                        x.getProperties().getPropertyStatus(),
                                        x.getProperties().getPropertyView().stream().count()
                                )
                        ).getProperty());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setData(responseObj);
        return responseModel;
    }

    @Override
    public ResponseModel favorite(UUID id) {
        responseModel = new ResponseModel();
        Favorite fav = favoriteRepo.findByUsersAndProperties_Id(userService.getLoggedInUser(), id);
        if (fav != null) {
            favoriteRepo.deleteById(fav.getId());
            responseModel.setStatus(HttpStatus.OK);
            responseModel.setMessage("Property has been removed from favorite successfully.");
        } else {
            Favorite favorite = new Favorite();
            favorite.setUsers(userService.getLoggedInUser());
            favorite.setProperties(propertyRepo.findById(id).get());
            favoriteRepo.save(favorite);
            responseModel.setStatus(HttpStatus.OK);
            responseModel.setMessage("Property has been favorite successfully.");
        }
        return responseModel;
    }

    @Override
    public ResponseModel deleteById(Long id) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        favoriteRepo.deleteById(id);
        responseModel.setMessage("Property has been removed from favorite successfully.");
        return responseModel;
    }
}
