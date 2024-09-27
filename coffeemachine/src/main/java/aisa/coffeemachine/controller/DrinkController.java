package aisa.coffeemachine.controller;

import aisa.coffeemachine.dto.drinks.DrinkRequest;
import aisa.coffeemachine.dto.drinks.DrinkResponse;
import aisa.coffeemachine.service.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffeemachine/drinks")
@Tag(name = "Drink controller", description = "Controller for manage drinks")
public class DrinkController {

    private final DrinkService drinkService;

    @GetMapping()
    @Operation(
            summary = "Get all drinks",
            description = "That's method use when you wanna get all drinks"
    )
    public List<DrinkResponse> getAll() {
        return drinkService.getAllDrinks();
    }

    @GetMapping("/byName")
    @Operation(
            summary = "Get drink by name",
            description = "Use this method if you wanna get drink by drink name"
    )
    public DrinkResponse byName(
            @RequestParam("name") @Parameter(description = "Drink name")String name) {
        return drinkService.getDrinkResponseByName(name);
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create drink",
            description = "Use this method when you wanna add new drink with new recipe"
    )
    public void create(@RequestBody DrinkRequest drinkRequest) {
        drinkService.addNewDrink(drinkRequest);
    }
}
