<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Admin Home Page</title>

        <link rel="stylesheet" href="/store/css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <style>
            #admin-home {
                margin-top: 150px;
            }

            .content-admin {
                display: none;
            }
            .content-active {
                display: block;

            }

            .sidebar {
                max-height: 500px;
            }

            .home-product-item__delete {
                position: absolute;
                left: 0;
                top: -2px;
                width: 25px;
                height: 25px;
                text-align: center;
                border-top-right-radius: 2px;
                background: #000;



            }

            .home-product-item__sale-icon {
                color: #fff;
            }

            .add-product-box {
                border: 2px dashed #ddd;
                padding: 20px;
                text-align: center;
                cursor: pointer;
                height: 100%;
                display: flex;
                text-decoration: none;
                color: #777;
                flex-direction: column;


            }
            .add-product-icon {
                font-size: 48px;
                color: #777;
            }

            .modal-add {
                position: fixed;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                display: flex;
                animation: fadeIn ease-in 0.1s;
                z-index: 100;
            }

            .add-close-btn {
                position: absolute;
                right: 11px;
                top: 1px;
                color: #777;
                font-size: 35px;
                text-decoration: none;
                cursor: pointer;

            }

            .add-close-btn:hover {
                color: red;
            }

            .form--select {
                width: 100%;
                height: 50px;
                margin-bottom: 1.25em;
                border-radius: 5px;
                border: 1px solid gray;
                padding: 0.8em;
                font-family: 'Inter', sans-serif;
                outline: none;
            }

            .form-add--product{
                margin-top: 80px;
            }

            .wrap-input{
                width: 100%;

            }

            .form--input-update{
                padding: 10px;
            }

            .form--input-update{
                margin-bottom: 5px;
            }

            .statics__container {
                display: none;
            }

            .statics__container-active {
                display: block;
            }

            #static__body{
                margin-top: 30px;
            }

            .static__order {
                background-color: #f5f5f9;
            }
            .static__order--product {
                display: flex;
                justify-content: space-between;
            }

            .static__order {
                height: 600px;
                width: 400px;
                margin: auto;
            }


            .static__order--header {
                padding-top: 20px;
                padding-bottom: 20px;
            }

            .static__order--total {
                margin-bottom: 40px;
            }
            .static__order--product {
                font-size: 20px;
            }
            .static__order--product-icon {
                padding-right: 5px;
            }

            .static__order--product-title {
            }

            .phone {
                color: #696cff;
            }

            .laptop {
                color: #71dd37;
            }

            .watch {
                color: #03c2eb;
            }

            .headphone {
                color: #8592a3;
            }

            .statics-filter__btn{
                background-color: var(--white-color);
                margin-right: 12px;
                min-width: 124px;
                height: 36px;
                border: none;
                text-decoration: none;
                border-radius: 3px;
                font-size: 1rem;
                padding: 0 12px;
                outline: none;
                color: var(--accent-color);
                display: inline-flex;
                justify-content: center;
                align-items: center;
                line-height: 1.6rem;
            }

            .statics-link-active{
                background-color:  #72aec8;
                color: black;
                font-size: 16px;
            }

            .h1 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }
            .product-list {
                list-style: none;
                padding: 0;
                margin: 0;
            }
            .product-item {
                border-bottom: 1px solid #ccc;
                padding: 10px 0;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .product-item:last-child {
                border-bottom: none;
            }
            .product-name {
                font-weight: bold;
                color: #555;
            }

            .sales-info {
                font-style: italic;
                color: #888;
            }
            .sales-icon {
                color: #4CAF50; /* Màu xanh lá cây */
            }

            .home-product-item__price-old.price-sell {
                text-decoration: none;
            }

            .sidebar {
                background-color: #f8f9fa;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .sidebar-heading {
                font-size: 1.5rem;
                color: #333;
                margin-bottom: 20px;
            }

            .category-link {
                display: block;
                color: #555;
                text-decoration: none;
                padding: 10px 0;
                transition: color 0.3s ease;
            }

            .category-link:hover {
                color: #007bff;
            }

            .category-link::before {
                content: "\2022"; /* Bullet symbol */
                color: #007bff;
                display: inline-block;
                width: 1em;
                margin-right: 5px;
            }

            [data-category="product"]::before {
                content: "\25B8"; /* Right arrow symbol */
                color: #007bff;
            }

            [data-category="account"]::before {
                content: "\25B8"; /* Right arrow symbol */
                color: #007bff;
            }

            [data-category="customer"]::before {
                content: "\25B8"; /* Right arrow symbol */
                color: #007bff;
            }

            [data-category="invoice"]::before {
                content: "\25B8"; /* Right arrow symbol */
                color: #007bff;
            }

            [data-category="statistics"]::before {
                content: "\25B8"; /* Right arrow symbol */
                color: #007bff;
            }

            .product-title-wrapper{
                display: flex;
            }

            .product-title-wrapper a {
                text-decoration: none;
                font-size: 20px;
                margin-left: 100px;
            }

        </style>


    </head>

    <body>


        <div>
            <jsp:include page="header.jsp" />
        </div>


        <div id="admin-home">

            <c:if test="${sessionScope.mess != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.mess}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="mess" scope="session" />
            </c:if>



            <c:if test="${sessionScope.messDeleteCus != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messDeleteCus}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="messDeleteCus" scope="session" />
            </c:if>


            <c:if test="${sessionScope.messDeleteOrder != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messDeleteOrder}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="messDeleteOrder" scope="session" />
            </c:if>

            <c:if test="${sessionScope.messDeleteAcc != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messDeleteAcc}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="messDeleteAcc" scope="session" />
            </c:if>

            <c:if test="${sessionScope.messDeletePro != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messDeletePro}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="messDeletePro" scope="session" />
            </c:if>







            <c:if test="${sessionScope.messUpdate != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messUpdate}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="messUpdate" scope="session" />
            </c:if>

            <c:if test="${sessionScope.messChange != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messChange}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="messChange" scope="session" />
            </c:if>

            <c:if test="${sessionScope.messCreate != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messCreate}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="messCreate" scope="session" />
            </c:if>


            <c:if test="${sessionScope.messAddCate != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messAddCate}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="messAddCate" scope="session" />
            </c:if>

            <div class="container">
                <div class="row">
                    <div class="col-md-2">
                        <div class="sidebar">
                            <h2 class="sidebar-heading">Danh mục</h2>
                            <a href="#" class="category-link" data-category="product">Quản lí sản phẩm</a>
                            <a href="#" class="category-link" data-category="account">Tài Khoản</a>
                            <a href="#" class="category-link" data-category="customer">Khách Hàng</a>
                            <a href="#" class="category-link" data-category="invoice">Hóa Đơn</a>
                            <a href="#" class="category-link" data-category="statistics">Thống kê</a>
                        </div>
                    </div>
                    <div class="col-md-10">
                        <!-- Content container -->
                        <div class="content">
                            <div class="content-admin content-active">
                                <div class="product-title-wrapper">
                                    <h2>Quản lí sản phẩm</h2>
                                    <a href="addCategory?action=true">Thêm loại sản phẩm</a>
                                </div>
                                <div class="home-product">
                                    <div class="row">
                                        <div class="col-md-4 mx-auto">
                                            <a href="add?action=click" class="add-product-box">
                                                <span class="add-product-icon">&#43;</span>
                                                <span>Thêm sản phẩm</span>
                                            </a>
                                        </div>
                                        <c:forEach var="product" items="${requestScope.data}">

                                            <div class="col-md-4">
                                                <div href="" class="home-product-item">
                                                    <div class="home-product-item__img"
                                                         style="background-image: url(${product.img});">
                                                    </div>
                                                    <h4 class="home-product-item__name">
                                                        ${product.productName}</h4>
                                                    <div class="home-product-item__price">
                                                        <c:choose>
                                                            <c:when test="${product.sale > 0}">
                                                                <span
                                                                    class="home-product-item__price-old">${product.price}</span>
                                                                <span
                                                                    class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span
                                                                    class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>

                                                    <div class="home-product-item__action">

                                                        <span class="home-product-item__sold">Số lượng:
                                                            ${product.quantity}</span>
                                                        <a href="update?id=${product.productId}&isUpdate=true"><button
                                                                class="btn-update"> Cập nhật
                                                            </button></a>
                                                    </div>

                                                    <div class="home-product-item__origin">
                                                        <span
                                                            class="home-product-item__brand">${(product.category).categoryName}</span>
                                                        <span class="home-product-item__origin-name">Việt Nam</span>
                                                    </div>

                                                    <div class="home-product-item__sale">
                                                        <span
                                                            class="home-product-item__sale-percent">${product.getPercentSale()}%</span>
                                                        <span
                                                            class="home-product-item__sale-label">Giảm</span>
                                                    </div>

                                                    <div class="home-product-item__delete">
                                                        <a class="home-product-item__delete-link"
                                                           href="delete?id=${product.productId}" onclick="return confirmDelete()">
                                                            <span class="home-product-item__sale-icon"><i
                                                                    style="font-size: 18px;"
                                                                    class="fa-solid fa-xmark"></i></span>
                                                        </a>
                                                    </div>
                                                </div>

                                            </div>

                                        </c:forEach>
                                    </div>

                                </div>
                            </div>
                            <div class="content-admin">
                                <h2>Danh sách tài khoản</h2>
                                <div>
                                    <table class="custom-table">
                                        <thead>
                                            <tr>
                                                <th class="table-header">Tên tài khoản</th>
                                                <th class="table-header">Mật khẩu</th>
                                                <th class="table-header">Quyền</th>
                                                <th class="table-header"></th>
                                                <th class="table-header"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="accountList" items="${listAccount}">   
                                                <c:if test="${sessionScope.account.userName ne accountList.userName}">
                                                    <tr>
                                                        <td class="table-cell">${accountList.userName}</td>
                                                        <td class="table-cell">${accountList.password}</td>
                                                        <c:if test="${accountList.role == 0}">
                                                            <td class="table-cell">Admin</td>
                                                        </c:if>
                                                        <c:if test="${accountList.role == 1}">
                                                            <td class="table-cell">User</td>
                                                        </c:if>
                                                        <c:if test="${accountList.role == 0}">
                                                            <td class="table-cell"> 
                                                                <button class="btn__remove--admin">

                                                                    <a style="text-decoration: none; color: #fff" href="changeAdmin?user=${accountList.userName}&action=remove"
                                                                       onclick="return confirmChange('remove')"
                                                                       >
                                                                        Xóa quyền Admin
                                                                    </a>
                                                                </button>                                                        
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${accountList.role == 1}">
                                                            <td class="table-cell"> 
                                                                <button class="btn__add--admin">
                                                                    <a style="text-decoration: none; color: #fff" href="changeAdmin?user=${accountList.userName}&action=add"
                                                                       onclick="return confirmChange('add')"
                                                                       >
                                                                        Cấp quyền Admin
                                                                    </a>
                                                                </button>                                                        
                                                            </td>
                                                        </c:if>
                                                        <td>
                                                            <a href="deleteAccount?user=${accountList.userName}"  onclick="return confirmDeleteAcc('${accountList.userName}')"><i class="fa-solid fa-x"></i></a>
                                                        </td>
                                                    </tr>
                                                </c:if>

                                            </c:forEach> 
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="content-admin">
                                <h2>Khách Hàng</h2>
                                <div>
                                    <table class="custom-table">
                                        <tr>
                                            <th>ID</th>   
                                            <th>Tên</th>
                                            <th>Email</th>
                                            <th>Liên hệ</th>
                                            <th>Địa chỉ</th>
                                            <th>Giới tính</th>
                                            <th>Đơn hàng</th>

                                        </tr>
                                        <c:forEach var="customers" items="${requestScope.listCustomer}">
                                            <tr>
                                                <td>${customers.customerId}</td>
                                                <td>${customers.getFullName()}</td>
                                                <td>${customers.email}</td>
                                                <td>${customers.phone}</td>
                                                <td>${customers.address}</td>
                                                <td>${customers.gender}</td>
                                                <c:forEach var="orderEntry" items="${requestScope.listOrderCus.entrySet()}">
                                                    <c:if test="${orderEntry.key == customers.customerId}">
                                                        <td>${orderEntry.value}</td>
                                                    </c:if>
                                                </c:forEach>

                                            </tr>
                                        </c:forEach>
                                    </table>


                                </div>
                            </div>
                            <div class="content-admin">
                                <h2>Hóa Đơn</h2>
                                <div>
                                    <table class="custom-table">
                                        <thead>
                                            <tr>
                                                <th class="table-header">Mã hóa đơn</th>
                                                <th class="table-header">Tên khách hàng</th>
                                                <th class="table-header">Ngày mua hàng</th>
                                                <th class="table-header"></th>


                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="listOrder" items="${requestScope.listOrder}">   
                                                <tr>
                                                    <td class="table-cell">${listOrder.orderId}</td>  
                                                    <c:forEach var="customers" items="${requestScope.listCustomer}">
                                                        <c:if test="${listOrder.customerId eq customers.customerId}">
                                                            <td class="table-cell">${customers.getFullName()}</td>   
                                                        </c:if>    
                                                    </c:forEach>
                                                    <c:if test="${listOrder.customerId == 0}">
                                                        <td class="table-cell">Đã xóa khỏi hệ thống</td>     
                                                    </c:if>        
                                                    <td class="table-cell">${listOrder.orderDate}</td>   
                                                    <td ><a href="deleteOrder?orderId=${listOrder.orderId}" onclick="return confirmDeleteOrder('${listOrder.orderId}')">

                                                            <i class="fa-solid fa-xmark"></i>
                                                        </a></td>                                   

                                                </tr>

                                            </c:forEach> 
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="content-admin">
                                <div class="home-filter hide-on-mobile-tablet">
                                    <a href="#" class="statics-filter__btn statics-link-active statics-link">Loại sản phẩm</a>
                                    <a href="#" class="statics-filter__btn statics-link">Sản phẩm</a>
                                    <a href="#" class="statics-filter__btn statics-link">Doanh thu</a>
                                    <a href="#" class="statics-filter__btn statics-link">Hóa đơn chi tiết</a>

                                </div>
                                <div class="statics__container statics__container-active">
                                    <h2 style="margin-top: 10px">Thống kê loại sản phẩm</h2>
                                    <div id="static__body">
                                        <div class="static__order">
                                            <div class="container">
                                                <c:set value="${requestScope.countOrder}" var="countOrder"/>
                                                <div class="static__order--total">
                                                    <h4 style="margin-top: 10px">Tổng hóa đơn: ${countOrder} </h4>

                                                    <canvas id="pieChart" height="320" width="320"></canvas>

                                                </div>
                                                <c:forEach items="${requestScope.orderWithCategory.entrySet()}" var="orderWithCategory">

                                                    <c:forEach items="${requestScope.listCategory}" var="listCategory">
                                                        <c:if test="${listCategory.categoryName eq orderWithCategory.key}">
                                                            <c:if test="${listCategory.categoryName eq 'Laptop'}">
                                                                <div class="static__order--detail">
                                                                    <div class="static__order--product">
                                                                        <div class="wrapper">
                                                                            <span class="static__order--product-icon laptop">
                                                                                <i class="fa-solid fa-laptop"></i>
                                                                            </span>
                                                                            <span class="static__order--product-title">${orderWithCategory.key}</span>
                                                                        </div>
                                                                        <span class="static__order--product-laptop">${orderWithCategory.value}</span>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${listCategory.categoryName eq 'Phone'}">
                                                                <div class="static__order--detail">
                                                                    <div class="static__order--product">
                                                                        <div class="wrapper">
                                                                            <span class="static__order--product-icon phone">
                                                                                <i class="fa-solid fa-mobile-screen-button">
                                                                                </i>
                                                                            </span>
                                                                            <span class="static__order--product-title">${orderWithCategory.key}</span>
                                                                        </div>


                                                                        <span class="static__order--product-phone">${orderWithCategory.value}</span>
                                                                    </div>
                                                                </div>

                                                            </c:if>
                                                            <c:if test="${listCategory.categoryName eq 'Headphone'}">
                                                                <div class="static__order--detail">
                                                                    <div class="static__order--product">
                                                                        <div class="wrapper">
                                                                            <span class="static__order--product-icon headphone">
                                                                                <i class="fa-solid fa-headphones"></i>
                                                                            </span>
                                                                            <span class="static__order--product-title">${orderWithCategory.key}</span>
                                                                        </div>
                                                                        <span class="static__order--product-headphone">${orderWithCategory.value}</span>
                                                                    </div>
                                                                </div>

                                                            </c:if>

                                                            <c:if test="${listCategory.categoryName eq 'Watche'}">
                                                                <div class="static__order--detail">
                                                                    <div class="static__order--product">
                                                                        <div class="wrapper">
                                                                            <span class="static__order--product-icon watch">
                                                                                <i class="fa-regular fa-clock"></i>
                                                                            </span>
                                                                            <span class="static__order--product-title">${orderWithCategory.key}</span>
                                                                        </div>
                                                                        <span class="static__order--product-watch">${orderWithCategory.value}</span>
                                                                    </div>
                                                                </div>
                                                            </c:if>

                                                        </c:if>
                                                    </c:forEach>



                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="statics__container">

                                    <h1 class="h1">Danh sách sản phẩm <i class="fas fa-shopping-cart"></i></h1>

                                    <ul class="product-list">
                                        <c:forEach items="${requestScope.data}" var="productSell">
                                            <c:forEach var="entry" items="${requestScope.productSold.entrySet()}">
                                                <c:set var="productId" value="${entry.key}" />
                                                <c:set var="quantity" value="${entry.value}" />

                                                <c:if test="${productSell.productId eq productId}">
                                                    <li class="product-item">
                                                        <span class="product-name">${productSell.productName}</span> <span class="sales-info"><i class="fas fa-chart-line sales-icon"></i> ${quantity}</span>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>

                                    </ul>

                                </div>    







                                <div class="statics__container">
                                    <div class="container-earning">
                                        <h1 class="earning-title">Doanh thu hàng tháng</h1>
                                        <canvas id="earningsChart"></canvas>
                                        <div class="total-earnings">
                                            <c:set var="total" value="${requestScope.totalMoney}"/>
                                            <h2>Tổng doanh thu</h2>
                                            <span class="home-product-item__price-old price-sell">${total}</span>
                                            <c:forEach var="entry" items="${requestScope.monthLy.entrySet()}">
                                                <c:set var="month" value="${entry.key}" />
                                                <c:set var="earning" value="${entry.value}" />
                                                <p style="display: none" class="month">${month}</p>
                                                <p style="display: none" class="home-product-item__price-old price-sell earning">${earning}</p>
                                            </c:forEach>
                                        </div>
                                    </div> 
                                </div>

                                <div class="statics__container">
                                    <table class="custom-table">
                                        <thead>
                                            <tr>
                                                <th class="table-header">Mã hóa đơn</th>
                                                <th class="table-header">Tên sản phẩm</th>
                                                <th class="table-header">Số lượng </th>
                                                <th class="table-header">Thành tiền</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="orderDetails" items="${requestScope.listOrderDetail}">
                                                <tr>  
                                                    <td class="table-cell">${orderDetails.orderId}</td>  
                                                    <c:forEach var="product" items="${requestScope.data}">
                                                        <c:if test="${product.productId == orderDetails.productId}">
                                                            <td class="table-cell">${product.productName}</td>  
                                                        </c:if>

                                                    </c:forEach>
                                                    <c:if test="${orderDetails.productId == 0}">
                                                        <td class="table-cell">Đã xóa khỏi hệ thống</td>     
                                                    </c:if>  
                                                    <td class="table-cell">${orderDetails.quantity}</td>  
                                                    <td class="table-cell order-total">${orderDetails.price}</td>                                   
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>          
                            </div>
                        </div>
                    </div>
                </div>




                <c:set var="click" value="${requestScope.isClick}" />
                <c:if test="${click eq 'click'}">
                    <div class="modal-add--container">
                        <div class="modal-add">
                            <div class="modal-add--overlay">
                            </div>
                            <div class="modal-add--body">
                                <form class="form-add--product" action="add" method="post">
                                    <span class="add-close-btn">×</span>
                                    <span class="add">Thêm sản phẩm</span>
                                    <input name="productName" type="text" placeholder="Tên sản phẩm"
                                           class="form--input">
                                    <select name="categoryName" class="form--select">
                                        <option value="" disabled selected>Loại sản phẩm</option>
                                        <c:forEach var="category" items="${requestScope.listCategory}">
                                            <option value="${category.categoryName}">${category.categoryName}</option>
                                        </c:forEach>
                                    </select>

                                    <input name="quality" type="number" min="1" placeholder="Số lượng sản phẩm"
                                           class="form--input">
                                    <input name="sale" type="number" step="0.01" min="0" placeholder="Giảm giá"
                                           class="form--input">
                                    <input name="price" type="number" min="1" placeholder="Giá" class="form--input">
                                    <input name="image" type="text" placeholder="Ảnh" class="form--input">
                                    <input name="description" type="text" placeholder="Mô tả" class="form--input">
                                    <button class="form--submit" type="submit">
                                        Thêm
                                    </button>
                                </form>
                            </div>

                        </div>
                    </div>

                </c:if>

                <c:set var="isUpate" value="${requestScope.isUpdate}" />
                <c:if test="${isUpate}">
                    <div class="modal-add--container">
                        <div class="modal-add">
                            <div class="modal-add--overlay">
                            </div>
                            <div class="modal-add--body">
                                <form class="form-add--product form-update--product" action="update" method="post">
                                    <c:set var="productUpate" value="${requestScope.productUpate}" />
                                    <span class="add-close-btn">×</span>
                                    <span class="add">Chỉnh sửa sản phẩm</span>  
                                    <div style="display: none;" class="wrap-input">
                                        <span>Mã sản phẩm</span>
                                        <input name="productId" type="text" placeholder="Tên sản phẩm" value="${productUpate.productId}"
                                               class="form--input form--input-update">
                                    </div>  
                                    <div class="wrap-input">
                                        <span>Tên sản phẩm</span>
                                        <input name="productName" type="text" placeholder="Tên sản phẩm" value="${productUpate.productName}"
                                               class="form--input form--input-update">
                                    </div>  
                                    <div class="wrap-input">
                                        <span>Loại sản phẩm</span>
                                        <input name="categoryName" type="text" readonly=""
                                               value="${productUpate.category.categoryName}" placeholder="Loại sản phẩm" class="form--input form--input-update">
                                    </div> 

                                    <div class="wrap-input">
                                        <span>Số lượng sản phẩm</span>
                                        <input name="quality" type="number" min="0" value="${productUpate.quantity}"
                                               placeholder="Số lượng sản phẩm" class="form--input form--input-update">
                                    </div>
                                    <div class="wrap-input">
                                        <span>Giảm giá</span>
                                        <input name="sale" type="number" step="0.01" value="${productUpate.sale}" min="0"
                                               placeholder="Giảm giá" class="form--input form--input-update">
                                    </div>
                                    <div class="wrap-input">
                                        <span>Giá</span>
                                        <input name="price" type="number" step="0.01" min="1" value="${productUpate.price}" placeholder="Giá"
                                               class="form--input price-format form--input-update">
                                    </div>  
                                    <div class="wrap-input">
                                        <span>Ảnh sản phẩm</span>
                                        <input name="image" type="text" value="${productUpate.img}" placeholder="Ảnh" class="form--input form--input-update">
                                    </div>  
                                    <div class="wrap-input">
                                        <span>Mô tả sản phẩm</span>
                                        <input name="description" type="text" value="${productUpate.description}" placeholder="Mô tả" class="form--input form--input-update">
                                    </div>  
                                    <button class="form--submit" type="submit">
                                        Chỉnh sửa
                                    </button>
                                </form>
                            </div>

                        </div>
                    </div>

                </c:if>


                <c:set var="isAddCate" value="${requestScope.isAddCate}" />
                <c:if test="${isAddCate}">
                    <div class="modal-add--container">
                        <div class="modal-add">
                            <div class="modal-add--overlay">
                            </div>
                            <div class="modal-add--body">
                                <form class="form-add--product" action="addCategory" method="post">
                                    <span class="add-close-btn">×</span>
                                    <span class="add">Thêm loại sản phẩm</span>
                                    <input name="categoryName" type="text" placeholder="Nhập tên loại sản phẩm" class="form--input">
                                    <button class="form--submit" type="submit">
                                        Thêm
                                    </button>

                                </form>
                            </div>

                        </div>
                    </div>

                </c:if>
















            </div>
        </div>
        <script>
            const categorys = document.querySelectorAll('.category-link');
            const contents = document.querySelectorAll('.content-admin');
            const closeBtn = document.querySelector('.add-close-btn');
            const modalAdd = document.querySelector('.modal-add--container');
            const deleteProduct = document.querySelectorAll('.home-product-item__delete-link');
            const phoneTotal = parseFloat(document.querySelector('.static__order--product-phone').innerText);
            const laptopTotal = parseFloat(document.querySelector('.static__order--product-laptop').innerText);
            const watchTotal = parseFloat(document.querySelector('.static__order--product-watch').innerText);
            const headphoneTotal = parseFloat(document.querySelector('.static__order--product-headphone').innerText);
            const staticsWrappers = document.querySelectorAll('.statics__container');
            const staticsLink = document.querySelectorAll('.statics-link');
            const staticLinkBtns = document.querySelectorAll('.statics-filter__btn');

            staticLinkBtns.forEach(btn => {
                btn.addEventListener('click', function () {
                    staticLinkBtns.forEach(btn => {
                        btn.classList.remove('statics-link-active');
                    });
                    this.classList.add('statics-link-active');

                });
            });


            staticsLink.forEach((s, index) => {
                const content = staticsWrappers[index];
                s.onclick = function () {
                    document.querySelector('.statics__container.statics__container-active').classList.remove('statics__container-active');
                    content.classList.add('statics__container-active');
                }

            });


            // Tạo mảng chứa dữ liệu
            const data = [laptopTotal, watchTotal, phoneTotal, headphoneTotal];

            // Tạo mảng chứa màu sắc
            const colors = ['#696cff', '#71dd37', '#03c2eb', '#8592a3'];
            const chartSize = 3200;
            // Lấy thẻ canvas và cấu hình biểu đồ tròn
            const ctx = document.getElementById('pieChart').getContext('2d');
            const pieChart = new Chart(ctx, {
                type: 'doughnut',
                data: {
                    labels: ['Laptop', 'Watche', 'Phone', 'Headphone'],
                    datasets: [{
                            data: data,
                            backgroundColor: colors,
                        }]
                },

            });


            // script.js

            document.addEventListener("DOMContentLoaded", function () {
                var labels = [];
                var data = [];

                // Lấy các phần tử có lớp là 'month' và 'earning'
                var monthElements = document.querySelectorAll('.month');
                var earningElements = document.querySelectorAll('.earning');

                // Lấy dữ liệu từ các phần tử và lưu vào mảng
                monthElements.forEach(function (monthElement, index) {
                    var month = monthElement.innerText;
                    var earning = parseFloat(earningElements[index].innerText.replace(/[^0-9.-]+/g, "")); // Loại bỏ ký tự không phải số
                    labels.push(month);
                    data.push(earning);
                });

                var ctx = document.getElementById('earningsChart').getContext('2d');
                var earningsChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                                label: 'Doanh thu mỗi tháng',
                                data: data,
                                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                                borderColor: 'rgba(54, 162, 235, 1)',
                                borderWidth: 1
                            }]
                    },
                    options: {
                        indexAxis: 'y',
                        scales: {
                            xAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        callback: function (value, index, values) {
                                            return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' VND';
                                        }
                                    }
                                }]
                        }
                    }
                });
                // Hiển thị tổng doanh thu
                var total = "${requestScope.totalMoney}";
                document.querySelector('.total-earnings p').innerText = total;
            });


            function formatCurrency(price) {
                return price.toFixed(0).replace(/\d(?=(\d{3})+$)/g, '$&,');
            }

            // Sử dụng hàm formatCurrency để định dạng giá sản phẩm
            document.querySelectorAll('.home-product-item__price-old').forEach(element => {
                // Lấy giá trị hiện tại
                let price = parseFloat(element.textContent);
                // Định dạng lại giá trị
                let formattedPrice = formatCurrency(price);
                // Gán giá trị mới cho phần tử
                element.textContent = formattedPrice + 'đ';
            });
            document.querySelectorAll('.home-product-item__price-curr').forEach(element => {
                // Lấy giá trị hiện tại
                let price = parseFloat(element.textContent);
                // Định dạng lại giá trị
                let formattedPrice = formatCurrency(price);
                // Gán giá trị mới cho phần tử
                element.textContent = formattedPrice + 'đ';
            });


            document.querySelectorAll('.order-total').forEach(element => {
                // Lấy giá trị hiện tại
                let price = parseFloat(element.textContent);
                // Định dạng lại giá trị
                let formattedPrice = formatCurrency(price);
                // Gán giá trị mới cho phần tử
                element.textContent = formattedPrice + 'đ';
            });

            categorys.forEach((category, index) => {
                const content = contents[index];
                category.onclick = function () {
                    document.querySelector('.content-admin.content-active').classList.remove('content-active');
                    content.classList.add('content-active');
                }

            });


            // Function to hide the toast after 3 seconds
            function hideToast() {
                const toast = document.querySelector('.toast-add');
                toast.style.animation = 'slideOutRight ease .3s';
                setTimeout(() => {
                    toast.style.display = 'none';
                }, 300); // 300 milliseconds for slideOutRight animation duration
            }

            // Add this line in your code where you display the toast
            setTimeout(hideToast, 3000); // 3000 milliseconds for 3 seconds delay

            setTimeout(function () {
                var toastContainer = document.querySelector('.toast-add--container');
                if (toastContainer) {
                    toastContainer.remove();
                }
            }, 3000); // 3000 milliseconds for 3 seconds delay


            closeBtn.addEventListener('click', () => {
                modalAdd.style.display = 'none';
            });

            document.querySelectorAll('input.price-format').forEach(input => {
                let value = parseFloat(input.value);
                input.value = value;
            });



            function confirmDelete() {
                // Hiển thị hộp thoại xác nhận
                var result = confirm("Bạn có chắc muốn xóa sản phẩm này?");
                // Nếu người dùng đồng ý xóa, trả về true để tiếp tục chuyển hướng, ngược lại trả về false
                return result;
            }





            function confirmChange(action) {
                let result;
                if (action === 'add') {
                    result = confirm("Bạn có chắc muốn thêm người này thành quản trị viên?");
                } else {
                    result = confirm("Bạn có chắc muốn xóa quản trị viên này?");
                }
            }

            function confirmDeleteAcc(user) {
                var result = confirm("Bạn có chắc muốn xóa tài khoản " + user + "?");
                return result;
            }

            function confirmDeleteOrder(orderId) {
                var result = confirm("Bạn có chắc muốn xóa tài khoản " + orderId + "?");
                return result;
            }


        </script>







    </body>

</html>