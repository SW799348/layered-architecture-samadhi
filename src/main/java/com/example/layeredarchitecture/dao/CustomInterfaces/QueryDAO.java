package com.example.layeredarchitecture.dao.CustomInterfaces;

import com.example.layeredarchitecture.model.CustomerDTO;

public interface QueryDAO {

    public void customerOrderDetails(CustomerDTO customerDTO);
}
