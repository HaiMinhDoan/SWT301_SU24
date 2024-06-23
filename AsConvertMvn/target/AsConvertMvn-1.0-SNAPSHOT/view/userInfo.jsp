<%-- 
    Document   : userInfo
    Created on : Feb 24, 2024, 12:05:33 PM
    Author     : tranm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/store/css/main.css">

        <title>My account</title>

        <style>
            #register{
                margin-top: 150px;
            }

            .form-box{
                display: none;
            }

            .form-box-active{
                display: block;
            }
        </style>
    </head>
    <body>

        <div>     
            <jsp:include page="header.jsp" />        
        </div>

        <div id="user-info">
            <div>
                <c:set  var="customer" value="${requestScope.userInfo}"/>

                <form class="form" action="infoUser?id=${customer.customerId}" method="post">
                    <p class="title">Thông tin tài khoản </p>   

                    <%
                            String message = (String)request.getAttribute("message_update");
                            if(message != null) {
                    %>  

                    <p style="color: green"><%= message%></p>

                    <%       
                        }
                    %>                    
                    <div class="flex">

                        <label>
                            <span>Họ</span>
                            <input name="firstName" required="" placeholder="Họ" value="${customer.firstName}" type="text" class="input">
                        </label>

                        <label>
                            <span>Tên</span>
                            <input name="lastName" required="" placeholder="Tên" value="${customer.lastName}" type="text" class="input">
                        </label>
                    </div>     
                    <label>
                        <span>Giới tính</span>

                        <input name="gender" required="" placeholder="Giới tính" value="${customer.gender}" type="text" class="input">
                    </label> 
                    <label>
                        <span>Email</span>
                        <input name="email" required="Email" placeholder="" value="${customer.email}" type="email" class="input">
                    </label> 
                    <label>
                        <span>Số điện thoại</span>

                        <input name="phone" required="" placeholder="Số điện thoại" value="${customer.phone}" type="number" class="input">
                    </label> 
                    <label>
                        <span>Địa chỉ</span>
                        <input name="address" required="" placeholder="Địa chỉ" value="${customer.address}" type="text" class="input">
                    </label>       
                    <label>

                        <span>Tên đăng nhập</span>
                        <c:forEach var="i" items="${requestScope.listAccount}">
                            <c:if test="${i.customerId == customer.customerId}">
                                <input name="user" readonly="" required="" placeholder="Tên đăng nhập" value="${i.userName}" type="text" class="input">
                            </c:if>

                        </c:forEach>

                    </label>    

                    <div>
                        <a class="change-password" href="#">Đổi mật khẩu</a>

                    </div>
                    <button class="submit">Thay Đổi</button>        
                </form>
            </div>
            <div class="form-box ">
                <c:forEach var="i" items="${requestScope.listAccount}">
                    <c:if test="${i.customerId == customer.customerId}">
                        <form class="form-change-pass" method="post" action="changePassword?user=${i.userName}">
                            <span class="title-change-pass">Đổi mật khẩu</span>         
                            <div class="form-container-change-pass">           
                                <input type="password" class="input-change-pass" placeholder="Mật khẩu cũ" value="${i.password}">
                                <input type="password" name="newPass" class="input-change-pass new-pass" placeholder="Mật khẩu mới">
                                <input type="password" class="input-change-pass new-pass-confirm" placeholder="Xác lại nhận khẩu">
                            </div>
                            <button>Thay đổi</button>
                        </form>
                    </c:if>
                </c:forEach>
            </div>

        </div>


        <div>     
            <jsp:include page="footer.jsp" />        
        </div>


        <script>

            document.querySelector('.change-password').addEventListener('click', function () {
                document.querySelector('.form-box').classList.add('form-box-active');
            });


            document.addEventListener("DOMContentLoaded", function () {
                // Get form element
                var form = document.querySelector('.form-change-pass');

                // Add event listener to form submit
                form.addEventListener("submit", function (event) {
                    // Get the values of new password and confirm password fields
                    var newPassword = document.querySelector('.new-pass').value;
                    var confirmPassword = document.querySelector('.new-pass-confirm').value;

                    // Check if new password and confirm password match
                    if (newPassword !== confirmPassword) {
                        // Prevent form submission
                        event.preventDefault();
                        // Display an error message or handle it as per your UI requirement
                        alert("Mật khẩu mới và xác nhận mật khẩu không trùng khớp.");
                    }
                });
            });


        </script>
    </body>
</html>
