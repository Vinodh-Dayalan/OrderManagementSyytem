package com.orderitem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderitem.entity.OrderItemEntity;
import com.orderitem.exception.CustomException;
import com.orderitem.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;

	public OrderItemEntity getOrderById(Long id) throws CustomException {
		Optional<OrderItemEntity> order = orderItemRepository.findById(id);

		if (order.isPresent()) {
			return order.get();
		} else {
			throw new CustomException("124");
		}
	}

	public List<OrderItemEntity> getAllOrders() {
		List<OrderItemEntity> orderList = orderItemRepository.findAll();

		if (orderList.size() > 0) {
			return orderList;
		} else {
			return new ArrayList<OrderItemEntity>();
		}
	}

	public void deleteOrderById(Long id) throws CustomException {
		Optional<OrderItemEntity> orderItems = orderItemRepository.findById(id);

		if (orderItems.isPresent()) {
			orderItemRepository.deleteById(id);
		} else {
			throw new CustomException("124");
		}
	}

	public OrderItemEntity createOrUpdateOrder(OrderItemEntity orderDet) {
		Optional<OrderItemEntity> order = orderItemRepository.findById(orderDet.getId());

		if (order.isPresent()) {
			OrderItemEntity newEntity = order.get();
			newEntity.setProductCode(orderDet.getProductCode());
			newEntity.setProductName(orderDet.getProductName());
			newEntity.setQuantity(orderDet.getQuantity());

			newEntity = orderItemRepository.save(newEntity);

			return newEntity;
		} else {
			orderDet = orderItemRepository.save(orderDet);

			return orderDet;
		}
	}

}
