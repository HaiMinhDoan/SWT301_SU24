/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.asconvertmvn.controller;

import com.mycompany.asconvertmvn.dal.ProductDAO;
import com.mycompany.asconvertmvn.model.Category;
import com.mycompany.asconvertmvn.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranm
 */
public class UpdateProductServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductServlet at " + request.getContextPath() + "</h1>");
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
        String idRaw = request.getParameter("id");

        if (idRaw != null) {
            int id = Integer.parseInt(idRaw);
            boolean isUpdate = Boolean.parseBoolean(request.getParameter("isUpdate"));
            Product p = null;
            try {
                p = ProductDAO.INSTANCE.getProductById(id);
            } catch (Exception ex) {
                Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("isUpdate", isUpdate);
            request.setAttribute("productUpate", p);
            request.getRequestDispatcher("adminHome").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        String categoryName = request.getParameter("categoryName");
        int quanlity = Integer.parseInt(request.getParameter("quality"));
        double sale = Double.parseDouble(request.getParameter("sale"));
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        Product p = new Product();
        p.setProductId(id);
        p.setProductName(productName);
        p.setImg(image);
        p.setQuantity(quanlity);
        p.setPrice(price);
        p.setSale(sale);
        p.setDescription(description);
        Category c = new Category();
        c.setCategoryName(categoryName);
        List<Category> listCategory = ProductDAO.INSTANCE.getAllCategory();
        for (Category category : listCategory) {
            if (category.getCategoryName().equals(categoryName)) {
                c.setCategoryId(category.getCategoryId());
            }
        }
        p.setCategory(c);
        boolean check = false;
        try {
            check = ProductDAO.INSTANCE.updateProduct(p);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (check) {
            request.getSession().setAttribute("messUpdate", "Chỉnh sửa sản phẩm thành công");
        } else {
            request.getSession().setAttribute("messUpdate", "Chỉnh sửa sản phẩm thất bại");
        }

        response.sendRedirect("adminHome");
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
