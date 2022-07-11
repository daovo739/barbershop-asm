<%-- 
    Document   : errorPage
    Created on : Jul 12, 2022, 1:31:44 AM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://www.paypal.com/sdk/js?client-id=AdznrYMJFJhdO3BsR0iQBUHNVyl5hZlQJqJ5UHA_xW9f9rlj2dEt4S9AFiKqvA_nZuAeJ5jdHMikrO25&disable-funding=credit,card"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp"/>
        <main class="d-flex justify-content-center align-items-center flex-column text-white " style="min-height:  75vh">
            <span class="material-symbols-outlined" style="color: red; font-size: 200px">
                report
            </span>
            <h1>Page not found!</h1>
            <a href="GetProductsHomeServlet" class="text-decoration-none btn btn-danger mt-3">Back to HOME PAGE!</a>
        </main>

        <jsp:include page="layout/footer.jsp"/>
    </body>
</html>
