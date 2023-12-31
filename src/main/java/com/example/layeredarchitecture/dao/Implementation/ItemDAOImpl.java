package com.example.layeredarchitecture.dao.Implementation;

import com.example.layeredarchitecture.dao.CustomInterfaces.ItemDAO;
import com.example.layeredarchitecture.dao.SqlUtil;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException {

       return SqlUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getUnitPrice());

    }
    @Override
    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {

        return  SqlUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());

    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {

        ResultSet resultSet=SqlUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return resultSet.next();
    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {

        return SqlUtil.execute("DELETE FROM Item WHERE code=?",code);

    }
    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst= SqlUtil.execute("SELECT * FROM Item");

       ArrayList<ItemDTO> allItem = new ArrayList<>();

        while (rst.next()) {
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand"));
            allItem.add(itemDTO);
        }
        return allItem;
    }
    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {

        ResultSet rst =SqlUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public ItemDTO search(String code) throws SQLException, ClassNotFoundException {

        ResultSet rst =SqlUtil.execute("SELECT * FROM Item WHERE code=?",code);
        rst.next();
        return new ItemDTO(code + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }
}
