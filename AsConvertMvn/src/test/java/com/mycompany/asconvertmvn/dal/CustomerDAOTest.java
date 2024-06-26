/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.asconvertmvn.dal;

import com.mycompany.asconvertmvn.model.Customer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tranm
 */
public class CustomerDAOTest {

    public CustomerDAOTest() {
    }

    private CustomerDAO customerDAO;
    private List<Customer> testData;
    private List<Customer> updateData;
    private List<Integer> idData;
    private List<Integer> idDelete;

    @Before
    public void setUp() throws IOException {
        customerDAO = new CustomerDAO();
        testData = new ArrayList<>();
        updateData = new ArrayList<>();
        idData = new ArrayList<>();
        idDelete = new ArrayList<>();

        loadTestData("testdata.txt");
    }

    @After
    public void tearDown() {
        // Clean up after each test if necessary
    }

    @Test
    public void testInsertCustomers()throws Exception {
        boolean result = customerDAO.insertCustomerV2(testData.get(0));
        int newId = customerDAO.getCustomerIdAddNew();
        assertNotEquals(0, newId);
        assertTrue(result);
        System.out.println(result);
    }

    @Test
    public void testGetCustomerById() throws Exception{
        Customer retrievedCustomer = customerDAO.getCustomerById(idData.get(1));
        assertNotNull(retrievedCustomer);
        System.out.println(retrievedCustomer);
    }

    @Test
    public void testUpdateCustomer() {
        boolean result = customerDAO.updateCustomerInfo(updateData.get(3));
        assertTrue(result);
    }

    @Test
    public void testDeleteCustomer() {
        boolean result = customerDAO.deleteCustomerById(idDelete.get(0));
        assertTrue(result);
    }

    private void loadTestData(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String testCase = parts[0];
                if (testCase.equalsIgnoreCase("is")) {
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String email = parts[3];
                    String phone = parts[4];
                    String address = parts[5];
                    String gender = parts[6];
                    testData.add(new Customer(firstName, lastName, email, phone, address, gender));
                } else if (testCase.equalsIgnoreCase("up")) {
                    int id = Integer.parseInt(parts[1]);
                    String firstName = parts[2];
                    String lastName = parts[3];
                    String email = parts[4];
                    String phone = parts[5];
                    String address = parts[6];
                    String gender = parts[7];
                    updateData.add(new Customer(id, firstName, lastName, email, phone, address, gender));
                } else if (testCase.equalsIgnoreCase("get")) {
                    int id = Integer.parseInt(parts[1]);
                    idData.add(id);
                } else if (testCase.equalsIgnoreCase("del")) {
                    int id = Integer.parseInt(parts[1]);
                    idDelete.add(id);
                }
            }
        }

    }
}
