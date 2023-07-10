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
                request.setAttribute("message", "Добро пожаловать на наш корабль! Ты на пороге каюты капитана. Зайдешь в каюту?");
                request.setAttribute("options", new String[]{"зайти в каюту", "уйти с корабля"});
            } else {
                if (answer.equals("зайти в каюту")) {
                    request.setAttribute("message", "Ты в каюте капитана и видишь на столе две книги. Какую из них ты возьмешь с собой?");
                    request.setAttribute("options", new String[]{"книга о навигации", "книга с рассказами"});
                } else if (answer.equals("уйти с корабля")) {
                    request.setAttribute("message", "К сожалению ты струсил, капитан должен быть смелее. Поражение.");
                    request.setAttribute("options", new String[]{});
                    request.setAttribute("showImage1", true);
                } else if (answer.equals("книга о навигации")) {
                    request.setAttribute("message", "Хороший выбор! Книга о навигации содержит карты и планетарные данные " +
                            "и будет очень полезна во время космического путешествия. При выходе из каюты ты встречаешь члена экипажа, " +
                            "который сообщает о проблеме с двигателем. Что ты решишь сделать?");
                    request.setAttribute("options", new String[]{"зайти в технический отсек и разобраться с проблемой",
                            "продолжить свой путь и игнорировать проблему"});
                } else if (answer.equals("книга с рассказами")) {
                    request.setAttribute("message", "Книга с рассказами может быть интересной, " +
                            "но она не поможет в твоем космическом приключении. Поражение.");
                    request.setAttribute("options", new String[]{});
                    request.setAttribute("showImage1", true);
                } else if (answer.equals("зайти в технический отсек и разобраться с проблемой")) {
                    request.setAttribute("message", "Отличный выбор! Ты решил самостоятельно разобраться с проблемой " +
                            "и найти способ ее устранить. В техническом отсеке ты находишь поломку, которую можешь попытаться " +
                            "починить самостоятельно. Что ты выберешь?");
                    request.setAttribute("options", new String[]{"использовать инструменты и знания для починки",
                            "позвать инженера, чтобы он занимался ремонтом"});
                } else if (answer.equals("продолжить свой путь и игнорировать проблему")) {
                    request.setAttribute("message", "Игнорирование проблемы с двигателем может привести к серьезным последствиям. " +
                            "Поражение.");
                    request.setAttribute("options", new String[]{});
                    request.setAttribute("showImage1", true);
                } else if (answer.equals("использовать инструменты и знания для починки")) {
                    request.setAttribute("message", "Отлично! Ты применяешь свои навыки и знания, чтобы успешно устранить поломку. " +
                            "После устранения поломки, ты возвращаешься на мостик и видишь на радаре незнакомый объект. Что ты решишь сделать?");
                    request.setAttribute("options", new String[]{"приблизиться и исследовать объект",
                            "избегать объект и продолжить свой путь"});
                } else if (answer.equals("позвать инженера, чтобы он занимался ремонтом")) {
                    request.setAttribute("message", "Хотя инженер мог бы помочь, но самостоятельное решение проблемы " +
                            "дает тебе больше контроля над ситуацией. Поражение.");
                    request.setAttribute("options", new String[]{});
                    request.setAttribute("showImage1", true);
                } else if (answer.equals("приблизиться и исследовать объект")) {
                    request.setAttribute("message", "Отличный выбор! Ты решаешь исследовать незнакомый объект и расширить " +
                            "свои знания об окружающей галактике. При приближении к объекту, ты получаешь сигнал SOS от затерянного космического корабля. " +
                            "Что ты решаешь сделать?");
                    request.setAttribute("options", new String[]{"ответить на сигнал и отправить команду для спасения",
                            "проигнорировать сигнал и продолжить свое путешествие"});
                } else if (answer.equals("избегать объект и продолжить свой путь")) {
                    request.setAttribute("message", "Пропускание возможности исследования может означать упущение важных открытий. " +
                            "Поражение.");
                    request.setAttribute("options", new String[]{});
                    request.setAttribute("showImage1", true);
                } else if (answer.equals("ответить на сигнал и отправить команду для спасения")) {
                    request.setAttribute("message", "Отличное решение! Ты решил помочь и отправить команду для спасения затерянного корабля. " +
                            "Твой экипаж может полностью положиться на своего капитана. Победа.");
                    request.setAttribute("showImage", true);
                } else if (answer.equals("проигнорировать сигнал и продолжить свое путешествие")) {
                    request.setAttribute("message", "Проигнорирование сигнала SOS может иметь негативные последствия. Поражение.");
                    request.setAttribute("options", new String[]{});
                    request.setAttribute("showImage1", true);
                } else {
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