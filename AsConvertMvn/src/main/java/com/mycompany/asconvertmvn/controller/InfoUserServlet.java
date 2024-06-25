/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.asconvertmvn.controller;

import com.mycompany.asconvertmvn.dal.AccountDAO;
import com.mycompany.asconvertmvn.dal.CustomerDAO;
import com.mycompany.asconvertmvn.model.Account;
import com.mycompany.asconvertmvn.model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author tranm
 */
public class InfoUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InfoUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InfoUserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userRaw = request.getParameter("user");
        if (userRaw != null) {
            int user = Integer.parseInt(userRaw);
            Customer c = CustomerDAO.INSTANCE.getCustomerById(user);
            List<Account> list = AccountDAO.INSTANCE.getAllAccount();
            if (c != null) {
                request.setAttribute("userInfo", c);
                request.setAttribute("listAccount", list);

            } else {
                request.setAttribute("mess", "Thông tin của bạn đã bị xóa khỏi hệ thông");
            }
            request.getRequestDispatcher("view/userInfo.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        Account a = new Account();
        a.setUserName(user);
        a.setPassword(password);
        Customer c = new Customer();
        c.setCustomerId(id);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setGender(gender);
        c.setAddress(address);
        c.setEmail(email);
        c.setPhone(phone);
        boolean check = CustomerDAO.INSTANCE.updateCustomerInfo(c);
        if (check) {
            HttpSession session = request.getSession();
            session.setAttribute("message_update", "Thay đổi thông tin thành công");
            response.sendRedirect("home");
        } else {
            request.setAttribute("message_update", "Không thay đổi thành công thông tin");
            request.getRequestDispatcher("view/userInfo.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
