package aisa.coffeemachine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import aisa.coffeemachine.service.StatisticService;
import aisa.coffeemachine.dto.statistic.StatisticResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffeemachine/stat")
@RequiredArgsConstructor
@Tag(name = "Statistic controller", description = "Controller for manage statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/mostByQuantity")
    @Operation(
            summary = "Get most popular drink by QUANTITY",
            description = "Use this if you wanna know drink most popular by value of QUANTITY"
    )
    public StatisticResponse mostPopularByQuantity() {
        return statisticService.mostPopularByQuantity();
    }

    @GetMapping("/mostByOrder")
    @Operation(
            summary = "Get most popular drink by ORDER",
            description = "Use this if you wanna know drink most popular by value of ORDER"
    )
    public StatisticResponse mostPopularByOrder() {
        return statisticService.mostPopularByOrder();
    }
}
