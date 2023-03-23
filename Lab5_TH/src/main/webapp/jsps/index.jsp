<%@ page import="com.nguyenduc.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    Product editProduct = (Product) request.getAttribute("product");
    String msg_success = (String) session.getAttribute("msg_success");
    String msg_error = (String) session.getAttribute("msg_error");
    session.removeAttribute("msg_success");
    session.removeAttribute("msg_error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script !src="">
        function confirm() {
            let uid = $('#delete_confirm').attr('uid');
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("GET", '../Lab5_TH_war/?action=delete&id=' + uid, false);
            xmlHttp.send(null);
            location.reload();
        }

        function confirmRemoval(btn) {
            let tds = $(btn).closest('tr').find('td')
            document.getElementById("product").innerHTML = tds[1].innerText;
            $('#delete_confirm').attr('uid', tds[0].innerHTML)
            $('#confirm-removal-modal').modal({ show: true });
        }
    </script>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            Xin chào <span class="text-danger"><%= session.getAttribute("username")%></span> | <a href="./logout">Logout</a>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
                <% if (editProduct !=null) { %>
                <form class="mt-3" method="post" action="./">
                    <input type="hidden" name="_method" value="PUT">
                    <input type="hidden" name="id" value="<%=editProduct.getID()%>">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" value="<%=editProduct.getName()%>" type="text" placeholder="Nhập tên sản phẩm" id="product-name" name="name">
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" value="<%=editProduct.getPrice()%>" type="number" placeholder="Nhập giá bán" id="price" name="price">
                </div>
                <% } else {%>
                    <form class="mt-3" method="post" action="./">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" value="" type="text" placeholder="Nhập tên sản phẩm" id="product-name" name="name">
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control"  type="number" placeholder="Nhập giá bán" id="price" name="price">
                </div>
                <% } %>
                <div class="form-group">
                    <button class="btn btn-success mr-2">Thêm sản phẩm</button>
                </div>
                <% if (msg_success!=null){ %>
                <div class="alert alert-success">
                    <%=msg_success%>
                </div>
                <%} %>
                <% if (msg_error!=null){ %>
                <div class="alert alert-danger">
                    <%=msg_error%>
                </div>
                <%} %>

            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <% for (Product product : products) { %>
                <tr>
                    <td><%= product.getID() %></td>
                    <td><a href="#"><%= product.getName() %></a></td>
                    <td><%= product.getPrice() %></td>
                    <td>
                        <a href="?action=edit&id=<%= product.getID() %>">Chỉnh sửa</a> |
                        <a href="#" onclick="confirmRemoval(this)">Xóa</a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <!-- Confirm Removal Modal -->
        <div class="modal fade" id="confirm-removal-modal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Xóa sản phẩm</h4>
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc chắn muốn xóa <strong id="product"></strong>?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="delete_confirm" onclick="confirm()" class="btn btn-danger" data-dismiss="modal">Xóa</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Không</button>
                    </div>
                </div>

            </div>
        </div><!-- Confirm Removel modal -->

    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>
