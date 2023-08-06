package ru.skypro.testtask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.testtask.exception.InvalidParametersException;
import ru.skypro.testtask.model.dto.SocksDTO;
import ru.skypro.testtask.model.dto.SocksQuantity;
import ru.skypro.testtask.model.entity.Socks;
import ru.skypro.testtask.service.SocksService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Контроллер SocksController
 * Контроллер для обработки REST-запросов, в данном случае приход, отпуск и получение общего количества носков на складе
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/socks")
public class SocksController {

    /**
     * Поле сервиса носков
     */
    private final SocksService socksService;

    /**
     * Функция регистрации прихода носков на склад
     *
     * @param socksDTO Данные носков
     * @return Возвращает объект, содержащий данные нового прихода носков
     */
    @Operation(
            summary = "Регистрация прихода носков на склад",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удалось добавить приход",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Параметры запроса отсутствуют или имеют некорректный формат",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class))
                    )
            }
    )
    @PostMapping("/income")
    public ResponseEntity<SocksDTO> arrivalOfSocksToTheWarehouse(@Valid @RequestBody SocksDTO socksDTO) {
        return ResponseEntity.ok(socksService.arrivalOfSocksToTheWarehouse(socksDTO));
    }

    /**
     * Функция регистрации отпуска носков со склада
     *
     * @param socksDTO Данные носков
     * @return Возвращает объект, содержащий данные об отпуске носков
     */
    @Operation(
            summary = "Регистрация отпуска носков со склада",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удалось отпустить со склада",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Параметры запроса отсутствуют или имеют некорректный формат",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class))
                    )
            }
    )
    @PostMapping("/outcome")
    public ResponseEntity<SocksDTO> releaseOfSocksFromTheWarehouse(@Valid @RequestBody SocksDTO socksDTO) {
        return ResponseEntity.ok(socksService.releaseOfSocksFromTheWarehouse(socksDTO));
    }

    /**
     * Функция получения общего количества носков на складе, по переданному запросу
     *
     * @param color      Цвет носков
     * @param operation  Операция
     * @param cottonPart Содержания хлопка в составе носков
     * @return Возвращает объект по заданным параметрам
     */
    @Operation(
            summary = "Получение общего количества носков на складе, по переданному запросу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Запрос выполнен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Параметры запроса отсутствуют или имеют некорректный формат",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Произошла ошибка",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class))
                    )
            }
    )
    @GetMapping
    public SocksQuantity findSocksByColorAndOperationAndCottonPart(@RequestParam(required = false) String color,
                                                                   @RequestParam(required = false) String operation,
                                                                   @Valid @RequestParam(required = false) Integer cottonPart) {
        try {
            return socksService.findSocksByColorAndOperationAndCottonPart(color, operation, cottonPart);
        } catch (RuntimeException e) {
            throw new InvalidParametersException();
        }
    }

    /**
     * Функция возвращает все носки на складе
     *
     * @return Возвращает коллекцию носков
     */
    @Operation(
            summary = "Получение общего количества носков на складе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Запрос выполнен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Socks.class)
                            )
                    )
            }
    )
    @GetMapping("/list")
    public ResponseEntity<Collection<SocksDTO>> getAllSocks() {
        log.info("Вызван метод получения списка всех носков на складе");
        return ResponseEntity.ok(socksService.getAllSocks());
    }

}

