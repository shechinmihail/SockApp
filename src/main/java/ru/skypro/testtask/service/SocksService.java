package ru.skypro.testtask.service;


import org.springframework.stereotype.Service;
import ru.skypro.testtask.model.dto.SocksDTO;
import ru.skypro.testtask.model.dto.SocksQuantity;

import java.util.Collection;

/**
 * Сервис SocksService
 * Сервис для учета прихода, отпуска носков и получения количества носков из базы данных по параметрам.
 */
@Service
public interface SocksService {

    Collection<SocksDTO> getAllSocks();
    SocksDTO arrivalOfSocksToTheWarehouse(SocksDTO socksDTO);
    SocksDTO releaseOfSocksFromTheWarehouse(SocksDTO socksDTO);
    SocksQuantity findSocksByColorAndOperationAndCottonPart(String color, String operation, Integer cottonPart);
}
