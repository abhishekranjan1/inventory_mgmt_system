package com.example.inventory_mgmt_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class AssetManagerException extends RuntimeException {
	
	/**
	 * 
	 */

	public AssetManagerException(String message) {
		super(message);
	}

}
