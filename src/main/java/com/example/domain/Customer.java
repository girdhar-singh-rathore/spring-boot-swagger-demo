package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "customer details")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CustomerId.class)
public class Customer {

	@Id
	private String id;
	@Length(max = 20)
	@ApiModelProperty("maximum length of customer is 20 chars")
	private String name;

	@ApiModelProperty(notes = "type of customer")
	private String type;
}
