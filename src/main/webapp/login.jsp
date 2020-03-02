<%--
  Created by IntelliJ IDEA.
  User: Linay
  Date: 01.03.2020
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Регистрация</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    >

    <style type="text/css">
        .signup-row {
            display: inline-flex;
            flex-direction: row;
            width: 100%;
        }

        .signup-row > span {
            flex: 1;
        }

        .signup-row > input {
            flex: 2;
        }
    </style>
</head>
<body>
<div class="jumbotron text-center">
    <h1>Вход</h1>
    <p>Войдите для оформления заказа</p>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <form method="POST" action="${pageContext.request.contextPath}/login">
                <div class="signup-row">
                    <span>Email:</span>
                    <input type="text" name="email" required placeholder="hello@world.com"/>
                </div>
                <div class="signup-row">
                    <span>Пароль:</span>
                    <input type="password" name="password" required placeholder="*****"/>
                </div>
                <%if (("1").equals(request.getParameter("error"))) { %>
                <div class="text-danger"> Неправильный логин или пароль </div>
                <%}%>
                <div style="text-align: center; margin-top: 20px;">
                    <input class="btn btn-secondary" type="submit" value="ВОЙТИ"/>
                </div>
            </form>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>

</body>
</html>
