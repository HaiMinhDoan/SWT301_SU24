<%-- 
    Document   : adminInfo
    Created on : Mar 2, 2024, 9:38:22 PM
    Author     : tranm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Information</title>
        <link rel="stylesheet" href="/store/css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>

        <style>
            #admin-info{
                margin-top: 100px;
            }

            .form {
                background-color: #fff;
                display: block;
                padding: 1rem;
                max-width: 350px;
                border-radius: 0.5rem;
                box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
            }

            .form-title {
                font-size: 1.25rem;
                line-height: 1.75rem;
                font-weight: 600;
                text-align: center;
                color: #000;
            }

            .input-container {
                position: relative;
            }

            .input-container input, .form button {
                outline: none;
                border: 1px solid #e5e7eb;
                margin: 8px 0;
            }

            .input-container input {
                background-color: #fff;
                padding: 1rem;
                padding-right: 3rem;
                font-size: 0.875rem;
                line-height: 1.25rem;
                width: 300px;
                border-radius: 0.5rem;
                box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
            }

            .submit {
                display: block;
                padding-top: 0.75rem;
                padding-bottom: 0.75rem;
                padding-left: 1.25rem;
                padding-right: 1.25rem;
                background-color: #4F46E5;
                color: #ffffff;
                font-size: 0.875rem;
                line-height: 1.25rem;
                font-weight: 500;
                width: 100%;
                border-radius: 0.5rem;
                text-transform: uppercase;
            }

            .signup-link {
                color: #6B7280;
                font-size: 0.875rem;
                line-height: 1.25rem;
                text-align: center;
            }

            .signup-link a {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div>
            <jsp:include page="header.jsp" />
        </div>

        <div id="admin-info">
            <c:set var="admin" value="${requestScope.admin}"/>
            <form class="form" method="post" action="changePassAdmin?user=${admin.userName}">
                <p class="form-title">Đổi mật khẩu</p>
                <div class="input-container">
                    <span>
                        Tài khoản
                    </span>
                    <input type="text" readonly="" placeholder="Tài khoản" value="${admin.userName}">

                </div>
                <div class="input-container">
                    <span>
                        Mật khẩu
                    </span>
                    <input type="password" readonly="" placeholder="Mật khẩu" value="${admin.userName}">
                </div>
                <div class="input-container">
                    <input type="password"  class="new-pass" placeholder="Mật khẩu mới " name="newPass">
                </div>
                <div class="input-container">
                    <input type="password"  class="new-pass-confirm" placeholder="Xác nhận mật khẩu">
                </div>
                <button type="submit" class="submit">
                    Thay đổi
                </button>

            </form>

        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Get form element
                var form = document.querySelector('.form');

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
