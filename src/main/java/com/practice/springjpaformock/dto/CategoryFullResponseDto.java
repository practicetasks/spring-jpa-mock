package com.practice.springjpaformock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Категория с характеристиками
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO для отображения полной информации о категории")
public class CategoryFullResponseDto {
    @Schema(description = "Идентификатор категории", example = "1")
    Integer id;

    @Schema(description = "Название категории", example = "Электроника")
    String name;

    @Schema(description = "Список характеристик категории")
    List<AttributeDto> attributes;

    /**
     * Характеристика категорий
     */
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(description = "DTO для характеристики категории")
    public static class AttributeDto {
        @Schema(description = "Идентификатор характеристики", example = "10")
        Integer id;

        @Schema(description = "Название характеристики", example = "Цвет")
        String name;
    }
}
