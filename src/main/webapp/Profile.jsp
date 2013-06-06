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
  <link href="css/style.css" rel="stylesheet" type="text/css"/>
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
  <div id="pID" style="display: none;"><c:out value="${personId}"/></div>
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

    <div class="container">
    <div id="users" class="hero-unit">
      <p><b>
	                   Profile of 	<c:out value= "${profile.firstName }"/>  <c:out value= "${profile.lastName}"/>
      </b></p>
	  <p>
	  Picture :   <div id=<c:out value= "${profile.mail }"/>></div>
	  </p> 
      <p>First name :  <c:out value= "${profile.firstName }"/></p>
	  <p>Last Name :  <c:out value= "${profile.lastName}"/></p>
	  <p>Email address : <c:out value= "${profile.mail}"/></p>
	  <p>Age : <c:out value= "${profile.age}"/></p>
	  <p>Rating : <c:out value= "${profile.rate}"/></p>
	  <p>Join Date : <c:out value= "${profile.joinDate}"/></p>
	  
    </div>


    <footer>
      <p>By: Payam, Mehran, Leila, Aryaz</p>
    </footer>

  </div>
 
</body>
</html>
