package ru.skypro.testtask.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.testtask.model.entity.Socks;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс SocksRepository
 * для работы с БД (для носков)
 */

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {

    List<Socks> findAll();

    Optional<Socks> findDistinctByColorAndCottonPart(String color, Integer CottonPart);

    List<Socks> findByColorIgnoreCaseAndCottonPart(String color, Integer cottonPart);
}
