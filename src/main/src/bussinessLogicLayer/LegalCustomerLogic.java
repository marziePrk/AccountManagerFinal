package bussinessLogicLayer;

import dataAccessLayer.CRUD.LegalCustomerCRUD;
import dataAccessLayer.LegalCustomer;
import exception.DuplicateCodeException;
import exception.EmptyFieldException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dotin school 6 on 8/8/2016.
 */
public class LegalCustomerLogic extends CustomerLogic {

    public static LegalCustomer createLegalCustomer(long id, String companyName, String registerDate, String economicId) throws SQLException {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setId(id);
        legalCustomer.setCompanyName(companyName);
        legalCustomer.setRegisterDate(registerDate);
        legalCustomer.setEconomicId(economicId);
        LegalCustomerCRUD.create(legalCustomer);

        return legalCustomer;
    }

    public static ArrayList<LegalCustomer> retrieveLegalCustomer(String companyName, String registerDate, String economicId) throws SQLException {
        ArrayList<LegalCustomer> legalCustomers = LegalCustomerCRUD.retrieveLegalCustomer(companyName, registerDate, economicId);
        return legalCustomers;
    }


    public static ArrayList<LegalCustomer> retrieveLegalCustomerByCustomerNumber(String customerNumber) throws SQLException {
        ArrayList<LegalCustomer> legalCustomers = LegalCustomerCRUD.retrieveLegalCustomerByCustomerNumber(customerNumber);
        return legalCustomers;
    }

    public static void deleteLegalCustomer(long id) throws SQLException {
        LegalCustomerCRUD.deleteLegalCustomer(id);
    }

    public static void updateLegalCustomer(long id, String companyName, String registerDate, String economicId) throws SQLException {
        LegalCustomerCRUD.updateLegalCustomer(id, companyName, registerDate, economicId);
    }

    public static boolean checkValidation(String customerNumber, String registerDate, String economicId) throws EmptyFieldException, DuplicateCodeException, SQLException {
        if (customerNumber.equals("")){
            throw new EmptyFieldException("لطفا فیلد نام شرکت را وارد کنید. ");
        }
        if (registerDate.equals("")){
            throw new EmptyFieldException("لطفا فیلد تاریخ ثبت را وارد کنید. ");
        }
        if (economicId.equals("")){
            throw new EmptyFieldException("لطفا فیلد کد اقتصادی را وارد کنید.");
        }
        if (LegalCustomerCRUD.retrieveLegalCustomer("","",economicId).size()>0){
            throw new DuplicateCodeException("کد اقتصادی وارد شده تکراری است.");
        }
        return true;
    }


    public static boolean checkValidation(long id ,String customerNumber, String registerDate, String economicId) throws EmptyFieldException, DuplicateCodeException, SQLException {
        if (customerNumber.equals("")) {
            throw new EmptyFieldException("لطفا فیلد نام شرکت را وارد کنید. ");
        }
        if (registerDate.equals("")) {
            throw new EmptyFieldException("لطفا فیلد تاریخ ثبت را وارد کنید. ");
        }
        if (economicId.equals("")) {
            throw new EmptyFieldException("لطفا فیلد کد اقتصادی را وارد کنید.");
        }

        List<LegalCustomer> legalCustomers = LegalCustomerCRUD.retrieveLegalCustomer("", "", economicId);
        int LegalCustomersSize = legalCustomers.size();

        if (LegalCustomersSize > 0) {
            LegalCustomer legalCustomer = legalCustomers.get(0);
            if (LegalCustomersSize > 1) {
                throw new DuplicateCodeException("کد اقتصادی وارد شده تکراری است.");
            }
            else if (legalCustomer.getId()!= id) {
                throw new DuplicateCodeException("کد اقتصادی وارد شده تکراری است.");
            }
        }
        return true;
    }

    /*public static void deleteEconomicId(long id) {
        LegalCustomerCRUD.deleteEconomicId(id);
    }*/
}
