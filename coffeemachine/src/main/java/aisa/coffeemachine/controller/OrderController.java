package aisa.coffeemachine.controller;

import aisa.coffeemachine.dto.order.OrderRequest;
import aisa.coffeemachine.dto.order.OrderResponse;
import aisa.coffeemachine.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/coffeemachine/order")
@RequiredArgsConstructor
@Tag(name = "Order controller", description = "Controller for manage order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping()
    @Operation(
            summary = "Get all orders",
            description = "That's method use when you wanna get all orders"
    )
    public List<OrderResponse> getAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/byName")
    @Operation(
            summary = "Get orders by drink name",
            description = "That's method use when you wanna get all order by drinks name"
    )
    public List<OrderResponse> getAllByName(
            @RequestParam("name") @Parameter(description = "Drink name") String name) {
        return orderService.getOrderByDrink(name);
    }

    @GetMapping("/byDate")
    @Operation(
            summary = "Get orders by order creating date",
            description = "That's method use when you wanna get all order by creating date"
    )
    public List<OrderResponse> getAllByDate(
            @RequestParam("date") @Parameter(description = "Date when order was bought") String date) throws ParseException {
        return orderService.getAllOrdersByDate(date);
    }

    @PostMapping("create")
    @Operation(
            summary = "Create order",
            description = "That's method use when you wanna create new order"
    )
    public void create(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }
}
