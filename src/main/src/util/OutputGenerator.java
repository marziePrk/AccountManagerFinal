package util;

import dataAccessLayer.Customer;
import dataAccessLayer.LegalCustomer;
import dataAccessLayer.RealCustomer;

import java.util.List;

/**
 * Created by Dotin school 6 on 8/10/2016.
 */
public class OutputGenerator {
    //TODO change method name
    public static String generateSearchPage(LegalCustomer legalCustomer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n");
        stringBuilder.append("<html lang=\"fa\">\n");
        stringBuilder.append("<head>\n");
        stringBuilder.append("    <meta charset=\"UTF-8\">\n");
        stringBuilder.append("    <link href=\"style/Style.css\" rel=\"stylesheet\">\n");
        stringBuilder.append("    <title>جستجوی مشتری حقوقی</title>\n");
        stringBuilder.append("</head>\n");
        stringBuilder.append("<body>\n");
        stringBuilder.append("<div class=\"pageHeader\">\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("<div><h6>نسخه آزمایشی</h6></div>\n");
        stringBuilder.append("<div class=\"content\">\n");
        stringBuilder.append("    <h1 style=\"color: saddlebrown\">لطفااطلاعات مشتری مورد نظر خود را در یکی از بخش های زیر وارد نمایید.</h1>\n");
        stringBuilder.append("    <br>\n");
        stringBuilder.append("    <div class=\"searchBox\">\n");
        stringBuilder.append("        <div class=\"searchBoxChild\">\n");
        stringBuilder.append("            <form  action=\"/SearchLegalCustomerServlet\" method=\"post\">\n");
        stringBuilder.append("                <br>\n");
        stringBuilder.append("                <table  >\n");
        stringBuilder.append("                    <tr>\n");
        stringBuilder.append("                        <td> نام شرکت : </td>\n");
        stringBuilder.append("                        <td><input name=\"companyName\" type=\"text\"></td>\n");
        stringBuilder.append("                    </tr>\n");
        stringBuilder.append("                    <tr>\n");
        stringBuilder.append("                        <td>کد اقتصادی :</td>\n");
        stringBuilder.append("                        <td><input name=\"economicId\" type=\"text\"placeholder=\"Search..\"></td>\n");
        stringBuilder.append("                    </tr>\n");
        stringBuilder.append("                    <tr>\n");
        stringBuilder.append("                        <td>تاریخ ثبت :</td>\n");
        stringBuilder.append("                        <td><input name=\"customerNumber\" type=\"text\"></td>\n");
        stringBuilder.append("                    </tr>\n");
        stringBuilder.append("                </table>\n");
        stringBuilder.append("                <input type=\"submit\" class=\"button\" value=\"جستجو \">\n");
        stringBuilder.append("            </form>\n");
        stringBuilder.append("        </div>\n");
        stringBuilder.append("        <div class=\"searchBoxChild\">\n");
        stringBuilder.append("            <form  action=\"/SearchLegalCustomerServlet\" method=\"post\">\n");
        stringBuilder.append("                <br>\n");
        stringBuilder.append("                <table >\n");
        stringBuilder.append("                    <tr>\n");
        stringBuilder.append("                        <td>شماره مشتری:</td>\n");
        stringBuilder.append("                        <td><input name=\"companyName\" type=\"text\"></td>\n");
        stringBuilder.append("                    </tr>\n");
        stringBuilder.append("                </table>\n");
        stringBuilder.append("                <br>\n");
        stringBuilder.append("                <br>\n");
        stringBuilder.append("                <input type=\"submit\" class=\"button\" value=\"جستجو \">\n");
        stringBuilder.append("            </form>\n");
        stringBuilder.append("        </div>\n");
        stringBuilder.append("    </div>\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("<body/>");
        return stringBuilder.toString();

    }


    public static String generateLegalCustomerSearchResult(List<LegalCustomer> legalCustomers) {
        String deletePath = "/DeleteLegalCustomerServlet?id=";
        String updatePath = "/UpdateLegalCustomerServlet?customerNumber=";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n");
        stringBuilder.append("<html>\n");
        stringBuilder.append("<head lang=\"fa\">\n");
        stringBuilder.append("    <meta charset=\"UTF-8\">\n");
        stringBuilder.append("    <link href=\"style/Style.css\" rel=\"stylesheet\">\n");
        stringBuilder.append("    <title>نتایج جستجو</title>\n");
        stringBuilder.append("</head>\n");
        stringBuilder.append("<body>\n");
        stringBuilder.append("<div class=\"pageHeader\">\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("<div><h6>نسخه آزمایشی</h6></div>\n");
        stringBuilder.append("<h3>نتایج جستجو:</h3>\n");
        stringBuilder.append("<div class=\"searchTable\">\n");
        stringBuilder.append("    <table>\n");
        stringBuilder.append("        <tr>\n");
        stringBuilder.append("            <th>نام شرکت</th>\n");
        stringBuilder.append("            <th>تاریخ ثبت</th>\n");
        stringBuilder.append("            <th>کد اقتصادی</th>\n");
        stringBuilder.append("            <th>شماره مشتری</th>\n");
        stringBuilder.append("            <th>انجام عملیات</th>\n");
        stringBuilder.append("        </tr>\n");

        if (legalCustomers.size() == 0) {
            stringBuilder.append("<h2>اطلاعات شما موجود نمی باشد.<br>لطفا در وارد کردن اطلاعات دقت فرمایید.</h2>");
            stringBuilder.append("        <tr>");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
           // stringBuilder.append("            </td>\n");
            stringBuilder.append("        </tr>\n");

        } else {
            for (LegalCustomer legalCustomer : legalCustomers) {
                stringBuilder.append("        <tr>");
                stringBuilder.append("            <td>" + legalCustomer.getCompanyName() + "</td>\n");
                stringBuilder.append("            <td>" + legalCustomer.getRegisterDate() + "</td>\n");
                stringBuilder.append("            <td>" + legalCustomer.getEconomicId() + "</td>\n");
                stringBuilder.append("            <td>" + legalCustomer.getCustomerNumber() + "</td>\n");
                stringBuilder.append("            <td>\n");
                stringBuilder.append("                <form style=\"float: right\" action=" + deletePath + legalCustomer.getId() + " method=\"post\">");
                stringBuilder.append("                    <input type=\"submit\" class=\"button button1\" value=\"حذف\">\n");
                stringBuilder.append("                </form>\n");
                stringBuilder.append("                <form style=\"float: left\" action=" + updatePath + legalCustomer.getCustomerNumber()+ " method=\"post\">");
                stringBuilder.append("                    <input type=\"submit\" class=\"button button1\" value=\"اصلاح\">\n");
                stringBuilder.append("                </form>\n");
                stringBuilder.append("            </td>\n");
                stringBuilder.append("        </tr>\n");
            }
        }
        stringBuilder.append("    </table>\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("</body>\n");
        stringBuilder.append("</html>\n");

        return stringBuilder.toString();
    }
    public static String generateRealCustomerSearchResult(List<RealCustomer> realCustomers) {
        String deletePath = "/DeleteRealCustomerServlet?id=";
        String updatePath = "/UpdateRealCustomerServlet?customerNumber=";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n");
        stringBuilder.append("<html>\n");
        stringBuilder.append("<head lang=\"fa\">\n");
        stringBuilder.append("    <meta charset=\"UTF-8\">\n");
        stringBuilder.append("    <link href=\"style/Style.css\" rel=\"stylesheet\">\n");
        stringBuilder.append("    <title>نتایج جستجو</title>\n");
        stringBuilder.append("</head>\n");
        stringBuilder.append("<body>\n");
        stringBuilder.append("<div class=\"pageHeader\">\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("<div><h6>نسخه آزمایشی</h6></div>\n");
        stringBuilder.append("<h3>نتایج جستجو:</h3>\n");
        stringBuilder.append("<div class=\"searchTable\">\n");
        stringBuilder.append("    <table>\n");
        stringBuilder.append("        <tr>\n");
        stringBuilder.append("            <th> نام</th>\n");
        stringBuilder.append("            <th>نام خانوادگی</th>\n");
        stringBuilder.append("            <th>نام پدر</th>\n");
        stringBuilder.append("            <th>تاریخ تولد</th>\n");
        stringBuilder.append("            <th>شماره ملی</th>\n");
        stringBuilder.append("            <th>شماره مشتری</th>\n");
        stringBuilder.append("            <th>انجام عملیات</th>\n");
        stringBuilder.append("        </tr>\n");

        if (realCustomers.size() == 0) {
            stringBuilder.append("<h2>اطلاعات شما موجود نمی باشد.<br>لطفا در وارد کردن اطلاعات دقت فرمایید.</h2>");
            stringBuilder.append("        <tr>");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            <td> ----- </td>\n");
            stringBuilder.append("            </td>\n");
            stringBuilder.append("        </tr>\n");

        } else {
            for (RealCustomer realCustomer : realCustomers) {
                stringBuilder.append("        <tr>");
                stringBuilder.append("            <td>" + realCustomer.getFirstName() + "</td>\n");
                stringBuilder.append("            <td>" + realCustomer.getLastName() + "</td>\n");
                stringBuilder.append("            <td>" + realCustomer.getFatherName() + "</td>\n");
                stringBuilder.append("            <td>" + realCustomer.getBirthDate() + "</td>\n");
                stringBuilder.append("            <td>" + realCustomer.getNationalCode() + "</td>\n");
                stringBuilder.append("            <td>" + realCustomer.getCustomerNumber() + "</td>\n");
                stringBuilder.append("            <td>\n");
                stringBuilder.append("                <form style=\"float: right\" action=" + deletePath + realCustomer.getId() + " method=\"post\">");
                stringBuilder.append("                    <input type=\"submit\" class=\"button button1\" value=\"حذف\">\n");
                stringBuilder.append("                </form>\n");
                stringBuilder.append("                <form style=\"float: left\" action=" + updatePath + realCustomer.getCustomerNumber()+ " method=\"post\">");
                stringBuilder.append("                    <input type=\"submit\" class=\"button button1\" value=\"اصلاح\">\n");
                stringBuilder.append("                </form>\n");
                stringBuilder.append("            </td>\n");
                stringBuilder.append("        </tr>\n");
            }
        }
        stringBuilder.append("    </table>\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("</body>\n");
        stringBuilder.append("</html>\n");

        return stringBuilder.toString();
    }

    public static String generateSuccessfulPage(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n");
        stringBuilder.append("<html>\n");
        stringBuilder.append("<head lang=\"fa\">\n");
        stringBuilder.append("    <meta charset=\"UTF-8\">\n");
        stringBuilder.append("    <link href=\"style/Style.css\" rel=\"stylesheet\">\n");
        stringBuilder.append("    <title>تایید عملیات</title>\n");
        stringBuilder.append("<body>\n");
        stringBuilder.append("<div class=\"pageHeader\">\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("<div><h6>نسخه آزمایشی</h6></div>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<h2 color=\"saddlebrown\">" + message + "</h2>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<div class=\"tableBox\">\n");
        stringBuilder.append("    <form>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("        <a href=\"index.html\" class=\"button\">بازگشت به صفحه اول</a>\n");
        stringBuilder.append("    </form>\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("</body>\n");
        stringBuilder.append("</html>");

        return stringBuilder.toString();
    }

    public static String generateSuccessfulInsert(LegalCustomer legalCustomer, Customer customer) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html>");
        stringBuilder.append("<head lang=\"fa\">");
        stringBuilder.append("    <meta charset=\"UTF-8\">");
        stringBuilder.append("    <link href=\"style/Style.css\" rel=\"stylesheet\">");
        stringBuilder.append("    <title>ثبت مشتری حقوقی</title>");
        stringBuilder.append("<body>");
        stringBuilder.append("<div class=\"pageHeader\">");
        stringBuilder.append("</div>");
        stringBuilder.append("<div><h6>نسخه آزمایشی</h6></div>");
        stringBuilder.append("<h2 color = \"saddlebrown\" >اطلاعات شما با موفقیت ثبت شد.</h2>");
        stringBuilder.append("<br>");
        stringBuilder.append("<div class=\"tableBox\">");
        stringBuilder.append("    <form>");
        stringBuilder.append("        <table>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>نام شرکت :</td>");
        stringBuilder.append("                <td><h5>" + legalCustomer.getCompanyName() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>تاریخ ثبت :</td>");
        stringBuilder.append("                <td><h5>" + legalCustomer.getRegisterDate() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>کد اقتصادی :</td>");
        stringBuilder.append("                <td><h5>" + legalCustomer.getEconomicId() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>شماره مشتری :</td>");
        stringBuilder.append("                <td><h5>" + customer.getCustomerNumber() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("        </table>");
        stringBuilder.append("        <a href=\"index.html\" class=\"button\">بازگشت به صفحه اول</a>");
        stringBuilder.append("    </form>");
        stringBuilder.append("</div>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }
    public static String generateSuccessfulInsert(RealCustomer realCustomer, Customer customer) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html>");
        stringBuilder.append("<head lang=\"fa\">");
        stringBuilder.append("    <meta charset=\"UTF-8\">");
        stringBuilder.append("    <link href=\"style/Style.css\" rel=\"stylesheet\">");
        stringBuilder.append("    <title>ثبت مشتری حقیقی</title>");
        stringBuilder.append("<body>");
        stringBuilder.append("<div class=\"pageHeader\">");
        stringBuilder.append("</div>");
        stringBuilder.append("<div><h6>نسخه آزمایشی</h6></div>");
        stringBuilder.append("<h2 color = \"saddlebrown\" >اطلاعات شما با موفقیت ثبت شد.</h2>");
        stringBuilder.append("<br>");
        stringBuilder.append("<div class=\"tableBox\">");
        stringBuilder.append("    <form>");
        stringBuilder.append("        <table>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>نام :</td>");
        stringBuilder.append("                <td><h5>" + realCustomer.getFirstName() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>نام خانوادگی :</td>");
        stringBuilder.append("                <td><h5>" + realCustomer.getLastName() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>نام پدر :</td>");
        stringBuilder.append("                <td><h5>" + realCustomer.getFatherName() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>تاریخ تولد :</td>");
        stringBuilder.append("                <td><h5>" + realCustomer.getBirthDate() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>شماره ملی :</td>");
        stringBuilder.append("                <td><h5>" + realCustomer.getNationalCode() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("            <tr>");
        stringBuilder.append("                <td>شماره مشتری :</td>");
        stringBuilder.append("                <td><h5>" + customer.getCustomerNumber() + "</h5></td>");
        stringBuilder.append("            </tr>");
        stringBuilder.append("        </table>");
        stringBuilder.append("        <a href=\"index.html\" class=\"button\">بازگشت به صفحه اول</a>");
        stringBuilder.append("    </form>");
        stringBuilder.append("</div>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }
    public static String generateLegalCustomerUpdatePage(LegalCustomer legalCustomer){
        String savePath = "/SaveLegalCustomerChangedServlet?id=";
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("<!DOCTYPE html>\n");
        stringBuilder.append("<html>\n");
        stringBuilder.append("<head lang=\"fa\">\n");
        stringBuilder.append("    <meta charset=\"UTF-8\">\n");
        stringBuilder.append("    <link href=\"style/Style.css\" rel=\"stylesheet\">\n");
        stringBuilder.append("    <title>بروزرسانی مشتری</title>\n");
        stringBuilder.append("<body>\n");
        stringBuilder.append("<div class=\"pageHeader\">\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("<div><h6>نسخه آزمایشی</h6></div>\n");
        stringBuilder.append("<div class=\"content\">\n");
        stringBuilder.append("    <h1>لطفا اطلاعات خود را جهت بازنویسی وارد نمایید.</h1>\n");
        stringBuilder.append("    <br>\n");
        stringBuilder.append("    <div class=\"tableBox\">\n");
        stringBuilder.append("        <form action="+ savePath + legalCustomer.getId()  +" method=\"post\">\n");
        stringBuilder.append("            <table>\n");
        stringBuilder.append("                <tr>\n");
        stringBuilder.append("                    <td>نام شرکت :</td>\n");
        stringBuilder.append("                    <td><input name=\"companyName\" type=\"text\" placeholder="+ legalCustomer.getCompanyName()+"></td>\n");
        stringBuilder.append("                </tr>\n");
        stringBuilder.append("                <tr>\n");
        stringBuilder.append("                    <td>تاریخ ثبت :</td>\n");
        stringBuilder.append("                    <td><input name=\"registerDate\" type=\"text\" placeholder="+legalCustomer.getRegisterDate()+"></td>\n");
        stringBuilder.append("                </tr>\n");
        stringBuilder.append("                <tr>\n");
        stringBuilder.append("                    <td>کد اقتصادی :</td>\n");
        stringBuilder.append("                    <td><input name=\"economicId\" type=\"text\" placeholder="+ legalCustomer.getEconomicId()+"></td>\n");
        stringBuilder.append("                </tr>\n");
        stringBuilder.append("            </table>\n");
        stringBuilder.append("            <input type=\"submit\" class=\"button\" value=\"ثبت اطلاعات \">\n");
        stringBuilder.append("        </form>\n");
        stringBuilder.append("    </div>\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("</body>\n");
        stringBuilder.append("</html>\n");
        return stringBuilder.toString();
    }
    public static String generateRealCustomerUpdatePage(RealCustomer realCustomer){
        String savePath = "/SaveRealCustomerChangedServlet?id=";
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("<!DOCTYPE html>\n");
        stringBuilder.append("<html>\n");
        stringBuilder.append("<head lang=\"fa\">\n");
        stringBuilder.append("    <meta charset=\"UTF-8\">\n");
        stringBuilder.append("    <link href=\"style/Style.css\" rel=\"stylesheet\">\n");
        stringBuilder.append("    <title>بروزرسانی مشتری</title>\n");
        stringBuilder.append("<body>\n");
        stringBuilder.append("<div class=\"pageHeader\">\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("<div><h6>نسخه آزمایشی</h6></div>\n");
        stringBuilder.append("<div class=\"content\">\n");
        stringBuilder.append("    <h1>لطفا اطلاعات خود را جهت بازنویسی وارد نمایید.</h1>\n");
        stringBuilder.append("    <br>\n");
        stringBuilder.append("    <div class=\"tableBox\">\n");
        stringBuilder.append("        <form action="+ savePath + realCustomer.getId()  +" method=\"post\">\n");
        stringBuilder.append("            <table>\n");
        stringBuilder.append("                <tr>\n");
        stringBuilder.append("                    <td>نام:</td>\n");
        stringBuilder.append("                    <td><input name=\"firstName\" type=\"text\" placeholder="+realCustomer.getFirstName()+"></td>\n");
        stringBuilder.append("                </tr>\n");
        stringBuilder.append("                <tr>\n");
        stringBuilder.append("                    <td>نام خانوادگی:</td>\n");
        stringBuilder.append("                    <td><input name=\"lastName\" type=\"text\" placeholder="+realCustomer.getLastName()+"></td>\n");
        stringBuilder.append("                </tr>\n");
        stringBuilder.append("                <tr>\n");
        stringBuilder.append("                    <td>نام پدر:</td>\n");
        stringBuilder.append("                    <td><input name=\"fatherName\" type=\"text\" placeholder="+realCustomer.getFatherName()+"></td>\n");
        stringBuilder.append("                </tr>\n");
        stringBuilder.append("                <tr>\n");
        stringBuilder.append("                    <td>تاریخ تولد:</td>\n");
        stringBuilder.append("                    <td><input name=\"birthDate\" type=\"text\" placeholder="+realCustomer.getBirthDate()+"></td>\n");
        stringBuilder.append("                </tr>\n");
        stringBuilder.append("                <tr>\n");
        stringBuilder.append("                    <td>شماره ملی:</td>\n");
        stringBuilder.append("                    <td><input name=\"nationalCode\" type=\"text\" placeholder="+realCustomer.getNationalCode()+"></td>\n");
        stringBuilder.append("                </tr>\n");
        stringBuilder.append("            </table>\n");
        stringBuilder.append("            <input type=\"submit\" class=\"button\" value=\"ثبت اطلاعات \">\n");
        stringBuilder.append("        </form>\n");
        stringBuilder.append("    </div>\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("</body>\n");
        stringBuilder.append("</html>\n");
        return stringBuilder.toString();
    }

    public static String generateExceptionPage(String message) {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("<!DOCTYPE html>\n");
        stringBuilder.append("<html>\n");
        stringBuilder.append("<head lang=\"fa\">\n");
        stringBuilder.append("    <meta charset=\"UTF-8\">\n");
        stringBuilder.append("    <link href=\"style/Style.css\" rel=\"stylesheet\">\n");
        stringBuilder.append("    <title>خطا در عملیات</title>\n");
        stringBuilder.append("<body>\n");
        stringBuilder.append("<div class=\"pageHeader\">\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("<div><h6>نسخه آزمایشی</h6></div>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<h1>خطا ! ! !</h1>\n");
        stringBuilder.append("<h2 color=\"saddlebrown\">" + message + "</h2>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("<div class=\"tableBox\">\n");
        stringBuilder.append("    <form>\n");
        stringBuilder.append("<br>\n");
        stringBuilder.append("        <a href=\"index.html\" class=\"button\">بازگشت به صفحه اول</a>\n");
        stringBuilder.append("    </form>\n");
        stringBuilder.append("</div>\n");
        stringBuilder.append("</body>\n");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }
}