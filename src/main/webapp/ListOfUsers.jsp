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
      <p>Message: 
        it is  list of users. you can see profile of each user!
      </p>
      <table border=1 cellpadding=5>
        <tr>
				
		<td>
		    First name / Last name

		</td>

        </tr>
        <c:forEach var="person" items="${users}">
          <tr>
          
            <td>
              
			  <c:url var="showProfile"   value ="Profile.jsp" >
				
				<c:param  name ="personId"  value="${personId}" />
				<c:param  name ="mail"  value="${person.mail}" />
				<c:param  name ="lastName"  value="${person.profile.lastName}" />
				<c:param  name ="firstName"  value="${person.profile.firstName}" />
				<c:param  name ="rate"  value="${person.profile.rate}" />
				<c:param  name ="joinDate"  value="${person.profile.joinDate}" />
				<c:param  name ="age"  value="${person.profile.age}" />
				<c:param  name ="picName"  value="${person.profile.picName}" />
				
				
			  </c:url>
			  <a href="${showProfile}"><c:out value= "${person.profile.firstName }"/>  <c:out value= "${person.profile.lastName}"/></a>
			  
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
