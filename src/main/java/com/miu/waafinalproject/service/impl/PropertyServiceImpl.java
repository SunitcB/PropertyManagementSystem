package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.domain.Address;
import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.PropertyDetail;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyRequestModel;
import com.miu.waafinalproject.model.responseDTO.AddressResponseModel;
import com.miu.waafinalproject.model.responseDTO.PropertyListResponseModel;
import com.miu.waafinalproject.model.responseDTO.PropertyResponseModel;
import com.miu.waafinalproject.repository.*;
import com.miu.waafinalproject.service.PropertyService;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.PropertyImageUtil;
import com.miu.waafinalproject.utils.enums.PropertyStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final EntityManager entityManager;
    private final PropertyRepo propertyRepo;
    private final PropertyDetailRepo propertyDetailRepo;
    private final AddressRepo addressRepo;
    private final PropertyOptionRepo propertyOptionRepo;
    private final PropertyTypeRepo propertyTypeRepo;
    private final FavoriteRepo favoriteRepo;
    private ResponseModel responseModel;
    private final UsersRepo usersRepo;
    private final UserService userService;
    private final PropertyImageUtil imageUtil;

    @Override
    public ResponseModel getAll(HashMap<String, Object> filters) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        List<PropertyListResponseModel> responseObj = new ArrayList<>();
        List<Property> propertyList = new ArrayList<>();
        if (filters == null) {
            propertyRepo.findAllByIsActive(true).forEach(propertyList::add);
        } else {
            propertyList = filterProperties(filters);
        }

        propertyList.forEach(x -> {
            try {
                responseObj.add(
                        new PropertyListResponseModel(
                                x.getId(),
                                x.getTitle(),
                                x.getPropertyDetail().getDescription(),
                                x.getPrice(),
                                new AddressResponseModel(x.getAddress()).toString(),
                                imageUtil.imageToBase64(),
                                x.getPropertyOption().getType(),
                                x.getPropertyDetail().getBed(),
                                x.getPropertyDetail().getBath(),
                                x.getBuiltYear(),
                                x.getPropertyStatus(),
                                x.getPropertyView().stream().count()
                        ));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });

        responseModel.setData(responseObj);
        return responseModel;
    }

    @Override
    public List<Property> filterProperties(HashMap<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Property.class);

        List<Predicate> predicateList = new ArrayList<>();
        Root<Property> property = criteriaQuery.from(Property.class);
        predicateList.add(criteriaBuilder.equal(property.get("isActive"), true));
        if (filters.get("price") != null) {
            predicateList.add(criteriaBuilder.equal(property.get("price"), filters.get("price")));
        }
        if (filters.get("roomSize") != null) {
            predicateList.add(criteriaBuilder.equal(property.get("propertyDetail").get("bed"), filters.get("roomSize")));
        }
        if (filters.get("location") != null) {
            predicateList.add(criteriaBuilder.equal(property.get("address").get("state"), filters.get("location")));
        }
        if (filters.get("propertyOption") != null) {
            predicateList.add(criteriaBuilder.equal(property.get("propertyOption").get("type"), filters.get("propertyOption")));
        }
        if (filters.get("propertyType") != null) {
            predicateList.add(criteriaBuilder.equal(property.get("propertyType").get("name"), filters.get("propertyType")));
        }
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));

        TypedQuery<Property> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public ResponseModel getById(UUID id) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        Property property = propertyRepo.findById(id).get();
        try {
            responseModel.setData(new PropertyResponseModel(
                    property.getId(),
                    property.getTitle(),
                    property.getPropertyDetail(),
                    (property.getPropertyOption() != null) ? property.getPropertyOption().getType() : null,
                    property.getPrice(),
                    (property.getPropertyType() != null) ? property.getPropertyType().getName() : null,
                    property.getAddress(),
                    (property.getPropertyView() != null) ? property.getPropertyView().stream().count() : 0,
                    imageUtil.imageToBase64(),
                    property.getBuiltYear(),
                    property.getPropertyStatus(),
                    userService.hasToken() ? false : favoriteRepo.findByUsersAndProperties(userService.getLoggedInUser(), property) != null));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return responseModel;
    }

    @Override
    public ResponseModel save(PropertyRequestModel requestModel) {
        responseModel = new ResponseModel();

        Property property = new Property();
        property.setTitle(requestModel.getTitle());
        PropertyDetail propertyDetail = new PropertyDetail(null, requestModel.getDescription(), requestModel.getBed(), requestModel.getBath(), requestModel.getHasBasement(), requestModel.getHasParking(), requestModel.getArea(), requestModel.getFeatures());
        property.setPropertyDetail(propertyDetailRepo.save(propertyDetail));
        Address address = new Address(null, requestModel.getStreet(), requestModel.getCity(), requestModel.getState(), requestModel.getZipcode());
        property.setAddress(addressRepo.save(address));
        property.setPrice(requestModel.getPrice());
        property.setBuiltYear(requestModel.getBuiltYear());
        property.setPropertyOption(propertyOptionRepo.findByType(requestModel.getPropertyOption()));
        property.setPropertyType(propertyTypeRepo.findByName(requestModel.getPropertyType()));
        property.setOwner(userService.getLoggedInUser());
        property.setPropertyStatus(requestModel.getPropertyStatus());
        propertyRepo.save(property);
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setMessage("Property has been saved successfully.");
        return responseModel;
    }

    @Override
    public ResponseModel showHideProperty(UUID propertyId, String action) {
        responseModel = new ResponseModel();
        Property property = propertyRepo.findById(propertyId).get();
        if (action == "show") {
            responseModel.setMessage("Property has been shown.");
            property.setIsActive(true);
        } else {
            property.setIsActive(false);
            responseModel.setMessage("Property has been hidden.");
        }
        propertyRepo.save(property);
        responseModel.setStatus(HttpStatus.OK);
        return responseModel;
    }

    @Override
    public ResponseModel update(UUID id, PropertyRequestModel requestModel) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        Property property = propertyRepo.findById(id).get();

        property.setId(id);
        property.setTitle(requestModel.getTitle());
        PropertyDetail propertyDetail = new PropertyDetail(null, requestModel.getDescription(), requestModel.getBed(), requestModel.getBath(), requestModel.getHasBasement(), requestModel.getHasParking(), requestModel.getArea(), requestModel.getFeatures());
        property.setPropertyDetail(propertyDetailRepo.save(propertyDetail));
        Address address = new Address(null, requestModel.getStreet(), requestModel.getCity(), requestModel.getState(), requestModel.getZipcode());
        property.setAddress(addressRepo.save(address));
        property.setPrice(requestModel.getPrice());
        property.setBuiltYear(requestModel.getBuiltYear());
        property.setPropertyOption(propertyOptionRepo.findByType(requestModel.getPropertyOption()));
        property.setPropertyType(propertyTypeRepo.findByName(requestModel.getPropertyType()));
        property.setPropertyStatus(requestModel.getPropertyStatus());
        propertyRepo.save(property);
        responseModel.setMessage("Property has been updated successfully.");
        return responseModel;
    }

    @Override
    public ResponseModel delete(UUID id) {
        responseModel = new ResponseModel();
        Property propertyObj = propertyRepo.findById(id).get();
        if (propertyObj.getPropertyStatus().equals(PropertyStatus.PENDING.toString())
                || propertyObj.getPropertyStatus().equals(PropertyStatus.CONTINGENT.toString())) {
            responseModel.setStatus(HttpStatus.NOT_ACCEPTABLE);
            responseModel.setMessage("Property has pending status so cannot be deleted.");
        } else {
            responseModel.setStatus(HttpStatus.OK);
            propertyRepo.deleteById(id);
            responseModel.setMessage("Property has been deleted successfully.");
        }
        return responseModel;
    }
}
