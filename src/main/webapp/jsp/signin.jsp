<%--
  Created by IntelliJ IDEA.
  User: dynamicniu
  Date: 2017/5/24
  Time: 下午8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <%--<link href="../js/ui/bootstrap/css/bootstrap.css" rel="stylesheet">--%>
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/signin.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <form class="form-signin" action="/loginAction/signin" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>

        <label for="loginName" class="sr-only">LoginName</label>
        <input id="loginName" name="loginName" class="form-control" placeholder="LoginName" required autofocus>
        <label>$('')</label>

        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>

        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button id="inputSubmit" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<%--<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>--%>
<script type="text/javascript">
function validateUserInfo() {

    var loginName = $('#inputLoginName').val();
    var password = $('#inputPassword').val();

    alert(contextPath);

    $.ajax({
        type: "POST",
        url: contextPath + '/loginAction/signin',
        data: {loginName: loginName, password: password},
        //data: $("form").serialize(),
        //dataType: 'json',
        async: false,
        error: function (request) {

        },
        success: function (result) {
            if (result.success) {
                alert("Error");
            }
        }
    });
}
</script>
</body>
</html>
