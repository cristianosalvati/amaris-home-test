package com.amaris.hometest.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDto {

	@JsonIgnore
	private String status;
	@JsonIgnore
	private String message;
}
