<%-- 
    Document   : login
    Created on : Jun 3, 2013, 5:33:22 PM
    Author     : aryaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login</title>
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
        <link rel="shortcut icon" href="img/icon-small.jpg">
        <script src="js/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body style="width: 100%">
        <form name="loginForm" method="post" action="j_security_check" style="margin-left: 40%; margin-right: 30%">
            <table border=0 style="background-color: #dd514c; -moz-border-radius: 145px; border-radius: 145px;">
                <tr style="height: 85px">
                </tr>
                <tr>
                    <td style="width: 40px"></td>
                    <td><input name="j_username" type="text" size="20" placeholder="Username"/></td>
                    <td style="width: 40px"></td>
                </tr>
                <tr style="height: 20px">
                </tr>
                <tr>
                    <td style="width: 40px"></td>
                    <td><input name="j_password" type="password" size="20" placeholder="Password"/></td>
                    <td style="width: 40px"></td>
                </tr>
                <tr style="height: 20px">
                </tr>
                <tr>
                    <td colspan="3" align="center">
                        <input name="Submit" type="submit" class="btn btn-primary" value='Login'/>
                    </td>
                </tr>
                <tr style="height: 55px">
                </tr>
            </table>
        </form>
    </body>
</html>
