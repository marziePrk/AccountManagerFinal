package presentationLayer;

import bussinessLogicLayer.LegalCustomerLogic;
import dataAccessLayer.LegalCustomer;
import util.OutputGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import static util.OutputGenerator.generateLegalCustomerUpdatePage;

/**
 * Created by Dotin school 6 on 8/13/2016.
 */
public class UpdateLegalCustomerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        String customerNumber = request.getParameter("customerNumber");
        String outHTML = "";


        try {
            ArrayList<LegalCustomer> legalCustomers = LegalCustomerLogic.retrieveLegalCustomerByCustomerNumber(customerNumber);
            LegalCustomer legalCustomer = legalCustomers.get(0);
            outHTML = generateLegalCustomerUpdatePage(legalCustomer);
        } catch (SQLException e) {
            outHTML = OutputGenerator.generateExceptionPage(e.getMessage() + "خطا در پایگاه داده...");
        }
        response.setContentType("text/html ;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(outHTML);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doGet(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
