package com.orderitem.controller;

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

import com.orderitem.entity.OrderItemEntity;
import com.orderitem.exception.CustomException;
import com.orderitem.service.OrderItemService;


@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

	@Autowired
	OrderItemService orderItemService;

	@GetMapping
	public ResponseEntity<List<OrderItemEntity>> getAllOrderItems() {
		List<OrderItemEntity> list = orderItemService.getAllOrders();

		return new ResponseEntity<List<OrderItemEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderItemEntity> getOrderItemsById(@PathVariable("id") Long id) throws CustomException {
		OrderItemEntity entity = orderItemService.getOrderById(id);

		return new ResponseEntity<OrderItemEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<OrderItemEntity> createOrUpdateOrderItems(@RequestBody OrderItemEntity orderDet) throws CustomException {
		OrderItemEntity updated = orderItemService.createOrUpdateOrder(orderDet);
		return new ResponseEntity<OrderItemEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteOrderItemsById(@PathVariable("id") Long id) throws CustomException {
		orderItemService.deleteOrderById(id);
		return HttpStatus.FORBIDDEN;
	}

}
