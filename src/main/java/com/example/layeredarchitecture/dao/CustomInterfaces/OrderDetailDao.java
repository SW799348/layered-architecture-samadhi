package com.example.layeredarchitecture.dao.CustomInterfaces;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDao extends CrudUtil<OrderDetailDTO> {
    public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
