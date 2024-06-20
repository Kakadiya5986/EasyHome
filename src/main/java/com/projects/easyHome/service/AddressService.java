package com.projects.easyHome.service;

import com.projects.easyHome.model.PropertyAddress;
import com.projects.easyHome.repository.PropertyAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private PropertyAddressRepository propertyAddressRepository;
    public void updateAddress(PropertyAddress propertyAddress)
    {
        propertyAddressRepository.save(propertyAddress);
    }
}
