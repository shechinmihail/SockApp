package ru.skypro.testtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение NotFoundException
 * Выпадает, когда в базе данных нет достаточного количества носков
 * Исключение унаследовано от {@link RuntimeException}.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
