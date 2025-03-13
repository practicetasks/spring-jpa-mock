package com.practice.springjpaformock.mapper;

import com.practice.springjpaformock.dto.CategoryCreateDto;
import com.practice.springjpaformock.dto.CategoryFullResponseDto;
import com.practice.springjpaformock.dto.CategoryResponseDto;
import com.practice.springjpaformock.model.Attribute;
import com.practice.springjpaformock.model.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "attributes", target = "attributes", qualifiedByName = "mapAttributes")
    Category fromCreateDto(CategoryCreateDto categoryCreateDto);

    CategoryFullResponseDto toFullResponse(Category category);

    List<CategoryResponseDto> toResponse(List<Category> categories);

    @Named("mapAttributes")
    default List<Attribute> mapAttributes(List<String> attributes) {
        return attributes.stream()
                .map(attributeName -> {
                    Attribute attribute = new Attribute();
                    attribute.setName(attributeName);
                    return attribute;
                })
                .toList();
    }

    @AfterMapping
    default void setCategory(@MappingTarget Category category) {
        if (category.getAttributes() != null) {
            category.getAttributes().forEach(attribute -> attribute.setCategory(category));
        }
    }
}
