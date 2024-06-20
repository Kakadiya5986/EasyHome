package com.projects.easyHome.service;

import com.projects.easyHome.model.Property;
import com.projects.easyHome.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> listAll()
    {
        return propertyRepository.findAll();
    }

    public Property addProperty(Property property)
    {
        return propertyRepository.save(property);
    }

    public Property getProperty(Integer id)
    {
        return propertyRepository.findById(id).get();
    }


    public String delete(Integer id)
    {
        if(getProperty(id)!= null) {
            propertyRepository.deleteById(id);
            return "SUCCESS";
        }
        return "ERROR";
    }

    public Property updateProperty(Integer id , Property property)
    {
        if(getProperty(id)!= null) {
           return  propertyRepository.save(property);
        }return null;
    }
}
