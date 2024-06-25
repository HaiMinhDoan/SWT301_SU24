/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.asconvertmvn.controller;

import com.mycompany.asconvertmvn.dal.AccountDAO;
import com.mycompany.asconvertmvn.dal.CustomerDAO;
import com.mycompany.asconvertmvn.dal.OrderDAO;
import com.mycompany.asconvertmvn.dal.ProductDAO;
import com.mycompany.asconvertmvn.model.Account;
import com.mycompany.asconvertmvn.model.Category;
import com.mycompany.asconvertmvn.model.Customer;
import com.mycompany.asconvertmvn.model.Order;
import com.mycompany.asconvertmvn.model.OrderDetail;
import com.mycompany.asconvertmvn.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author tranm
 */
public class AdminHomeServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminHomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminHomeServlet at " + request.getContextPath() + "</h1>");
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
        List<Product> list = ProductDAO.INSTANCE.getAllProduct();
        List<Category> listCategory = ProductDAO.INSTANCE.getAllCategory();
        List<Account> listAccount = AccountDAO.INSTANCE.getAllAccount();
        List<Customer> listCustomer = CustomerDAO.INSTANCE.getAllCustomers();
        Map<Integer, Integer> listOrderCus = AccountDAO.INSTANCE.getAllToalOrderByCus();
        List<Order> listOrder = OrderDAO.INSTANCE.getAllOrder();
        int countOrder = OrderDAO.INSTANCE.getTotalOrder();
        Map<String, Integer> orderWithCategory = OrderDAO.INSTANCE.getOrderWithCategory();
        Map<Integer, Integer> productSold = OrderDAO.INSTANCE.getProductSold();
        Map<String, Double> monthLy = OrderDAO.INSTANCE.getMonthlyMoney();
        double totalMoney = OrderDAO.INSTANCE.getAllTotalMoney();
        List<OrderDetail> listOrderDetail = OrderDAO.INSTANCE.getAllOrderDetail();
        request.setAttribute("data", list);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listAccount", listAccount);
        request.setAttribute("listCustomer", listCustomer);
        request.setAttribute("listOrderCus", listOrderCus);
        request.setAttribute("listOrder", listOrder);
        request.setAttribute("countOrder", countOrder);
        request.setAttribute("orderWithCategory", orderWithCategory);
        request.setAttribute("productSold", productSold);
        request.setAttribute("totalMoney", totalMoney);
        request.setAttribute("monthLy", monthLy);
        request.setAttribute("listOrderDetail", listOrderDetail);
        request.getRequestDispatcher("view/adminHome.jsp").forward(request, response);
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
        processRequest(request, response);
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
