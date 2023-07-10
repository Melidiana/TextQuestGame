<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Text Quest Game</title>
    <style>
        img {
            max-width: 700px;
            max-height: 700px;
        }
        h1 {
            font-size: 20px;
            color: red;
        }

        h2 {
            font-size: 20px;
            color: blue;
        }
        h3 {
            font-size: 18px;
            color: black;
        }

        p {
            font-size: 20px;
            color: blue;
        }

        .section-container {
            border: 2px solid black;
            display: inline-block;
            padding: 10px;
            margin: 10px;
        }
    </style>
</head>
<body>
<div class="section-container">
    <img src="images/greeting.jpg" alt="Greeting Image">
    <h1>
        <% String playerName = (String) request.getSession().getAttribute("playerName");
            if (playerName == null || playerName.isEmpty()) {
                playerName = request.getParameter("playerName");
                if (playerName == null || playerName.trim().isEmpty()) {
                    playerName = "Unknown Player";
                }
                request.getSession().setAttribute("playerName", playerName);
            }
            out.println("Мы рады приветствовать вас, " + playerName + "!");
        %>
    </h1>
    <h2>Перед вами текстовый квест.</h2>
    <h3>Вам нужно выбрать ответ на вопрос:</h3>
    <p><%= request.getAttribute("message") %></p>

    <% Boolean showImage = (Boolean) request.getAttribute("showImage");
        if (showImage != null && showImage) { %>
    <img src="images/victory.jpg" alt="Victory Image" style="width: 500px; height: 300px;>
    <% } %>

    <% Boolean showImage1 = (Boolean) request.getAttribute("showImage1");
        if (showImage1 != null && showImage1) { %>
    <img src="images/loss.jpg" alt="Loss Image" style="width: 500px; height: 300px;>
    <% } %>

    <form method="post" action="game">
        <input type="hidden" name="action" value="continue">

        <% String[] options = (String[]) request.getAttribute("options");
            if (options != null) {
                for (String option : options) { %>
        <input type="submit" name="answer" value="<%= option %>">
        <% }
        } %>
    </form>
</div>
</body>
</html>