package aisa.coffeemachine.service;

import aisa.coffeemachine.dto.drinks.DrinkRequest;
import aisa.coffeemachine.dto.drinks.DrinkResponse;
import aisa.coffeemachine.domain.Drink;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DrinkService {
    List<DrinkResponse> getAllDrinks();
    DrinkResponse getDrinkResponseByName(String name);
    void addNewDrink(DrinkRequest drinkRequest);

    Drink getDrinkByName(String name);
    Drink getById(int drinkId);

}
