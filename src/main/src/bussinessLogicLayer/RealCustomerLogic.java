package bussinessLogicLayer;

import dataAccessLayer.CRUD.RealCustomerCRUD;
import dataAccessLayer.RealCustomer;
import exception.DuplicateCodeException;
import exception.EmptyFieldException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dotin school 6 on 8/8/2016.
 */
public class RealCustomerLogic extends CustomerLogic {
    public static RealCustomer create(long id, String firstName, String lastName, String fatherName, String birthDate, String nationalCode) throws SQLException {
        RealCustomer realCustomer = new RealCustomer();
        realCustomer.setId(id);
        realCustomer.setFirstName(firstName);
        realCustomer.setLastName(lastName);
        realCustomer.setFatherName(fatherName);
        realCustomer.setBirthDate(birthDate);
        realCustomer.setNationalCode(nationalCode);
        RealCustomerCRUD.create(realCustomer);

        return realCustomer;
    }

    public static ArrayList<RealCustomer> retrieveRealCustomer(String firstName, String lastName, String fatherName, String birthDate, String nationalCode) throws SQLException {
        ArrayList<RealCustomer> realCustomers = RealCustomerCRUD.retrieveRealCustomer(firstName, lastName, fatherName, birthDate, nationalCode);
        return realCustomers;
    }


    public static ArrayList<RealCustomer> retrieveRealCustomerByCustomerNumber(String customerNumber) throws SQLException {
        ArrayList<RealCustomer> realCustomers = RealCustomerCRUD.retrieveRealCustomerByCustomerNumber(customerNumber);
        return realCustomers;
    }

    public static void deleteRealCustomer(long id) throws SQLException {
        RealCustomerCRUD.deleteRealCustomer(id);
    }

    public static void updateRealCustomer(long id, String firstName, String lastName, String fatherName, String birthDate, String nationalCode) throws SQLException {
        RealCustomerCRUD.updateRealCustomer(id, firstName, lastName, fatherName, birthDate, nationalCode);
    }

    public static boolean checkValidation(String firstName, String lastName, String fatherName, String birthDate, String nationalId) throws EmptyFieldException, SQLException, DuplicateCodeException {
        if (firstName.equals("")) {
            throw new EmptyFieldException("لطفا فیلد نام  را وارد کنید. ");
        }
        if (lastName.equals("")) {
            throw new EmptyFieldException("لطفا فیلد نام خانوادگی  را وارد کنید. ");
        }
        if (fatherName.equals("")) {
            throw new EmptyFieldException("لطفا فیلد نام پدر  را وارد کنید.");
        }
        if (fatherName.equals("")) {
            throw new EmptyFieldException("لطفا فیلد نام پدر  را وارد کنید.");
        }
        if (birthDate.equals("")) {
            throw new EmptyFieldException("لطفا فیلد تاریخ تولد   را وارد کنید.");
        }
        if (nationalId.equals("")) {
            throw new EmptyFieldException("لطفا فیلد شماره ملی   را وارد کنید.");
        }
        if (RealCustomerCRUD.retrieveRealCustomer("", "", "", "", nationalId).size() > 0) {
            throw new DuplicateCodeException("کد ملی وارد شده تکراری است.");
        }
        return true;
    }

    public static boolean checkValidation(Long id, String firstName, String lastName, String fatherName, String birthDate, String nationalId) throws EmptyFieldException, SQLException, DuplicateCodeException {
        if (firstName.equals("")) {
            throw new EmptyFieldException("لطفا فیلد نام  را وارد کنید. ");
        }
        if (lastName.equals("")) {
            throw new EmptyFieldException("لطفا فیلد نام خانوادگی  را وارد کنید. ");
        }
        if (fatherName.equals("")) {
            throw new EmptyFieldException("لطفا فیلد نام پدر  را وارد کنید.");
        }
        if (fatherName.equals("")) {
            throw new EmptyFieldException("لطفا فیلد نام پدر  را وارد کنید.");
        }
        if (birthDate.equals("")) {
            throw new EmptyFieldException("لطفا فیلد تاریخ تولد   را وارد کنید.");
        }
        if (nationalId.equals("")) {
            throw new EmptyFieldException("لطفا فیلد شماره ملی   را وارد کنید.");
        }

        ArrayList<RealCustomer> realCustomers = RealCustomerCRUD.retrieveRealCustomer("", "", "", "", nationalId);
        int realCustomersSize = realCustomers.size();

        if (realCustomersSize > 0) {
            RealCustomer realCustomer = realCustomers.get(0);
            if (realCustomersSize > 1) {
                throw new DuplicateCodeException("کد ملی وارد شده تکراری است.");
            } else if (realCustomer.getId() != id) {
                throw new DuplicateCodeException("کد ملی وارد شده تکراری است.");
            }
        }
        return true;
    }
}
