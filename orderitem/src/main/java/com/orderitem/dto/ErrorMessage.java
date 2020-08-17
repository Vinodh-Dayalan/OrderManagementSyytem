package com.orderitem.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ErrorMessage {
	
	private Integer code;
	private List<String> messge;

}
