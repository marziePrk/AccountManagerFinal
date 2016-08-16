package presentationLayer;

import bussinessLogicLayer.CustomerLogic;
import bussinessLogicLayer.RealCustomerLogic;
import dataAccessLayer.Customer;
import dataAccessLayer.RealCustomer;
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
public class CreateRealCustomerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String birthDate = request.getParameter("birthDate");
        String nationalId = request.getParameter("nationalCode");
        String outputHTML = "";
        RealCustomer realCustomer = null;
        Customer customer = null;

        try {
            RealCustomerLogic.checkValidation(firstName.trim(), lastName.trim(), fatherName.trim(), birthDate.trim(), nationalId.trim());
            customer = CustomerLogic.createCustomer();
            realCustomer = RealCustomerLogic.create(customer.getId(), firstName, lastName, fatherName ,birthDate , nationalId);
            outputHTML = OutputGenerator.generateSuccessfulInsert(realCustomer, customer);

        } catch (SQLException e) {
            e.printStackTrace();
            //outputHTML = OutputGenerator.generateExceptionPage(e.getMessage() + "خطا در پایگاه داده...  ");
            //CustomerLogic.deleteByCustomerNumber(customer.getCustomerNumber());
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
    public void doPost(HttpServletRequest request , HttpServletResponse response){

        try {
            doGet(request , response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
