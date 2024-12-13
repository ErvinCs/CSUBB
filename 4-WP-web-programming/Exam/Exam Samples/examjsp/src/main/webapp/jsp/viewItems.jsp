<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ubb.exam.model.User" %>
<%@ page import="ubb.exam.controller.UserController"%>
<%@ page import="ubb.exam.controller.ItemController"%>
<%
    User currentUser = (User)(session.getAttribute("currentSessionUser"));
    if (currentUser == null) {
        session.invalidate();
        response.sendRedirect("../index.html");
    }
%>

<html>
<head>
    <title>Items</title>
    <link rel="stylesheet" type="text/css" href="../css/items.css">
    <script src="../lib/jquery-2.0.3.js"></script>
    <script lang="javascript">
        var counter = 0;

        function removeItemRaw(rawId) {
            $("#" + rawId).remove();
        }

        function removeItem(id) {
            $("#itemTable" + id).remove();
        }

        function newItem() {
            var tbl = document.createElement('table');
            var tr;
            var thisId = counter;
            tbl.setAttribute('id', 'itemTable' + thisId);
            tbl.classList.add("new-items");
            counter++;

            tr = tbl.insertRow(0);

            var thname = document.createElement('th');
            thname.innerHTML = "Name";
            tr.appendChild(thname);

            var thprice = document.createElement('th');
            thprice.innerHTML = "Description";
            tr.appendChild(thprice);

            var thcateg = document.createElement('th');
            thcateg.innerHTML = "Value";
            tr.appendChild(thcateg);

            for(var i = 0; i < 1; i++) {
                tr = tbl.insertRow(i+1);
                for (var column = 0; column < 4; column++) {
                    var td = document.createElement('td');
                    td = tr.insertCell(column);
                    var elem;
                    switch (column) {
                        case 0:
                            td.innerHTML = '<input type="text" class="tbl-input" name="name" id="name"/>';
                            break;
                        case 1:
                            td.innerHTML = '<input type="text" class="tbl-input" name="description" id="desc"/>';
                            break;
                        case 2:
                            td.innerHTML = '<input type="text" class="tbl-input" name="value" id="value"/>';
                            break;
                        case 3:
                            td.innerHTML = '<input id="undo-item" class="tbl-input" type="button" value="Remove" onclick="removeItem(' + thisId + ')">';
                    }
                }
            }

            var section = document.getElementById('add-items-div');
            section.appendChild(tbl);
        }

        function loadData(items) {
            console.log(items);
            $("#items-div").html('');
            var size = items.length;
            var tbl = document.createElement('table');
            var tr;
            tbl.setAttribute('id', 'itemTable');
            tbl.classList.add("my-items");

            tr = tbl.insertRow(0);

            var thname = document.createElement('th');
            thname.innerHTML = "Name";
            tr.appendChild(thname);

            var thprice = document.createElement('th');
            thprice.innerHTML = "Description";
            tr.appendChild(thprice);

            var thcateg = document.createElement('th');
            thcateg.innerHTML = "Value";
            tr.appendChild(thcateg);

            for(var i = 0; i < size; i++) {
                tr = tbl.insertRow(i+1);
                //if (items[i].value > 100) {
                //    tr.style.background = "red";
                //}
                for (var column = 0; column < 4; column++) {
                    var td = document.createElement('td');
                    td = tr.insertCell(column);
                    switch (column) {
                        case 0:
                            td.innerHTML = items[i].name;
                            break;
                        case 1:
                            td.innerHTML = items[i].description;
                            break;
                        case 2:
                            td.innerHTML = items[i].value;
                            break;
                        case 3:
                            td.innerHTML = '<a href="http://localhost:8080/exam_jsp_war_exploded/jsp/detailsItemServlet?itemId=' + items[i].id + '">Edit</a>';
                            break;
                    }
                }

            }

            var section = document.getElementById('items-div');
            section.appendChild(tbl);
        }

        $(document).ready(function() {
            $("#show-items-btn").click(function(){
                $.getJSON('viewItemsServlet',
                    {},
                    loadData
                );
            });
            $("#push-items-btn").click(function() {
                $("table.new-items").each(function() {
                    var attributes = [];
                    var colCount = 0;
                    var tbl = $(this);
                    tbl.children('tbody').children('tr:last').children('td').each(function () {
                        switch (colCount) {
                            case 0:
                                attributes[colCount] = $(this).find('.tbl-input').val();
                                break;
                            case 1:
                                attributes[colCount] = $(this).find('.tbl-input').val();
                                break;
                            case 2:
                                attributes[colCount] = $(this).find('.tbl-input').val();
                                break;

                        }
                        colCount++;
                        //console.log(this);
                    });

                    $.post('viewItemsServlet', {
                        name: attributes[0],
                        description: attributes[1],
                        value: attributes[2]
                    });
                    //console.log(tbl.attr('id'));
                    removeItemRaw(tbl.attr('id'));
                });
                $.getJSON('viewItemsServlet',
                    {},
                    loadData
                );
            });
        });


    </script>
</head>
<body>
    <h3>Your Items</h3>
    <h5>Logged in as: <%=currentUser.getUsername()%></h5>

    <input id="show-items-btn" type="button" value="Show Items">
    <div id="items-div">
    </div>
    <br/>

    <div id="add-items-div">
        <input id="push-items-btn" type="button" value="Create Items">
        <br/>
        <input id="new-item-btn" type="button" value="New Item" onclick="newItem()">
    </div>
    <br/>

    <section>
        <input id="back-btn" type="button" value="Menu" onclick="document.location.href='menu.jsp'">
        <br/>
        <input id="log-out-btn" type="button" value="Log Out" onclick="document.location.href='logout.jsp'">
        <br/>
    </section>

    <input type="hidden" id="user_id" name="user_id" value="<%=currentUser.getId()%>">
</body>
</html>
