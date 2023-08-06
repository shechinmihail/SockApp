package ru.skypro.testtask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.skypro.testtask.model.dto.SocksDTO;
import ru.skypro.testtask.model.entity.Socks;

/**
 * Интерфейс SocksMapper
 * используется для преобразования полей SocksDTO с сущностью Socks
 */
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SocksMapper {

    Socks socksDtoToSocks(SocksDTO socksDTO);
    SocksDTO socksToSocksDto(Socks socks);
}
