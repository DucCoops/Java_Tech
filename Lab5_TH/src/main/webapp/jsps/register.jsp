<%--
  Created by IntelliJ IDEA.
  User: Trong
  Date: 28-Feb-23
  Time: 10:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%
  String msg_error = (String) session.getAttribute("msg_error");
  session.removeAttribute("msg_error");
%>

<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css    ">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style>
    p.item {
      padding: 16px;
      text-align: center;
      border-radius: 8px;
      color: white;
      background-color: green;
    }
  </style>

  <script !src="">
    function validate(bnt) {
      let email = $('#email').val().trim()
      let pwd = $('#password').val()
      var pattern = /^\b[A-Za-z0-9._%-]+@[A-Za-z0-9]+.([A-Za-z]{2,4})+([.A-Za-z]{2,4})?$/
      if (email == "") {
        $('#msg_err').show()
        $('#msg_err').css('color', 'red').text("Please enter your email")
        bnt.preventDefault();
      } else
      if (!pattern.test(email)) {
        $('#msg_err').show()
        $('#msg_err').css('color', 'red').text("Your email is not correct")
        bnt.preventDefault();
      }
      else if (pwd == "") {
        $('#msg_err').show()
        $('#msg_err').css('color', 'red').text("Please enter your password")
        bnt.preventDefault();
      }
      else if ($('#password').val().length < 6) {
        $('#msg_err').show()
        $('#msg_err').css('color', 'red').text("Your password must contain at least 6 characters")
        bnt.preventDefault();
      }
      else if ($('#password').val != $("#password-confirm").val) {
        $('#msg_err').show()
        $('#msg_err').css('color', 'red').text("Your confirm password not match")
        bnt.preventDefault();
      }
      else {
        $('#msg_err').hide()
      }

    }
  </script>
</head>

<body class="bg-secondary">

<h3 class="text-center my-5 text-light">Account Registration</h3>

<!-- buộc toàn bộ dòng và cột phải bỏ trong class container -->
<div class="container">
  <div class="row justify-content-center">
    <div class="col-md-10 col-lg-8 col-xl-5">
      <div class="border p-3 rounded bg-light">
        <form method="post" action="./register">
          <div class="form-group">
            <label for="name">Fullname</label>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fas fa-usertable"></i>
                                    </span>
              </div>
              <input id="name" type="text" name="name" class="form-control" placeholder="Your Name">
            </div>

            <div class="form-group">
              <label for="email">Email</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                                        <span class="input-group-text">
                                            <i class="fas fa-usertable"></i>
                                        </span>
                </div>
                <input id="email" name="email" type="text" class="form-control" placeholder="Email">
              </div>

            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                                        <span class="input-group-text">
                                            <i class="fas fa-lock"></i>
                                        </span>
                </div>
                <input id="password" name="pwd" type="password" class="form-control"
                       placeholder="Password">
              </div>
            </div>
            <div class="form-group">
              <label for="password-confirm">Confirm Password</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                                        <span class="input-group-text">
                                            <i class="fas fa-lock"></i>
                                        </span>
                </div>
                <input id="password-confirm" data-rule-equalTo="#password" name="confirm_pwd"
                       type="password" class="form-control" placeholder="Password">
              </div>
            </div>
            <div class="form-group">
              <div class="alert alert-danger alert-dismissible fade show" id="msg_err"
                   style="display: none;">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                Password must have at least 6 characters!
              </div>
            </div>
            <% if (msg_error!=null){ %>
            <div class="alert alert-danger">
              <%=msg_error%>
            </div>
            <%} %>
            <div class="form-group">
              <button class="btn btn-success px-5" type="submit"
                      onclick="validate(event)">Register</button>
            </div>
            <div class="form-group">
              <p>Already have an account? <a href="./login">Login now!</a></p>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>


</body>

</html>