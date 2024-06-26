/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asconvertmvn.dal;

import com.mycompany.asconvertmvn.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author tranm
 */
public class AccountDAO {

    private static final Logger logger = Logger.getLogger(AccountDAO.class.getName());
    public static final AccountDAO INSTANCE = new AccountDAO();
    public final String USERNAME = "UserName";
    public final String PASSWORD = "Password";
    public final String CUSTOMERID = "CustomerID";

    private Connection con;

    public AccountDAO() {

        con = new DBContext().connection;

    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select UserName, Password,role, c.CustomerID from Account a left outer join Customer c ON a.CustomerID = c.CustomerID";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString(USERNAME));
                a.setPassword(rs.getString(PASSWORD));
                a.setRole(rs.getInt("role"));
                a.setCustomerId(rs.getInt(CUSTOMERID));
                list.add(a);
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

    public Account getAccountByUser(String user) {
        String sql = "select * from Account where UserName = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString(USERNAME));
                a.setPassword(rs.getString(PASSWORD));
                a.setRole(rs.getInt("role"));
                a.setCustomerId(rs.getInt(CUSTOMERID));

                return a;

            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }
        return null;
    }

    public void insertAccount(Account a, int cusId) {
        String sql = "Insert into Account(UserName, Password, CustomerID) values (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getUserName());
            ps.setString(2, a.getPassword());
            ps.setInt(3, cusId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public void insertAccountAdmin(Account a) {
        String sql = "Insert into Account(UserName, Password, role) values (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getUserName());
            ps.setString(2, a.getPassword());
            ps.setInt(3, a.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public Account checkAccount(String user, String password) {
        String sql = "select * from Account a  where a.UserName = ? and a.[Password] = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString(USERNAME));
                a.setPassword(rs.getString(PASSWORD));
                a.setRole(rs.getInt("role"));
                a.setCustomerId(rs.getInt(CUSTOMERID));
                return a;
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

        return null;
    }

    public void updateAccountRole(String user, int role) {
        String sql = "Update Account Set [role] = ? where UserName = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role);
            ps.setString(2, user);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public Map<Integer, Integer> getAllToalOrderByCus() {
        Map<Integer, Integer> hashmap = new HashMap<>();

        String sql = "select c.CustomerID, count(o.CustomerID) as  TotalOrder  from [Order] o right outer join Customer c  ON o.CustomerID = c.CustomerID\n"
                + "group by o.CustomerID, c.CustomerID order by c.CustomerID";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                hashmap.put(rs.getInt(CUSTOMERID), rs.getInt("TotalOrder"));
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

        return hashmap;
    }

    public boolean deleteAccountByUser(String user) {
        String sql = "DELETE FROM Account WHERE UserName = ?";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return false;
    }

    public void changePassword(String user, String password) {
        String sql = "Update Account SET [Password] = ? where UserName = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, user);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public static void main(String[] args) {

    }
}
