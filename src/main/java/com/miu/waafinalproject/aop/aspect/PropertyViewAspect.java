package com.miu.waafinalproject.aop.aspect;

import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.PropertyView;
import com.miu.waafinalproject.repository.PropertyRepo;
import com.miu.waafinalproject.repository.PropertyViewRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;
import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class PropertyViewAspect {
    private final PropertyViewRepo viewRepo;
    private final PropertyRepo propertyRepo;

    @Pointcut("@annotation(com.miu.waafinalproject.aop.aspect.HandleView)")
    public void handleView() {
    }

    @After("handleView()")
    public void setPropertyView(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteHost();
        final Map<String, String> pathVariables = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        UUID propertyId = UUID.fromString(pathVariables.get("id"));
        System.out.println("__________________________________________________________");
        System.out.println(ipAddress);
        Property property = propertyRepo.findById(propertyId).get();
        if (viewRepo.findByIpAddressAndProperty(ipAddress, property) == null) {
            PropertyView view = new PropertyView();
            view.setIpAddress(ipAddress);
            view.setProperty(property);
            viewRepo.save(view);
        }
    }
}
