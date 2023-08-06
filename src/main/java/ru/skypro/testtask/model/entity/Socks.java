package ru.skypro.testtask.model.entity;

import javax.persistence.*;

import lombok.*;


/**
 * Класс Socks, представляет сущность носки.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "socks")
public class Socks {

    /**
     * Идентификационный номер (id) партии носков
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    /**
     * Название носков
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Цвет носков
     */
    @Column(name = "color", nullable = false)
    private String color;

    /**
     * Содержание хлопка в составе носков
     */
    @Column(name = "cotton_part", nullable = false)
    private int cottonPart;

    /**
     * Количество пар носков
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}
