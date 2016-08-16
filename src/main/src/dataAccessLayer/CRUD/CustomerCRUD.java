package dataAccessLayer.CRUD;

import dataAccessLayer.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static util.DBConnection.getDBConnection;

/**
 * Created by Dotin school 6 on 8/7/2016.
 */
public class CustomerCRUD {
    static PreparedStatement preparedStatement = null;

    public static Customer createCustomer() throws SQLException {
        long id = 01;
        Customer customer = new Customer();
        String customerNumber = generateCustomerNumber();
        customer.setCustomerNumber(customerNumber);
        //TODO INSERT INTO CUSTOMER TABLE
        preparedStatement = getDBConnection().prepareStatement("INSERT INTO customer(customer_number) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customerNumber);
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            id = (long) resultSet.getInt(1);
        }
        customer.setId(id);
        resultSet.close();

        return customer;
    }

    public static String generateCustomerNumber() throws SQLException {
        String customerNumber = "";
        preparedStatement = getDBConnection().prepareStatement("SELECT MAX(customer_number) FROM customer");

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        if (resultSet.getInt(1) != 0) {
            customerNumber = String.valueOf(resultSet.getInt(1) + 1);
        } else {
            customerNumber = "100";
            //legalCustomer.setCustomerNumber(1000);
        }

        return customerNumber;
    }

    public static void deleteCustomer(long id) throws SQLException {
        preparedStatement = getDBConnection().prepareStatement("DELETE FROM customer WHERE id=?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    public static void deleteCustomerByCustomerNumber(String customerNumber)  {
        try {
            preparedStatement = getDBConnection().prepareStatement("DELETE FROM customer WHERE customer_number =?");
            preparedStatement.setString(1, customerNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
