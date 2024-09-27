package aisa.coffeemachine.service;

import aisa.coffeemachine.dto.statistic.StatisticResponse;
import aisa.coffeemachine.domain.Order;

import java.text.ParseException;

public interface StatisticService {
    void createStat(Order order);
    void removeData() throws ParseException;
    StatisticResponse mostPopularByOrder();
    StatisticResponse mostPopularByQuantity();

}
