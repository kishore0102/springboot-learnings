package com.fresco.beverage.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fresco.beverage.models.Batch;
import com.fresco.beverage.models.BatchRepo;
import com.fresco.beverage.models.Beverage;
import com.fresco.beverage.models.BeverageRepo;
import com.fresco.beverage.models.Order;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @Autowired
    BeverageRepo beverageRepo;

    @Autowired
    BatchRepo batchRepo;

    @PostMapping("/load-prices")
    @ResponseStatus(value = HttpStatus.OK)
    public void loadPrices(@RequestBody String req) {

        JSONArray beverages = new JSONArray(req);
        for(Object beverage: beverages) {
            JSONObject beverageObject = (JSONObject) beverage;
            String name = beverageObject.getString("beverage");
            Double price = beverageObject.getDouble("price");
            Double estimatedTime = beverageObject.getDouble("timeToMake");
            beverageRepo.save(new Beverage(name, price, estimatedTime));
        }
    }

    @PostMapping("make-order")
    public ResponseEntity<?> makeOrder(@RequestBody String req) {
        JSONArray batch = new JSONArray(req);
        System.out.println(batch);
        List<Order> orders = new ArrayList<>();
        
        Double price = 0.0;
        Double time = 0.0;

        for(Object order: batch){
            JSONObject orderjson = (JSONObject) order;
            Integer beverageId = orderjson.getInt("beverage");
            String orderFor = orderjson.getString("orderFor");
            Beverage beverage = beverageRepo.findById(beverageId + 1).get();
            price = price + beverage.getPrice();
            time = beverage.getEstimatedTime() > time ? beverage.getEstimatedTime() : time;
            Order newOrder = new Order(orderFor, beverage);
            orders.add(newOrder);
        }

        Map<String, Double> response = new HashMap<>();
        response.put("totalPrice", price);
        response.put("estimatedTime", time);
        System.out.println(response.toString());

        batchRepo.save(new Batch(orders));
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-orders")
    public ResponseEntity<?> getOrders() {
        List<Batch> batches = batchRepo.findAll();

        List<List<Map<String, Object>>> totalResponse = new ArrayList<>();
    
        for(Batch batch: batches) {
            List<Map<String, Object>> response = new ArrayList<>();
            for(Order order: batch.getOrders()) {
                String orderFor = order.getOrderFor();
                String beverage = order.getBeverage().getName();
                Map<String, Object> resData = new HashMap<>();
                resData.put("orderFor", orderFor);
                resData.put("beverage", beverage);
                response.add(resData);
            }
            totalResponse.add(response);
        }
        System.out.println(totalResponse);
        return ResponseEntity.ok(totalResponse);
    }

    @GetMapping("/clear-order/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void clearOrder(@PathVariable Integer id) {
        batchRepo.deleteById(id);
    }

}
