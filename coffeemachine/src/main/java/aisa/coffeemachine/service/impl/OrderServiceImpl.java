package aisa.coffeemachine.service.impl;

import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import aisa.coffeemachine.domain.*;
import aisa.coffeemachine.service.*;
import aisa.coffeemachine.mappers.Mapper;
import aisa.coffeemachine.dto.order.OrderRequest;
import aisa.coffeemachine.dto.order.OrderResponse;
import aisa.coffeemachine.repository.OrderRepository;
import aisa.coffeemachine.exception.SomethingWentWrong;
import aisa.coffeemachine.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final Mapper mapper;
    private final OrderRepository orderRepository;

    private final DrinkService drinkService;
    private final StatisticService statisticService;
    private final InventoryService inventoryService;
    private final RecipeInventoryService recipeInventoryService;

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return checkOnEmpty(orders, "Orders not exists");
    }

    @Override
    public List<OrderResponse> getOrderByDrink(String drinkName) { // Сделать более красивый вывод данных
        List<Order> orders = orderRepository.findByDrinkName(drinkName);
        return checkOnEmpty(orders, "No orders with that drinks");
    }

    @Override
    public List<OrderResponse> getAllOrdersByDate(String date) throws ParseException { // Сделать более красивый вывод данных
        try {
            Date dateToSearch = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            List<Order> orders = orderRepository.findByDate(dateToSearch);
            return checkOnEmpty(orders, "No orders on that date");
        } catch (SomethingWentWrong wentWrong) {
            throw new ResourceNotFoundException("Something went wrong");
        }
    }

    @Override
    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        Drink drink = drinkService.getDrinkByName(orderRequest.drinkName());
        if (drink == null) {
            throw new ResourceNotFoundException("Drink with name: " + orderRequest.drinkName() + " not found");
        }

        Order order = mapper.requestToOrder(orderRequest, drink, new Date());
        processRecipeInventories(drink, orderRequest.quantity());
        orderRepository.save(order);
        statisticService.createStat(order);
    }

    private void processRecipeInventories(Drink drink, int quantity) {
        List<RecipeInventory> recipeInventories = recipeInventoryService.findAllByRecipeId(drink);
        if (recipeInventories.isEmpty()) {
            throw new ResourceNotFoundException("Recipe and inventory for drink: " + drink.getDrinkName() + " not exists");
        }

        for (RecipeInventory recipeInventory : recipeInventories) {
            int numToReduce = recipeInventory.getNumOfIngredient() * quantity;
            inventoryService.reduceInventory(recipeInventory.getInventory().getInventoryName(), numToReduce);
        }
    }

    private List<OrderResponse> checkOnEmpty(List<Order> orders, String ex) {
        if (!orders.isEmpty()) {
            return orders.stream().map(mapper::orderToResponse).toList();
        } else {
            throw new ResourceNotFoundException(ex);
        }
    }
}
