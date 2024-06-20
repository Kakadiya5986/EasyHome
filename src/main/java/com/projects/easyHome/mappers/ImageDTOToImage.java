package com.projects.easyHome.mappers;

import com.projects.easyHome.dto.PropertyImageDTO;
import com.projects.easyHome.model.PropertyImages;
import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ImageDTOToImage implements Converter<PropertyImageDTO, PropertyImages> {

    @Synchronized
    @Nullable
    @Override
    public PropertyImages convert(PropertyImageDTO source) {
        if (source == null) {
            return null;
        }
        final PropertyImages image = new PropertyImages();
        image.setId(source.getId());
        image.setName(source.getName());
        image.setType(source.getType());
        image.setImage_data(source.getImage_data());
       /* if(source.getPropertyId() != null)
        {
            Property property = new Property();
            property.setProperty_id(source.getPropertyId());
            image.setProperty(property);
            property.addImage(image);

        }*/
        return image;
    }
}
