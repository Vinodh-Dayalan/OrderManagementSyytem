package com.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.controller.OrderItemServiceProxy;
import com.order.entity.OrderEntity;
import com.order.entity.OrderItemEntity;
import com.order.exception.CustomException;
import com.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderItemServiceProxy orderItemServiceProxy;

	public OrderEntity getOrderById(Long id) throws CustomException {
		Optional<OrderEntity> order = orderRepository.findById(id);
		
		OrderEntity orderEnt = new OrderEntity();
		if (order.isPresent()) {
			orderEnt = order.get();
			orderEnt.setOrderItemEntity(orderItemServiceProxy.getOrderById(id));
			return orderEnt;
		} else {
			throw new CustomException("123");
		}
	}

	public List<OrderEntity> getAllOrders() {
		List<OrderEntity> orderList = orderRepository.findAll();
	
		if (orderList.size() > 0) {
			return orderList;
		} else {
			return new ArrayList<OrderEntity>();
		}
	}

	public void deleteOrderById(Long id) throws CustomException {
		Optional<OrderEntity> employee = orderRepository.findById(id);

		if (employee.isPresent()) {
			orderRepository.deleteById(id);
		} else {
			throw new CustomException("123");
		}
	}

	public OrderEntity createOrUpdateOrder(OrderEntity orderDet) {
		Optional<OrderEntity> order = orderRepository.findById(orderDet.getId());

		if (order.isPresent()) {
			OrderEntity newEntity = order.get();
			newEntity.setCustomerName(orderDet.getCustomerName());
			newEntity.setOrderDate(orderDet.getOrderDate());

			OrderItemEntity orderItem = orderDet.getOrderItemEntity();
			newEntity.setOrderItemEntity(orderItem);

			newEntity.setShippingAddress(orderDet.getShippingAddress());
			newEntity.setTotal(orderDet.getTotal());

			newEntity = orderRepository.save(newEntity);

			return newEntity;
		} else {
			orderItemServiceProxy.createOrUpdateOrder(orderDet.getOrderItemEntity());
			orderDet = orderRepository.save(orderDet);

			return orderDet;
		}
	}

}
