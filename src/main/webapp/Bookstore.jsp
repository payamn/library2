<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="domain.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bookstore</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="A place for buying and selling used books">
    <meta name="author" content="">

    <link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/bookStoreStyle.css" rel="stylesheet" type="text/css"/>
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

        $("input#signin-btn").click(function(){
          var patt=/@/gi;
          if ($("input#email").val() == null || $("input#email").val() == ""){
            alert("Email cannot be empty");
          }
          else if(patt.test($("input#email").val()) == false) {
            alert("Email must have the format 'someone@somewhere.sth'");
          }
          else{
            $("form#signin").submit();
          }
        });

        $("input#searchAuction").click(function(){
          if(pID >= 0){
            $("form#searchAuction").submit();
          }
          else{
            alert("You should Login first");
          }
        });

        $("input#createAuction").click(function(){
          if(pID >= 0){
            $("form#createAuction").submit();
          }
          else{
            alert("You should Login first");
          }
        });

        $("input#joinAuction").click(function(){
          if(pID >= 0){
            $("form#joinAuction").submit();
          }
          else{
            alert("You should Login first");
          }
        });

        $("input#selectWinner").click(function(){
          if(pID >= 0){
            $("form#closeAuction").submit();
          }
          else{
            alert("You should Login first");
          }
        });
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
              <form id="signin" class="navbar-form pull-right" action="Login.action" method="post">
                <input id="email" name="email" class="span2" type="text" placeholder="Email">
                <input id="password" name="password" class="span2" type="password" placeholder="Password">
                <input type="button" id="signin-btn" class="btn" value="Sign in"/>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
	<!--style="width:35%;float:left" -->
      <div   class="hero-unit" >
        <p>
        	<% if (request.getAttribute("message") != null && request.getAttribute("message") != "") {%>
			<%= "Message: " + request.getAttribute("message")%>
			<% } %>
        </p><br>
        <h1>Hello <%= ((request.getAttribute("name") == null)?"Newbie":request.getAttribute("name")) %>!</h1>
        <p>This is the place for all the book lovers.</p>
 
		
      </div>  

	  
      <div class="row">
        <div style="width:20%" class="span4" >
          <h2>Create Auction</h2>
          <p>If you have a book that you want to sell, here is the place for you. You can create an auction by filling the form.</p>
          <p>
          	<form id="createAuction" action="CreateAuction.jsp">
          		<input type="hidden" name="personId" value="<%= request.getAttribute("personId") %>"/>
          		<input type="button" id="createAuction" class="btn btn-warning" value="Create an Auction &raquo;"/>
          	</form>
          </p>
        </div>
        <div style="width:20%" class="span4">
          <h2>Join Auction</h2>
          <p>Need a book? Here you can search for a book or see all the available books. Then you can join the auction and bid for it</p>
          <p>
            <form id="joinAuction" action="joinShowAuctions.action">
              <input type="hidden" name="personId" value="<%= request.getAttribute("personId") %>"/>
              <input type="button" id="joinAuction" class="btn btn-danger" value="Join an Auction &raquo;"/>
            </form>
          </p>
        </div>
        <div style="width:20%" class="span4">
          <h2>Select Winner</h2>
          <p>If you have already created an auction, you can select the winner among all the people who participated in it.</p>
          <p>
            <form id="closeAuction" action="closeShowAuctions.action">
              <input type="hidden" name="personId" value="<%= request.getAttribute("personId") %>"/>
              <input type="button" id="selectWinner" class="btn btn-success" value="Select the Winner &raquo;"/>
            </form>
          </p>
        </div>
		<div style="width:20%" class="span4">
          <h2>Search Book</h2>
          <p>Need a book ??  If you want to find auctions quickly  ,you can use our quick searches ! please try it !! we provide 3 way to search!  </p>
          <p>
		    <form id="searchAuction" action="SearchAuction.jsp">
				<input type="hidden" name="personId" value="<%= request.getAttribute("personId") %>"/>
				<input type="button" id="searchAuction" class="btn btn-primary btn-Large" value="Search the book &raquo;"/>
			</form>
          
          </p>
        </div>
		<div style="width:20%" class="span4">
          <h2>see profiles</h2>
          <p>If you want to know users , you can see profiles of each user ! please try it !! </p>
          <p>
		    <form id="see profile" action="ShowUserList.action">
				<input type="hidden" name="personId" value="<%= request.getAttribute("personId") %>"/>
				<input type="submit" id="see profile" class="btn btn-primary btn-Large" value="See profiles &raquo;"/>
			</form>
          
          </p>
        </div>
      </div>
	  <div    class="hero-unit" >
	
				recently auctions:
		<table border=1 cellpadding=5>
			<tr>
			  
			  <td>name of book </td>
			  <td>name of seller</td>
			   <td>name of writer </td>
			  <td>publish year </td>
			  <td>Quality </td>
			  <td>end date</td>
			  
			  <td>minimom price</td>
			  <td>maximom offer</td>
			  <td> close/join </td>
			</tr>
			<c:forEach var="auc" items="${auctions}">
			   <tr>
			     
				
					 <td><c:out value="${auc.book.name}"/></td>
				 <td><c:out value="${auc.person.name}"/></td>
				 
				 <td><c:out value="${auc.book.writerName}"/></td>
				 <td><c:out value="${auc.book.publishYear}"/></td>
				 
				 <td><c:out value="${auc.book.qualityStr}"/></td>
				 <td><c:out value="${auc.endDate}"/></td>
				 <td><c:out value="${auc.minPrice}"/></td>
				 <td><c:out value="${auc.maxOfferPrice}"/></td>
				
				
				 <c:choose>
					<c:when test="${auc.person.id== personId}">
						<form action="ShowOffers.action" method="POST">
							<input type="hidden" name="auctionId" value="${auc.id}">
							<input type="hidden" name="personId" value="${personId}">
							<td><input type="submit" value="close"></td>
						</form>
					</c:when>
					<c:otherwise>
							<form action="JoinAuction.action"  method="POST">
								<input type="hidden" name="auctionId" value="${auc.id}">
								<input type="hidden" name="personId" value="${personId}">
								<input type="hidden" name="sourceOfReq" value="Bookstore.jsp">
								<td>
								<input type="text" name="price"/>
								<input type="submit" value="join"></td>
							</form>
					</c:otherwise>
					
				</c:choose>	 
				 
				
				 
					 
				 
				</tr>
			</c:forEach> 
		
		</table>
		
	  </div>
      <hr>

      <footer>
        <p>By: Payam, Mehran, Leila, Aryaz</p>
      </footer>

    </div>
  </body>
</html>
