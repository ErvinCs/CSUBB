<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Items</title>
    <script src="../lib/jquery-2.0.3.js"></script>
    <script lang="javascript">

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
            thname.innerHTML = "Username";
            tr.appendChild(thname);

            var thprice = document.createElement('th');
            thprice.innerHTML = "Item Count";
            tr.appendChild(thprice);

            var thcateg = document.createElement('th');
            thcateg.innerHTML = "Total Item Value";
            tr.appendChild(thcateg);

            for(var i = 0; i < size; i++) {
                tr = tbl.insertRow(i+1);
                for (var column = 0; column < 3; column++) {
                    var td = document.createElement('td');
                    td = tr.insertCell(column);
                    switch (column) {
                        case 0:
                            td.innerHTML = items[i].username;
                            break;
                        case 1:
                            td.innerHTML = items[i].itemCount;
                            break;
                        case 2:
                            td.innerHTML = items[i].totalValue;
                            break;
                    }
                }

            }

            var section = document.getElementById('items-div');
            section.appendChild(tbl);
        }

        $(document).ready(function() {
            $("#show-items-btn").click(function () {
                $.getJSON('adminItemsServlet',
                    {},
                    loadData
                );
            });
        });

    </script>
</head>
<body>
    <input id="show-items-btn" type="button" value="Show Items">
    <div id="items-div">
    </div>

    <section>
        <input id="back-btn" type="button" value="Menu" onclick="document.location.href='menu.jsp'">
        <br/>
        <input id="log-out-btn" type="button" value="Log Out" onclick="document.location.href='logout.jsp'">
        <br/>
    </section>


<br/>
</body>
</html>
