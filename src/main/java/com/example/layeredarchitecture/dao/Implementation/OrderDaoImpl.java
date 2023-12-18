package com.example.layeredarchitecture.dao.Implementation;

import com.example.layeredarchitecture.dao.CustomInterfaces.OrderDAO;
import com.example.layeredarchitecture.dao.SqlUtil;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDAO {
    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SqlUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String orderId) throws SQLException, ClassNotFoundException {

        ResultSet rst = SqlUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {

       return SqlUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",dto.getOrderId(),dto.getOrderDate(),dto.getCustomerId());

    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

}
