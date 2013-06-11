<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256"%>

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
      /*$(function() {
        
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
      });*/
    </script>
</head>

<body>
  <div id="pID" style="display: none;"><% String personId = request.getUserPrincipal().getName(); %></div>
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
            <form id="signin" class="navbar-form pull-right" action="Logout" method="post">
              <input type="submit" id="signout-btn" class="btn" value="Sign out"/>
            </form>
            <!--<form class="navbar-form pull-right">
              <input id="email" class="span2" type="text" placeholder="Email">
              <input id="password" class="span2" type="password" placeholder="Password">
              <button id="signin-btn" type="submit" class="btn">Sign in</button>
            </form>-->
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="container">
    <div id="auction" class="hero-unit">
      <p>Message: 
        <%= request.getAttribute("message") %>
      </p><br> 
      List of persons that offered:
      <table border=1 cellpadding=5>
        <tr>
				
		<td>
		    Person Name

		</td>
	
		<td>
	
		    Bid
		 
		 </td>
		 <td>
		
			select !

		</td>
        </tr>
        <c:forEach var="off" items="${auction.offers}">
          <tr>
          
            <td>
              <c:out value= "${off.person.name}"/>
            </td>
            <td>
               <c:out value= "${off.price}"/>
            
            </td>
            <td>
              <form action="CloseAuction.action" method="GET">
                <input type="hidden" name="winnerId" value="${off.person.id}"/>
                <input type="hidden" name="auctionId" value="${auction.id}"/>
                <input type="submit" value="select as winner">
              </form>
              </td>
            
          </tr>
        </c:forEach>
      </table>
    </div>


    <footer>
      <p>By: Payam, Mehran, Leila, Aryaz</p>
    </footer>

  </div>
 
</body>
</html>
