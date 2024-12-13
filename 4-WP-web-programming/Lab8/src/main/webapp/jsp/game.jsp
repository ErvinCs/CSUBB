<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ubb.web.lab8.model.User" %>
<%@ page import="ubb.web.lab8.controller.BoardController" %>
<%@ page import="java.util.Optional" %>
<%@ page import="ubb.web.lab8.model.Board" %>
<%  User currentUser = (User)(session.getAttribute("currentSessionUser"));
    String username = "";
    Long userId = null;
    boolean valid = false;
    if (currentUser == null) {
        session.invalidate();
        response.sendRedirect("../index.html");
    } else {
        username = currentUser.getUsername();
        userId = currentUser.getId();
        valid = true;
    }
    int numberOfMoves = 0;
    BoardController instance = BoardController.GetInstance();
    Optional<Board> optionalBoard = instance.getByUserId(currentUser.getId());
    Board board = null;
    if (valid) {
        if (optionalBoard.isEmpty()) {
            board = new Board(currentUser.getId());
            instance.addBoard(board);
        } else {
            board = optionalBoard.get();
        }
        numberOfMoves = board.getNumberOfMoves();
    }
%>
<html>
<head>
    <title>Game</title>
    <link rel="stylesheet" type="text/css" media="screen" href="../css/game.css" />
    <script src="../lib/jquery-2.0.3.js"></script>
    <script lang="javascript">

        // function processCookie(cookieName) {
        //     var name = cookieName + "=";
        //     var decodeCookie = decodeURIComponent(document.cookie);
        //     var cookieArr = decodeCookie.split(';');
        //
        //     console.log(decodeCookie);
        //     console.log(cookieArr);
        //     for(var i = 0; i < cookieArr.length; i++) {
        //         console.log(cookieArr[i]);
        //     }
        // }

        $(document).ready(function(){
            $(".cell").click(function(){
                var div = $("div[name='game-cell']").eq($(this).index());
                var id = div.attr("id");
                var value = div.attr("value");
                var userId = $("#user_id").attr("value");
                $.get('gameServlet', {
                    cellId: id,
                    cellVal: value,
                    userId: userId,
                    // success:function(data) {
                    //     //Issue: Undefined
                    //     console.log(data);
                    //     //processCookie("board");
                    //     //var obj = $.parseJSON(data);
                    //
                    //    // var obj = S{param.values()};
                    //
                    //    //for(var i = 0; i < 9; i++) {
                    //    //    $("#" + i.toString()).innerHTML = '<img src="../res/' + obj[i].cellVal + '.png">';
                    //    //}
                    // }
                });

            });
        });

    </script>
</head>
<body>
    <div id="user-info">
        <br/>
        Playing as: <%= username %>
        <br/>
        Number of moves: <%= numberOfMoves %>
        <br/>
        <form action="${pageContext.request.contextPath}/jsp/gameServlet" method="post">
            <input type="submit" name="Reset" value="<%=userId%>">
        </form>
    </div>

    <div class="game-grid">
    <%
        int[] tiles = (int[])request.getAttribute("board");
        for(int i = 0; i < Board.BoardSize; i++) {
    %>
        <div id="<%=i%>" name="game-cell" value="<%=board == null ? 0 : String.valueOf(board.getTileAt(i))%>" class="cell">
            <img src="../res/<%= board == null ? 0 : String.valueOf(board.getTileAt(i)) %>.png">
        </div>
    <%
        }
    %>
    </div>



    <input type="hidden" id="user_id" name="user_id" value="<%=userId%>">
</body>
</html>
