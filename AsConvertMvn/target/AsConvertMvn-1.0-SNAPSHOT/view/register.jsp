<%-- 
    Document   : register
    Created on : Feb 23, 2024, 2:33:27 PM
    Author     : tranm
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/store/css/main.css">
        <title>Register</title>
        <style>
            .invalid {
                background-color: #ffd6d6; /* Màu nền khi thông tin không hợp lệ */
            }

            #register{
                margin-top: 100px;
            }
        </style>

    </head>
    <body>
        <div> <%@include file="header.jsp" %></div>

        <div id="register">
            <form class="form" name="form" action="register" method="post">
                <p class="title">Đăng ký</p>
                <p class="message">Đăng ký ngay bây giờ và có toàn quyền truy cập vào ứng dụng của chúng tôi. </p>

                <% 
                  if(request.getAttribute("message") != null) { 
                    String message = (String) request.getAttribute("message");
                %>
                <p style="color: red" class="message"> <%= message %></p>

                <%                     
                   }
                %>

                <div class="flex">
                    <label>
                        <input name="firstName" required="" placeholder="Họ" type="text" class="input" value="${requestScope.firstName}">

                    </label>

                    <label>
                        <input name="lastName" required="" placeholder="Tên" type="text" class="input" value="${requestScope.lastName}">
                    </label>
                </div>     
                <label class="gender-label" >
                    <select required="" class="gender-select" name="gender">
                        <option value="" disabled selected>Lựa chọn giới tính</option>
                        <option ${requestScope.gender eq 'Nam' ? 'selected' : ''}   value="Nam">Nam</option>
                        <option ${requestScope.gender eq 'Nữ' ? 'selected' : ''} value="Nữ">Nữ</option>
                        <option ${requestScope.gender eq 'Khác' ? 'selected' : ''} value="Khác">Khác</option>
                    </select>
                </label>
                <label>
                    <input name="email" required="" placeholder="Email" type="email" class="input" value="${requestScope.email}">
                </label> 
                <label>
                    <input name="phone" required="" placeholder="Số điện thoại" type="tel" pattern="^0\d{9,10}$" class="input" value="${requestScope.phone}">

                </label> 
                <label>
                    <input name="address" required="" placeholder="Địa chỉ" type="text" class="input" value="${requestScope.address}">

                </label> 
                <label>
                    <input name="user" required="" placeholder="Tên đăng nhập" type="text" class="input" value="${requestScope.user}">

                </label> 
                <label>
                    <input name="password" required="" placeholder="Mật khẩu" type="password" class="input" value="${requestScope.password}">
                </label>
                <label>
                    <input name="confirm" required="" placeholder="Nhập lại mật khẩu" type="password" class="input confirm-pass" value="${requestScope.confirm}">
                    <span id="error_message" class="error-message"></span>
                </label>
                <button class="submit">Đăng ký</button>
                <p class="signin">Bạn có tài khoản rồi ? <a href="login">Đăng nhập</a> </p>
            </form>

        </div>

        <div> <%@include file="footer.jsp" %></div>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var password = document.getElementsByName('password')[0];
                var confirm = document.getElementsByName('confirm')[0];
                var error_message = document.getElementById('error_message');
                var form = document.querySelector('form');
                var submitButton = document.querySelector('.submit');
                var confirmInput = document.querySelector('.confirm-pass');
                confirm.addEventListener('input', function () {
                    if (confirm.value !== password.value) {
                        error_message.textContent = 'Mật khẩu không trùng khớp';
                        confirmInput.classList.add('invalid');
                        submitButton.disabled = true; // Disable nút Đăng ký
                    } else {
                        error_message.textContent = '';
                        confirmInput.classList.remove('invalid');
                        submitButton.disabled = false; // Enable nút Đăng ký
                    }
                });

                form.addEventListener('submit', function (event) {
                    if (confirm.value !== password.value) {
                        error_message.textContent = 'Mật khẩu không trùng khớp';
                        confirmInput.classList.add('invalid');
                        event.preventDefault(); // Ngăn chặn việc submit form
                    }
                });
            });

        </script>


    </body>
</html>
