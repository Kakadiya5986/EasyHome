package com.projects.easyHome.controller;
import com.projects.easyHome.dto.PropertyDTO;
import com.projects.easyHome.mappers.PropertyDTOToProperty;
import com.projects.easyHome.model.Property;
import com.projects.easyHome.model.PropertyImages;
import com.projects.easyHome.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class PropertyController {

    @Autowired
    private PropertyService service;


    @Autowired
    private PropertyDTOToProperty propertyDTOToProperty;

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> list() {

        return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/properties", consumes = {"multipart/form-data"}, produces = {"application/json"})
    public ResponseEntity<Property> addProperty(@RequestPart("property") @Valid PropertyDTO propertyDTO,
                                                @RequestPart("file") MultipartFile[] files) {
        try {

            Set<PropertyImages> propertyImages = new HashSet<>();
            Property property = propertyDTOToProperty.convert(propertyDTO);

            for (MultipartFile file : files) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                PropertyImages image = new PropertyImages(fileName, file.getContentType(), file.getBytes());
                property.addImage(image);
                //image.setProperty(property);
            }

            service.addProperty(property);
            return new ResponseEntity<>(property, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping(value = "/properties/{propertyId}")
    public ResponseEntity<HttpStatus> removeProperty(@PathVariable int propertyId) {
        if ("SUCCESS".equalsIgnoreCase(service.delete(propertyId))) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @PutMapping(value = "/properties/{propertyId}/update", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Property> updateProperty(@PathVariable int propertyId, @RequestBody Property property) {
        return new ResponseEntity<>(service.updateProperty(propertyId, property), HttpStatus.NO_CONTENT);

    }
}
