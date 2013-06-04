<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256"%>
<%@page import="domain.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Bookstore</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="select winner">
  <meta name="author" content="">

  <link href="css/bootstrap.css" rel="stylesheet">
  <style type="text/css">
  body {
    padding-top: 60px;
    padding-bottom: 40px;
  }
  </style>
  <link href="css/bootstrap-responsive.css" rel="stylesheet">
  <link rel="shortcut icon" href="img/icon-small.jpg">
  <script src="js/jquery.min.js"></script>
  <script>
  $(function() {
    
    var pID = $("div#pID").html();
    if(pID == null || pID == "")
      pID=-1;
    else
      pID=parseInt(pID);
    if(pID >= 0){
      $("div#auth").html('<div class="navbar pull-right">Welcome </div>');
    }
    else{
    }
  });
  </script>
</head>
<body>
  <div id="pID" style="display: none;"><%= request.getAttribute("personId") %></div>
  <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="brand">Bookstore</a>
        <div class="nav-collapse collapse">
          <ul class="nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">Auction</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auction <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Create</a></li>
                <li><a href="#">Join</a></li>
                <li><a href="#">End</a></li>
                <li class="divider"></li>
                <li class="nav-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
          <div id="auth">
            <form class="navbar-form pull-right">
              <input id="email" class="span2" type="text" placeholder="Email">
              <input id="password" class="span2" type="password" placeholder="Password">
              <button id="signin-btn" type="submit" class="btn">Sign in</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <h3>List of Auctions</h3><p>
  <div class="container">
   <p>Message:
    <%= request.getAttribute("message") %>
   </p><br>
   <div class="hero-unit">
     <table border=1 cellpadding=5>
       <tr>
        
        <td>name of book </td>
        <td>name of seller</td>
        <td>surname of seller</td>	
        <td>name of writer </td>
        <td>publish year </td>
        <td>Quality </td>
        <td>end date</td>
        
        <td>minimom price</td>
        <td>maximom offer</td>
        <td>Close</td>
        
      </tr>

      <c:forEach var="auc" items="${auctions}">

      <tr>
       <td><c:out value="${auc.book.name}"/></td>
       <td><c:out value="${auc.person.profile.name}"/></td>
       <td><c:out value="${auc.person.profile.lastName}"/></td>
       <td><c:out value="${auc.book.writerName}"/></td>
       <td><c:out value="${auc.book.publishYear}"/></td>

       <td><c:out value="${auc.book.qualityStr}"/></td>
       <td><c:out value="${auc.endDate}"/></td>
       <td><c:out value="${auc.minPrice}"/></td>
       <td><c:out value="${auc.maxOfferPrice}"/></td>
       
       <form action="ShowOffers.action" method="POST">
        <input type="hidden" name="auctionId" value="${auc.id}">
        <input type="hidden" name="personId" value="<%= request.getParameter("personId") %>">
        <td><input type="submit" value="close"></td>
      </form>
    </tr>
  </c:forEach>
</table>
</div>


<footer>
  <p>By: Payam, Mehran, Leila, Aryaz</p>
</footer>

</div>

</body>