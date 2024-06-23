/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asconvertmvn.dal;

import com.mycompany.asconvertmvn.model.Cart;
import com.mycompany.asconvertmvn.model.Customer;
import com.mycompany.asconvertmvn.model.Item;
import com.mycompany.asconvertmvn.model.Order;
import com.mycompany.asconvertmvn.model.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranm
 */
public class OrderDAO {

    private static final Logger logger = Logger.getLogger(OrderDAO.class.getName());

    public static final OrderDAO INSTANCE = new OrderDAO();
    private Connection con;

    public OrderDAO() {

        con = new DBContext().connection;

    }

    public boolean addOrder(Customer cus, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "insert into [Order](CustomerID, OrderDate) values (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, cus.getCustomerId());
            ps.setString(2, date);
            ps.executeUpdate();
            String sql1 = "select top 1 OrderId from [Order] order by OrderId desc";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                for (Item item : cart.getItems()) {
                    String sql2 = "insert into OrderDetail(OrderID, ProductID, Quantity, Price) values (?, ?, ?, ?)";
                    PreparedStatement ps2 = con.prepareStatement(sql2);
                    ps2.setInt(1, orderId);
                    ps2.setInt(2, item.getProduct().getProductId());
                    ps2.setInt(3, item.getQuantity());
                    ps2.setDouble(4, item.getPrice());
                    ps2.executeUpdate();
                }
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return false;
    }

    public boolean addOrderByCusId(int cusId, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "insert into [Order](CustomerID, OrderDate) values (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, cusId);
            ps.setString(2, date);
            ps.executeUpdate();
            String sql1 = "select top 1 OrderId from [Order] order by OrderId desc";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                for (Item item : cart.getItems()) {
                    String sql2 = "insert into OrderDetail(OrderID, ProductID, Quantity, Price) values (?, ?, ?, ?)";
                    PreparedStatement ps2 = con.prepareStatement(sql2);
                    ps2.setInt(1, orderId);
                    ps2.setInt(2, item.getProduct().getProductId());
                    ps2.setInt(3, item.getQuantity());
                    ps2.setDouble(4, item.getPrice());
                    ps2.executeUpdate();
                }
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return false;
    }

    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from [Order]";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("OrderID"));
                o.setCustomerId(rs.getInt("CustomerID"));
                o.setOrderDate(rs.getDate("OrderDate").toString());
                list.add(o);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return list;
    }

    public Map<String, Integer> getOrderWithCategory() {
        Map<String, Integer> orders = new HashMap<>();

        String sql = "SELECT c.CategoryName, COALESCE(SUM(od.Quantity), 0) AS TotalSold\n"
                + "FROM Category c\n"
                + "LEFT JOIN Product p ON c.CategoryID = p.CategoryID\n"
                + "LEFT JOIN OrderDetail od ON p.ProductID = od.ProductID\n"
                + "GROUP BY c.CategoryName;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                orders.put(rs.getString("CategoryName"), rs.getInt("TotalSold"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return orders;
    }

    public int getTotalOrder() {
        String sql = "select count(*) as TotalOrder from [Order]";
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("TotalOrder");

            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return count;
    }

    public Map<Integer, Integer> getProductSold() {
        Map<Integer, Integer> hash = new HashMap<>();
        String sql = "	SELECT p.ProductID, COUNT(o.OrderID) AS NumberOfOrders\n"
                + "FROM Product p\n"
                + "LEFT JOIN OrderDetail od ON p.ProductID = od.ProductID\n"
                + "LEFT JOIN [Order] o ON od.OrderID = o.OrderID\n"
                + "GROUP BY p.ProductID\n"
                + "ORDER BY NumberOfOrders DESC;";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                hash.put(rs.getInt("ProductId"), rs.getInt("NumberOfOrders"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return hash;
    }

    public double getAllTotalMoney() {
        String sql = "SELECT SUM(od.Quantity * od.Price) AS TotalEarnings\n"
                + "FROM [Order] o\n"
                + "JOIN OrderDetail od ON o.OrderID = od.OrderID";
        double total = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("TotalEarnings");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        }
        return total;
    }

    public Map<String, Double> getMonthlyMoney() {
        Map<String, Double> hash = new HashMap<>();
        String sql = "SELECT MONTH(o.OrderDate) AS Month, SUM(od.Quantity * od.Price) AS MonthlyTotalMoney\n"
                + "FROM  [Order] o INNER JOIN   OrderDetail od ON o.OrderID = od.OrderID GROUP BY  YEAR(o.OrderDate), MONTH(o.OrderDate)\n"
                + "ORDER BY  Month;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                hash.put(rs.getString("Month"), rs.getDouble("MonthlyTotalMoney"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return hash;

    }

    public List<OrderDetail> getAllOrderDetail() {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select * from  OrderDetail";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOrderId(rs.getInt("OrderID"));
                od.setProductId(rs.getInt("ProductID"));
                od.setQuantity(rs.getInt("Quantity"));
                od.setPrice(rs.getDouble("Price"));
                list.add(od);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }
        return list;
    }

    public void deleteOrderById(int orderId) {
        String sql = "delete OrderDetail where  OrderID = ?";
        PreparedStatement ps1 = null;

        try {
            ps1 = con.prepareStatement(sql);
            ps1.setInt(1, orderId);
            ps1.executeUpdate();
            String sql1 = "delete [Order] where  OrderID = ?";
            PreparedStatement ps2 = con.prepareStatement(sql1);
            ps2.setInt(1, orderId);
            ps2.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {

                if (ps1 != null) {
                    ps1.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }
    }

    public static void main(String[] args) {

    }
}
