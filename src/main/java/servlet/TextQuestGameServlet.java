package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TextQuestGameServlet", urlPatterns = "/game")
public class TextQuestGameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("startGame".equals(action)) {
            response.sendRedirect("enterName.jsp");
            return;
        }

        String playerName = (String) request.getSession().getAttribute("playerName");

        if (playerName != null && !playerName.isEmpty()) {
            request.getSession().setAttribute("playerName", playerName);
        }

        String answer = request.getParameter("answer");
        boolean isFinished = false;
        while (!isFinished) {
            if (answer == null) {
                request.setAttribute("message", "Добро пожаловать на наш корабль. Ты потерял память. Принять вызов НЛО?");
                request.setAttribute("options", new String[]{"принять вызов", "отклонить вызов"});
            } else {
                if (answer.equals("принять вызов")) {
                    request.setAttribute("message", "Ты принял вызов. Поднимешься на мостик к капитану?");
                    request.setAttribute("options", new String[]{"подняться на мостик к капитану", "отказаться подниматься на мостик"});
                } else if (answer.equals("отклонить вызов капитана")) {
                    request.setAttribute("message", "Ты отклонил вызов. Поражение.");
                    isFinished = true;
                } else if (answer.equals("подняться на мостик к капитану")) {
                    request.setAttribute("message", "Ты поднялся на мостик. Ты кто?");
                    request.setAttribute("options", new String[]{"рассказать о себе правду", "солгать о себе"});
                } else if (answer.equals("отказаться подниматься на мостик")) {
                    request.setAttribute("message", "Ты не пошел на переговоры. Поражение.");
                    isFinished = true;
                } else if (answer.equals("рассказать о себе правду")) {
                    request.setAttribute("message", "Тебя вернули домой. Победа.");
                    request.setAttribute("showImage", true);
                } else if (answer.equals("солгать о себе")) {
                    request.setAttribute("message", "Твою ложь разоблачили. Поражение.");
                    isFinished = true;
                }
                else {
                    request.setAttribute("message", "Ошибка: Неправильный выбор.");
                    request.setAttribute("options", new String[]{});
                }
            }
            if (!isFinished) {
                request.getRequestDispatcher("playGame.jsp").forward(request, response);
            } else {
                break;
            }
        }
    }
}