package presentationLayer;

import bussinessLogicLayer.CustomerLogic;
import bussinessLogicLayer.LegalCustomerLogic;
import dataAccessLayer.Customer;
import dataAccessLayer.LegalCustomer;
import exception.DuplicateCodeException;
import exception.EmptyFieldException;
import util.OutputGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Dotin school 6 on 8/6/2016.
 */
public class CreateLegalCustomerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        String companyName = request.getParameter("companyName");
        String registerDate = request.getParameter("registerDate");
        String economicId = request.getParameter("economicId");
        String outputHTML = "";
        LegalCustomer legalCustomer = null;
        Customer customer = null;

        try {
            LegalCustomerLogic.checkValidation(companyName.trim(), registerDate.trim(), economicId.trim());
            customer = CustomerLogic.createCustomer();
            legalCustomer = LegalCustomerLogic.createLegalCustomer(customer.getId(), companyName, registerDate, economicId);
            outputHTML = OutputGenerator.generateSuccessfulInsert(legalCustomer, customer);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmptyFieldException e) {
            outputHTML = OutputGenerator.generateExceptionPage(e.getMessage());
        } catch (DuplicateCodeException e) {
            outputHTML = OutputGenerator.generateExceptionPage(e.getMessage());
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(outputHTML);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            doGet(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
