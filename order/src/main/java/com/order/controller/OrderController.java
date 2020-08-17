package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.OrderEntity;
import com.order.exception.CustomException;
import com.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping
	public ResponseEntity<List<OrderEntity>> getAllOrders() {
		List<OrderEntity> list = orderService.getAllOrders();

		return new ResponseEntity<List<OrderEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderEntity> getOrdersById(@PathVariable("id") Long id) throws CustomException {
		OrderEntity entity = orderService.getOrderById(id);

		return new ResponseEntity<OrderEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<OrderEntity> createOrUpdateOrders(@RequestBody OrderEntity orderDet) throws CustomException {
		OrderEntity updated = orderService.createOrUpdateOrder(orderDet);
		return new ResponseEntity<OrderEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteOrderById(@PathVariable("id") Long id) throws CustomException {
		orderService.deleteOrderById(id);
		return HttpStatus.FORBIDDEN;
	}

}
