package com.ecom.apis.service.order;

import com.ecom.apis.exceptionHandling.exceptions.NotFoundException;

public interface OrderService {
    String addOrder(String createTime, String id) throws NotFoundException;
}
