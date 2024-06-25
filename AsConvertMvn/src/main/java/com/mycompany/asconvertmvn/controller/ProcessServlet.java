/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.asconvertmvn.controller;

import com.mycompany.asconvertmvn.dal.CartDAO;
import com.mycompany.asconvertmvn.dal.ProductDAO;
import com.mycompany.asconvertmvn.model.Cart;
import com.mycompany.asconvertmvn.model.Item;
import com.mycompany.asconvertmvn.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranm
 */
public class ProcessServlet extends HttpServlet {

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
            out.println("<title>Servlet ProcessServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            List<Product> list = ProductDAO.INSTANCE.getAllProduct();
            Cookie[] arr = request.getCookies();
            String cok = "";
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart")) {
                        cok += o.getValue();
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
            }

            Cart cart = new Cart(cok, list);
            int num = Integer.parseInt(request.getParameter("num").trim());
            int id = Integer.parseInt(request.getParameter("id"));
            Product p = null;
            try {
                p = ProductDAO.INSTANCE.getProductById(id);
            } catch (Exception ex) {
                Logger.getLogger(ProcessServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int numStore = p.getQuantity();
            if (num == -1 && (cart.getQuantityById(id) <= 1)) {
                cart.removeItem(id);
            } else {
                if (num == 1 && cart.getQuantityById(id) >= numStore) {
                    num = 0;
                }
                double price = p.getNewPrice();
                Item t = new Item();
                t.setProduct(p);
                t.setQuantity(num);
                t.setPrice(price);
                cart.addItem(t);
            }

            List<Item> items = cart.getItems();
            cok = "";

            if (items.size() > 0) {
                cok = items.get(0).getProduct().getProductId() + ":" + items.get(0).getQuantity();
                for (int i = 1; i < items.size(); i++) {
                    cok += "." + items.get(i).getProduct().getProductId() + ":" + items.get(i).getQuantity();
                }
            }

            Cookie c = new Cookie("cart", cok);
            c.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(c);
            request.setAttribute("cart", cart);
//            request.getRequestDispatcher("view/cart.jsp").forward(request, response);
            response.sendRedirect("cart");
        } else {

            int num = Integer.parseInt(request.getParameter("num").trim());
            int id = Integer.parseInt(request.getParameter("id"));
            int cartId = Integer.parseInt(request.getParameter("cart"));
            Cart cart = CartDAO.INSTANCE.getCartById(cartId);
            if (num == -1) {
                CartDAO.INSTANCE.deleteItemById(id, cartId);
            } else {
                Product p = null;
                try {
                    p = ProductDAO.INSTANCE.getProductById(id);
                } catch (Exception ex) {
                    Logger.getLogger(ProcessServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                int quantityItem = CartDAO.INSTANCE.getQuantityItem(id);
                int numStore = p.getQuantity();
                if (num == 1 && numStore > quantityItem) {
                    double price = p.getNewPrice();
                    Item t = new Item();
                    t.setProduct(p);
                    t.setQuantity(1);
                    t.setPrice(price);
                    cart.addItem(t);
                    List<Item> list = cart.getItems();
                    CartDAO.INSTANCE.addItemsToCart(list, cart);
                    request.setAttribute("numStore", list.size());
                }

            }

            response.sendRedirect("cart");

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
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            handleGuestCart(request, response);
        } else {
            handleUserCart(request, response);
        }
    }

    private void handleGuestCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> productList = ProductDAO.INSTANCE.getAllProduct();
        String updatedCart = updateAndClearCartCookies(request, response);
        Cart cart = new Cart(updatedCart, productList);
        request.setAttribute("cart", cart);
        response.sendRedirect("cart");
    }

    private String updateAndClearCartCookies(HttpServletRequest request, HttpServletResponse response) {
        String existingCart = getExistingCartAndClearCookies(request, response);
        String idToRemove = request.getParameter("id");
        String updatedCart = removeItemFromCart(existingCart, idToRemove);
        setUpdatedCartCookie(response, updatedCart);
        return updatedCart;
    }

    private String getExistingCartAndClearCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String existingCart = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    existingCart = cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return existingCart;
    }

    private String removeItemFromCart(String existingCart, String idToRemove) {
        StringBuilder updatedCart = new StringBuilder();
        String[] items = existingCart.split("\\.");
        for (String item : items) {
            String[] parts = item.split(":");
            if (!parts[0].equals(idToRemove)) {
                if (updatedCart.length() > 0) {
                    updatedCart.append(".");
                }
                updatedCart.append(item);
            }
        }
        return updatedCart.toString();
    }

    private void setUpdatedCartCookie(HttpServletResponse response, String updatedCart) {
        Cookie cookie = new Cookie("cart", updatedCart);
        cookie.setMaxAge(2 * 60 * 60 * 24);
        response.addCookie(cookie);
    }

    private void handleUserCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CartDAO.INSTANCE.deleteAllItemByProId(id);
        response.sendRedirect("cart");
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
