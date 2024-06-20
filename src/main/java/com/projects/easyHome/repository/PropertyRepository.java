package com.projects.easyHome.repository;

import com.projects.easyHome.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Integer> {

}
