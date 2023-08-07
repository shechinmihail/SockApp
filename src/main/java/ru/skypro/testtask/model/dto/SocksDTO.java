package ru.skypro.testtask.model.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Носки
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SocksDTO {

    /**
     * Цвет носков
     */
    private String color;

    /**
     * Содержание хлопка в составе носков
     */
    @Max(value = 100, message = "Процент хлопка не может быть больше 100")
    private int cottonPart;

    /**
     * Количество пар носков
     */
    @Min(value = 1, message = "Количество пар носков должно быть больше 0")
    private Integer quantity;
}
