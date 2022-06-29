<%-- 
    Document   : bookingAdmin
    Created on : Jun 28, 2022, 2:28:08 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking</title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="wrapper d-flex align-items-stretch">
            <jsp:include page="/layout/admin/navbarAdmin.jsp"/>
            <div class="w-100" style="padding: 12px; margin-left: 280px;" >
                <h1 class="section-title bg-white text-dark"style="padding: 0; margin-bottom: 0">Booking</h1>
                <section>
                    <c:if test="${not empty msgBooking}">
                        <h2>${msgBooking}</h2>
                    </c:if>
                    <c:if test="${empty msgBooking}">
                        <div class="text-white d-flex">
                            <a href="bookingAdmin" class="text-decoration-none">All</a>
                            <a href="bookingAdmin?filter=uncompleted" class="text-decoration-none">Uncompleted</a>
                            <a href="bookingAdmin?filter=completed" class="text-decoration-none">Completed</a>
                        </div>
                        <c:forEach var="entry" items="${bookings}">
                            <div class="mt-3">
                                <h5>${entry.key}</h5>
                                <hr>
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">PhoneNumber</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Service</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Note</th>
                                        </tr>
                                    </thead>
                                    <c:forEach var="booking" items="${entry.value}">


                                        <tbody>
                                            <tr>
                                                <th scopt="row">${booking.getBookingId()}</td>
                                                <td>${booking.getBookingPhone()} </td>
                                                <td>${booking.getBookingName()} </td>
                                                <td>${booking.getBookingService()} </td>
                                                <td>${booking.getBookingDateAllowDay()} </td>
                                                <td>${booking.getBookingNote()} </td>
                                            </tr>
                                        </tbody>

                                    </c:forEach>
                                </table>
                            </div> 
                        </c:forEach>



                    </c:if>
                </section>
            </div>
        </div>
    </body>
</html>
