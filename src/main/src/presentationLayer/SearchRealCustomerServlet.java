package presentationLayer;

import bussinessLogicLayer.RealCustomerLogic;
import dataAccessLayer.RealCustomer;
import util.OutputGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dotin school 6 on 8/6/2016.
 */
public class SearchRealCustomerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        String firstName = request.getParameter("firstName");
        System.out.println(firstName);
        String lastName = request.getParameter("lastName");
        System.out.println(lastName);
        String fatherName = request.getParameter("fatherName");
        System.out.println(fatherName);
        String birthDate = request.getParameter("birthDate");
        System.out.println(birthDate);
        String nationalCode = request.getParameter("nationalCode");
        System.out.println(nationalCode);
        String customerNumber = request.getParameter("customerNumber");
        System.out.println(customerNumber);
        String outputHTML = "";
        List<RealCustomer> realCustomers;

        try {
            if (customerNumber == null) {
                realCustomers = RealCustomerLogic.retrieveRealCustomer(firstName, lastName, fatherName, birthDate, nationalCode);
            } else {
                realCustomers = RealCustomerLogic.retrieveRealCustomerByCustomerNumber(customerNumber);
            }
            outputHTML = OutputGenerator.generateRealCustomerSearchResult(realCustomers);
        } catch (SQLException e) {
            outputHTML = OutputGenerator.generateExceptionPage(e.getMessage() + "خطا در پایگاه داده...");
        }
        response.setContentType("text/html ;charset=UTF-8");
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
