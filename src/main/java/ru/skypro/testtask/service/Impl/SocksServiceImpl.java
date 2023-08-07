package ru.skypro.testtask.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.testtask.exception.InvalidParametersException;
import ru.skypro.testtask.exception.NotFoundException;
import ru.skypro.testtask.mapper.SocksMapper;
import ru.skypro.testtask.model.dto.SocksDTO;
import ru.skypro.testtask.model.dto.SocksQuantity;
import ru.skypro.testtask.model.entity.Socks;
import ru.skypro.testtask.repository.SocksRepository;
import ru.skypro.testtask.service.SocksService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис SocksServiceImpl
 * Сервис для учета прихода, отпуска носков и получения количества носков из базы данных по параметрам.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SocksServiceImpl implements SocksService {

    /**
     * Поле репозитория носков
     */
    private final SocksRepository socksRepository;
    /**
     * Поле маппинга носков
     */
    private final SocksMapper socksMapper;

    /**
     * Получение списка всех носков из базы данных
     *
     * @return список (коллекцию) носков
     */
    @Override
    public Collection<SocksDTO> getAllSocks() {
        log.info("Вызван метод получения списка всех носков на складе");
        return socksRepository.findAll().stream().map(socksMapper::socksToSocksDto).collect(Collectors.toList());
    }

    /**
     * Добавление прихода носков в базу данных
     *
     * @param socksDTO носки
     * @return Возвращает новое количество носков в базе данных
     */
    @Override
    public SocksDTO arrivalOfSocksToTheWarehouse(SocksDTO socksDTO) {
        log.info("Вызван метод добавления носков на склад");
        Socks socks = socksRepository.findDistinctByColorAndCottonPart(socksDTO.getColor(), socksDTO.getCottonPart())
                .orElse(new Socks(null, socksDTO.getColor(), socksDTO.getCottonPart(), 0));
        socks.setQuantity(socks.getQuantity() + socksDTO.getQuantity());
        socksRepository.save(socks);
        return socksMapper.socksToSocksDto(socks);
    }

    /**
     * Отпуск носков из базы данных
     *
     * @param socksDTO носки
     * @return Возвращает новое количество носков в базе данных
     */
    @Override
    public SocksDTO releaseOfSocksFromTheWarehouse(SocksDTO socksDTO) {
        log.info("Вызван метод отпуска носков со склада");
        Socks socks = socksRepository.findDistinctByColorAndCottonPart(socksDTO.getColor(), socksDTO.getCottonPart())
                .orElseThrow(InvalidParametersException::new);
        int quantity = socks.getQuantity() - socksDTO.getQuantity();
        if (quantity < 0) {
            throw new NotFoundException("Данных носков нет на складе");
        }
        socks.setQuantity(quantity);
        socksRepository.save(socks);
        return socksMapper.socksToSocksDto(socks);
    }

    /**
     * Получение количества носков по заданным параметрам
     *
     * @param color      Цвет носков
     * @param operation  Операция
     * @param cottonPart Содержания хлопка в составе носков
     * @return список количества носков по параметрам
     */
    @Override
    public SocksQuantity findSocksByColorAndOperationAndCottonPart(String color, String operation, Integer cottonPart) {
        log.info("Вызван метод получения общего количества носков на складе, по переданному запросу");
        List<Socks> socksList = socksRepository.findByColorIgnoreCaseAndCottonPart(color, cottonPart);
        int totalQuantity = 0;

        switch (operation) {
            case "moreThan":
                for (Socks socks : socksList) {
                    if (socks.getQuantity() > cottonPart) {
                        totalQuantity += socks.getQuantity();
                    }
                }
                break;

            case "lessThan":
                for (Socks socks : socksList) {
                    if (socks.getQuantity() < cottonPart) {
                        totalQuantity += socks.getQuantity();
                    }
                }
                break;

            case "equals":
                for (Socks socks : socksList) {
                    if (socks.getQuantity() == cottonPart) {
                        totalQuantity += socks.getQuantity();
                    }
                }
                break;

            default:
                throw new NotFoundException("Некорректная операция: " + operation);
        }
        return new SocksQuantity(totalQuantity);
    }
}
