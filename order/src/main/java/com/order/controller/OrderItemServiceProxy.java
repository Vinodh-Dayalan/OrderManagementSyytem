package com.order.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.entity.OrderItemEntity;

@FeignClient(name = "order-item")
public interface OrderItemServiceProxy {
	
	@GetMapping(path = "/orderitems", produces = "application/json")
	public List<OrderItemEntity> getAllOrders();
	
	@GetMapping(path = "/orderitems/{id}", produces = "application/json")
	public OrderItemEntity getOrderById(@PathVariable("id") Long id);
	
	@PostMapping(path = "/orderitems", produces = "application/json")
	public OrderItemEntity createOrUpdateOrder(@RequestBody OrderItemEntity orderDet); 
}
