package dataAccessLayer.CRUD;

import dataAccessLayer.LegalCustomer;

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
public class LegalCustomerCRUD {
    static Connection connection = getDBConnection();

    public static void create(LegalCustomer legalCustomer) throws SQLException {
        String sglStatement = "INSERT INTO legal_customer (id ,economic_id ,company_name ,register_date) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sglStatement);
        preparedStatement.setLong(1, legalCustomer.getId());
        preparedStatement.setString(2, legalCustomer.getEconomicId());
        preparedStatement.setString(3, legalCustomer.getCompanyName());
        preparedStatement.setString(4, legalCustomer.getRegisterDate());
        preparedStatement.executeUpdate();


    }

    public static ArrayList<LegalCustomer> retrieveLegalCustomer(String companyName, String registerDate, String economicId) throws SQLException {
        ArrayList<LegalCustomer> legalCustomers = new ArrayList<LegalCustomer>();

        StringBuilder sqlCommand = new StringBuilder("");
        int counter = 0;
        List<String> parameters = new ArrayList<String>();
        if (!companyName.equals("")) {
            sqlCommand.append(" AND company_name=?");
            parameters.add(companyName);
        }
        if (!economicId.equals("")) {
            sqlCommand.append(" AND economic_id=?");
            parameters.add(economicId);
        }
        if (!registerDate.equals("")) {
            sqlCommand.append(" AND register_date=?");
            parameters.add(registerDate);
        }
        //sqlCommand.append(" true ");

        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM legal_customer , customer  WHERE customer.id = legal_customer.id " + sqlCommand.toString());
        for (String param : parameters) {
            preparedStatement.setString(++counter, param);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            LegalCustomer legalCustomer = new LegalCustomer();
            legalCustomer.setCompanyName(resultSet.getString("company_name"));
            legalCustomer.setRegisterDate(resultSet.getString("register_date"));
            legalCustomer.setEconomicId(resultSet.getString("economic_id"));
            legalCustomer.setCustomerNumber(resultSet.getString("customer_number"));
            legalCustomer.setId(resultSet.getLong("id"));
            legalCustomers.add(legalCustomer);

        }

        return legalCustomers;
    }

    public static ArrayList<LegalCustomer> retrieveLegalCustomerByCustomerNumber(String customerNumber) throws SQLException {
        ArrayList<LegalCustomer> legalCustomers = new ArrayList<LegalCustomer>();
        ResultSet resultSet = null;

        if (customerNumber.equals("")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM legal_customer , customer  WHERE customer.id = legal_customer.id ;");
            resultSet = preparedStatement.executeQuery();
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM legal_customer , customer  WHERE customer.id = legal_customer.id AND customer.customer_number = ?;");
            preparedStatement.setString(1, customerNumber);
            resultSet = preparedStatement.executeQuery();
        }
        while (resultSet.next()) {
            LegalCustomer legalCustomer = new LegalCustomer();
            legalCustomer.setCompanyName(resultSet.getString("company_name"));
            legalCustomer.setRegisterDate(resultSet.getString("register_date"));
            legalCustomer.setEconomicId(resultSet.getString("economic_id"));
            legalCustomer.setCustomerNumber(resultSet.getString("customer_number"));
            legalCustomer.setId(resultSet.getLong("id"));
            System.out.println(legalCustomer);
            legalCustomers.add(legalCustomer);
        }

        return legalCustomers;
    }

    public static void deleteLegalCustomer(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM legal_customer WHERE legal_customer.id = ?;");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        CustomerCRUD.deleteCustomer(id);

    }

    public static void updateLegalCustomer(long id, String companyName, String registerDate, String economicId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bank_database.legal_customer SET company_name =?,economic_id =?,register_date=? WHERE id=?");
        preparedStatement.setString(1, companyName);
        preparedStatement.setString(2, economicId);
        preparedStatement.setString(3, registerDate);
        preparedStatement.setLong(4, id);
        preparedStatement.executeUpdate();
    }

  /*  public static void deleteEconomicId(long id) {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM legal_customer WHERE legal_customer.id = ?;");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }*/
}
