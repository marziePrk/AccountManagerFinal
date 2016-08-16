package bussinessLogicLayer;

import dataAccessLayer.CRUD.CustomerCRUD;
import dataAccessLayer.Customer;

import java.sql.SQLException;

/**
 * Created by Dotin school 6 on 8/7/2016.
 */
public class CustomerLogic {
    //create customer??
    public static Customer createCustomer() throws SQLException {
        Customer customer = CustomerCRUD.createCustomer();
        return customer;
    }

    public static void deleteById(long id) throws SQLException {
        CustomerCRUD.deleteCustomer(id);
    }

    public static void deleteByCustomerNumber(String customerNumber) {
        CustomerCRUD.deleteCustomerByCustomerNumber(customerNumber);
    }
}
