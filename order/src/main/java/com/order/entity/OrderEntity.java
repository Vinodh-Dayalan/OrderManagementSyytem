package com.order.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ORDER")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "order_date")
	private String orderDate;

	@Column(name = "shipping_address")
	private String shippingAddress;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TBL_ORDERITEM_id")
    private OrderItemEntity orderItemEntity;
	
	@Column(name = "total")
	private String total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public OrderItemEntity getOrderItemEntity() {
		return orderItemEntity;
	}

	public void setOrderItemEntity(OrderItemEntity orderItemEntity) {
		this.orderItemEntity = orderItemEntity;
	}

}
