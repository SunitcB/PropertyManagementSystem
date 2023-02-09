package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.domain.PropertyApplication;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.responseDTO.AddressResponseModel;
import com.miu.waafinalproject.model.responseDTO.OwnerPropertyListResponseModel;
import com.miu.waafinalproject.model.responseDTO.PropertyApplicationResponseModel;
import com.miu.waafinalproject.repository.PropertyApplicationRepo;
import com.miu.waafinalproject.repository.PropertyRepo;
import com.miu.waafinalproject.service.OwnerService;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.PropertyImageUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private ResponseModel responseModel;
    private final EntityManager entityManager;
    private final PropertyImageUtil imageUtil;
    private final PropertyRepo propertyRepo;
    private final UserService userService;
    private final PropertyApplicationRepo applicationRepo;

    @Override
    public ResponseModel getAllOwnedPropertyList() {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        List<OwnerPropertyListResponseModel> propertyList = new ArrayList<>();
        propertyRepo.findAllByOwner(userService.getLoggedInUser()).forEach(p ->
                {
                    propertyList.add(new OwnerPropertyListResponseModel(
                            p.getId(),
                            p.getTitle(),
                            p.getPropertyDetail().getDescription(),
                            p.getPrice(),
                            new AddressResponseModel(p.getAddress()).toString(),
                            p.getPropertyOption().getType(),
                            p.getPropertyDetail().getBed(),
                            p.getPropertyDetail().getBath(),
                            p.getBuiltYear(),
                            p.getApplications().size(),
                            p.getPropertyView().size(),
                            p.getPropertyStatus(),
                            p.getIsActive()
                    ));
                }
        );
        responseModel.setData(propertyList);
        return responseModel;
    }

    @Override
    public ResponseModel getAllOwnedPropertyApplicationList(HashMap<String, Object> filters) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        List<PropertyApplicationResponseModel> propertyList = new ArrayList<>();
        List<PropertyApplication> propertyApplicationList = new ArrayList<>();
        if (filters == null) {
            applicationRepo.findAllByProperty_Owner(userService.getLoggedInUser()).forEach(propertyApplicationList::add);
        } else {
            propertyApplicationList = filterProperties(filters);
        }
        propertyApplicationList.forEach(p ->
                {
                    propertyList.add(new PropertyApplicationResponseModel(
                            p.getId(),
                            p.getStatus(),
                            p.getRemarks(),
                            p.getOfferPrice(),
                            p.getUsers().getUserFullName(),
                            p.getProperty().getTitle(),
                            p.getProperty().getOwner().getUserFullName()
                    ));
                }
        );
        responseModel.setData(propertyList);
        return responseModel;
    }

    @Override
    public List<PropertyApplication> filterProperties(HashMap<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(PropertyApplication.class);

        List<Predicate> predicateList = new ArrayList<>();
        Root<PropertyApplication> application = criteriaQuery.from(PropertyApplication.class);
        if (filters.get("submissionDate") != null) {
            predicateList.add(criteriaBuilder.equal(application.get("submissionDate"), filters.get("submissionDate")));
        }
        if (filters.get("location") != null) {
            predicateList.add(criteriaBuilder.equal(application.get("property").get("address").get("state"), filters.get("location")));
        }
        if (filters.get("title") != null) {
            predicateList.add(criteriaBuilder.equal(application.get("property").get("title"), filters.get("title")));
        }

        criteriaQuery.where(predicateList.toArray(new Predicate[0]));

        TypedQuery<PropertyApplication> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
