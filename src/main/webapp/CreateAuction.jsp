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
  <meta name="description" content="create auction">
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
  <div id="pID" style="display: none;"><%= request.getParameter("personId") %></div>
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
    <p>Message: 
      <%= request.getAttribute("message") %>
    </p><br>
    <div id="auction" class="hero-unit">
      <form action="CreateAuction.action">
      	<input type="hidden" name="personId" value="<%= request.getParameter("personId") %>"/>
        <p>
          Book Name: <input name="bookName" id="bookname" class="span2" type="text" placeholder="Book Name"/>
        </p>
        <p>
          Publish Year: <input name="publishYear" id="year" class="span2" type="text" placeholder="Publish Year"/>
        </p>
        <p>
          Writer's First Name: <input name="writerName" id="wfname" class="span2" type="text" placeholder="Writer's First Name"/>
        </p>
        <p>
          Writer's Last Name: <input name="writerSurname" id="wlname" class="span2" type="text" placeholder="Writer's Last Name"/>
        </p>
        <p>
          Minimum Price: <input name="leastPrice" id="price" class="span2" type="text" placeholder="Min Price"/>
        </p>
        <p>
          Start Date: <input name="startYear" id="sdatey" class="span2" type="text" placeholder="year"/>
          <input name="startMonth" id="sdatem" class="span2" type="text" placeholder="month"/>
          <input name="startDay" id="sdated" class="span2" type="text" placeholder="day"/>
        </p>
        <p>
          End Date: <input name="endYear" id="edatey" class="span2" type="text" placeholder="year"/>
          <input name="endMonth" id="edatem" class="span2" type="text" placeholder="month"/>
          <input name="endDay" id="edated" class="span2" type="text" placeholder="day"/>
        </p>
        <p>
          Quality: <input name="quality" type="radio" value="excellent"/>Excellent
          <input name="quality" type="radio" value="good"/>Good
          <input name="quality" type="radio" value="normal"/>Normal
          <input name="quality" type="radio" value="bad"/>Bad
          <input name="quality" type="radio" value="awful"/>Awful
        </p>
        <button id="add-btn" type="submit" class="btn">Add</button>
      </form>
    </div>


    <footer>
      <p>By: Payam, Mehran, Leila, Aryaz</p>
    </footer>

  </div>
</body>
</html>
