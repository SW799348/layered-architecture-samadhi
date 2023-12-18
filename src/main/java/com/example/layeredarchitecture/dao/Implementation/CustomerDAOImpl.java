package com.example.layeredarchitecture.dao.Implementation;
import com.example.layeredarchitecture.dao.CustomInterfaces.CustomerDAO;
import com.example.layeredarchitecture.dao.SqlUtil;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst= SqlUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();

        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"));
            allCustomer.add(customerDTO);
        }
        return allCustomer;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {

       return SqlUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());

    }

    @Override
    public boolean update(CustomerDTO                                                                                  dto) throws SQLException, ClassNotFoundException {

       return SqlUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getId());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst= SqlUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

       return SqlUtil.execute("DELETE FROM Customer WHERE id=?",id);

    }

    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {

        ResultSet rst=SqlUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst=SqlUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        rst.next();
        return new CustomerDTO(id + "", rst.getString("name"), rst.getString("address"));
    }
}
