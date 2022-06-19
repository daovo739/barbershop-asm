<%-- 
    Document   : register
    Created on : Jun 14, 2022, 10:22:14 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link href="./static/css/login-register.css" rel="stylesheet" type="text/css"/>
        <title>Register User</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <jsp:include page="layout/navbarNotFixed.jsp"/>
        <main style="height: calc(100vh - 126px - 125px)">
            <section class="form-login">
                <div class="logo-login "><span class="material-symbols-outlined">
                        app_registration
                    </span></div>
                <h2 class="text-white">Register</h2>
                <form action="RegisterServlet" method="POST" class="box box-register">
                    <input type="text" name="name" class="name"
                           id="name" placeholder="Enter your name" />

                    <input type="email" name="email" id="email"
                           placeholder="Enter email"/>

                    <input type="text" name="userName" id="userName"
                           placeholder="Enter user name"/>

                    <input type="password" name="password" id="password"
                           placeholder="Enter password" />

                    <input type="password" name="confirmPassword"
                           id="confirmPassword" placeholder="Enter password again" />

                    <div class="register-login">
                        <button type="submit" name="register">Register</button> 
                    </div>
                </form>
                <c:if test="${not empty msg}">
                    <h3 style="color: red">${msg}</h3>
                </c:if>
                </section>
        </main>
        <jsp:include page="layout/footer.jsp"/>
    </body>
</html>
