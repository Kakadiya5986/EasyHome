package com.projects.easyHome.controller;
import com.projects.easyHome.repository.PropertyAddressRepository;
import com.projects.easyHome.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyAddressController {
    @Autowired
    //autowire
    private PropertyAddressRepository addressRepository;

    @Autowired
    private PropertyService service;
}
