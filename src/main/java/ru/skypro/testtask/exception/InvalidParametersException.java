package ru.skypro.testtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение InvalidParametersException
 * Выпадает, когда в базе данных не найден объект
 * Исключение унаследовано от {@link RuntimeException}.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidParametersException extends RuntimeException {

    public InvalidParametersException(){
        super("Объект по данным параметрам не найден");
    }
}
