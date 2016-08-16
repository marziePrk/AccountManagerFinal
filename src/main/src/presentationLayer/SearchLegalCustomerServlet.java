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
import java.util.List;

/**
 * Created by Dotin school 6 on 8/6/2016.
 */
public class SearchLegalCustomerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        String companyName = request.getParameter("companyName");
        String economicId = request.getParameter("economicId");
        String registerDate = request.getParameter("registerDate");
        String customerNumber = request.getParameter("customerNumber");
        String outputHTML = "";
        List<LegalCustomer> legalCustomers;

        try {
            if (customerNumber == null) {
                legalCustomers = LegalCustomerLogic.retrieveLegalCustomer(companyName, registerDate, economicId);
            } else {
                legalCustomers = LegalCustomerLogic.retrieveLegalCustomerByCustomerNumber(customerNumber);
            }
            outputHTML = OutputGenerator.generateLegalCustomerSearchResult(legalCustomers);
        } catch (SQLException e) {
            outputHTML = OutputGenerator.generateExceptionPage(e.getMessage() + "خطا در پایگاه داده...");
        }
        response.setContentType("text/html ;charset=UTF-8");
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
