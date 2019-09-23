package com.example.inventory_mgmt_system.data.constants;

import lombok.Data;

@Data
public class APIResultView {

    APIResult status;

    Integer executionTime;

    Long serverTime;

    String message;

}
