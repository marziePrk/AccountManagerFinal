package dataAccessLayer.CRUD;

import dataAccessLayer.RealCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static util.DBConnection.getDBConnection;

/**
 * Created by Dotin school 6 on 8/7/2016.
 */
public class RealCustomerCRUD {
    static Connection connection = getDBConnection();

    public static void create(RealCustomer realCustomer) throws SQLException {
        String sglStatement = "INSERT INTO real_customer (id ,first_name ,last_name ,father_name , birth_date ,national_code) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sglStatement);
        preparedStatement.setLong(1, realCustomer.getId());
        preparedStatement.setString(2, realCustomer.getFirstName());
        preparedStatement.setString(3, realCustomer.getLastName());
        preparedStatement.setString(4, realCustomer.getFatherName());
        preparedStatement.setString(5, realCustomer.getBirthDate());
        preparedStatement.setString(6, realCustomer.getNationalCode());
        preparedStatement.executeUpdate();
    }


    public static ArrayList<RealCustomer> retrieveRealCustomer(String firstName, String lastName, String fatherName, String birthDate, String nationalCode) throws SQLException {
        ArrayList<RealCustomer> realCustomers = new ArrayList<RealCustomer>();

        StringBuilder sqlCommand = new StringBuilder("");
        int counter = 0;
        List<String> parameters = new ArrayList<String>();
        if (!firstName.equals("")) {
            sqlCommand.append(" AND first_name=?");
            parameters.add(firstName);
        }
        if (!lastName.equals("")) {
            sqlCommand.append(" AND last_name=?");
            parameters.add(lastName);
        }
        if (!fatherName.equals("")) {
            sqlCommand.append(" AND father_name=?");
            parameters.add(fatherName);
        }
        if (!birthDate.equals("")) {
            sqlCommand.append(" AND birth_date=?");
            parameters.add(birthDate);
        }
        if (!nationalCode.equals("")) {
            sqlCommand.append(" AND national_code=?");
            parameters.add(nationalCode);
        }
        //sqlCommand.append(" true ");

        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM real_customer , customer  WHERE customer.id = real_customer.id " + sqlCommand.toString());
        for (String param : parameters) {
            preparedStatement.setString(++counter, param);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            RealCustomer realCustomer = new RealCustomer();
            realCustomer.setFirstName(resultSet.getString("first_name"));
            realCustomer.setLastName(resultSet.getString("last_name"));
            realCustomer.setFatherName(resultSet.getString("father_name"));
            realCustomer.setBirthDate(resultSet.getString("birth_date"));
            realCustomer.setNationalCode(resultSet.getString("national_code"));
            realCustomer.setCustomerNumber(resultSet.getString("customer_number"));
            realCustomer.setId(resultSet.getLong("id"));
            realCustomers.add(realCustomer);

        }

        return realCustomers;
    }

    public static ArrayList<RealCustomer> retrieveRealCustomerByCustomerNumber(String customerNumber) throws SQLException {
        ArrayList<RealCustomer> realCustomers = new ArrayList<RealCustomer>();
        ResultSet resultSet = null;

        if (customerNumber.equals("")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM real_customer , customer  WHERE customer.id = real_customer.id ;");
            resultSet = preparedStatement.executeQuery();
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM real_customer , customer  WHERE customer.id = real_customer.id AND customer.customer_number = ?;");
            preparedStatement.setString(1, customerNumber);
            resultSet = preparedStatement.executeQuery();
        }
        while (resultSet.next()) {
            RealCustomer realCustomer = new RealCustomer();
            realCustomer.setId(resultSet.getLong("id"));
            realCustomer.setFirstName(resultSet.getString("first_name"));
            realCustomer.setLastName(resultSet.getString("last_name"));
            realCustomer.setFatherName(resultSet.getString("father_name"));
            realCustomer.setBirthDate(resultSet.getString("birth_date"));
            realCustomer.setNationalCode(resultSet.getString("national_code"));
            realCustomer.setCustomerNumber(resultSet.getString("customer_number"));
            realCustomers.add(realCustomer);
        }

        return realCustomers;
    }

    public static void deleteRealCustomer(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM real_customer WHERE real_customer.id = ?;");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        CustomerCRUD.deleteCustomer(id);

    }

    public static void updateRealCustomer(long id, String firstName, String lastName, String fatherName, String birthDate, String nationalCode) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bank_database.real_customer SET first_name =?,last_name =?,father_name=? , birth_date=? , national_code=? WHERE id=?");
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, fatherName);
        preparedStatement.setString(4, birthDate);
        preparedStatement.setString(5, nationalCode);
        preparedStatement.setLong(6, id);
        preparedStatement.executeUpdate();
    }

}
