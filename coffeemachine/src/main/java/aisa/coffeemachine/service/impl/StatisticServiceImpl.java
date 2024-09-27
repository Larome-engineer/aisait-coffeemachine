package aisa.coffeemachine.service.impl;

import java.util.*;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.stream.Collectors;

import aisa.coffeemachine.domain.Drink;
import aisa.coffeemachine.domain.Order;
import aisa.coffeemachine.domain.Statistic;
import aisa.coffeemachine.exception.ScheduleException;
import aisa.coffeemachine.exception.SomethingWentWrong;
import aisa.coffeemachine.service.DrinkService;
import aisa.coffeemachine.service.StatisticService;
import aisa.coffeemachine.repository.StatisticRepository;
import aisa.coffeemachine.dto.statistic.StatisticResponse;
import aisa.coffeemachine.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticServiceImpl implements StatisticService {

    private final DrinkService drinkService;
    private final StatisticRepository statisticRepository;

    @Override
    public void createStat(Order order) {
        try {
            statisticRepository.save(
                    Statistic.builder()
                            .drink(order.getDrink())
                            .quantity(order.getQuantity())
                            .startStat(new Date())
                            .build()
            );
        } catch (SomethingWentWrong wentWrong) {
            throw new ResourceNotFoundException("With statistic save something went wrong");
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void removeData() {
        try {
            LocalDate fiveYears = LocalDate.now().minusYears(5);
            Date dateToDelete = Date.from(fiveYears.atStartOfDay(ZoneId.systemDefault()).toInstant());
            statisticRepository.deleteByDate(dateToDelete);
        } catch (ScheduleException e) {
            throw new ScheduleException("Error with schedule: " + e.getMessage());
        }
    }

    @Override
    public StatisticResponse mostPopularByQuantity() {
        Optional<Map<Integer, Integer>> statistic = Optional.ofNullable(statisticRepository.getMostPopularDrinkByQuantity());
        statistic.orElseThrow(() -> new ResourceNotFoundException("With getting statistic by quantity something went wrong"));

        Map<String, Integer> countDrink = statistic.get().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> String.valueOf(entry.getKey()),
                        Map.Entry::getValue
                ));

        Drink drink = drinkService.getById(countDrink.get("stat_drink_id"));
        return new StatisticResponse(
                drink.getDrinkName(),
                countDrink.get("total_quantity")
        );
    }


    @Override
    public StatisticResponse mostPopularByOrder() {
        Optional<Map<Integer, Integer>> statistic = Optional.ofNullable(statisticRepository.getMostPopularDrinkByOrder());
        statistic.orElseThrow(() -> new ResourceNotFoundException("With getting statistic by quantity something went wrong"));

        Map<String, Integer> countDrink = statistic.get().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> String.valueOf(entry.getKey()),
                        Map.Entry::getValue
                ));

        Drink drink = drinkService.getById(countDrink.get("stat_drink_id"));
        return new StatisticResponse(
                drink.getDrinkName(),
                countDrink.get("total_count")
        );
    }
}
