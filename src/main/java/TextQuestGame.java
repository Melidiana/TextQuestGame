import java.util.Scanner;

public class TextQuestGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ты стоишь в космическом порту и готов подняться на борт космического корабля.\n" +
                "    Разве ты не об этом мечтал?\n" +
                "    Добро пожаловать на наш корабль!");
        System.out.println("Представьтесь:");
        String playerName = scanner.nextLine();
        System.out.println("Принять вызов?");
        String answer = scanner.nextLine();

        boolean isFinished = false;
        while (!isFinished) {
            if (answer.equals("принять вызов")) {
                System.out.println("Ты принял вызов. Поднимешься на мостик к капитану?");
                System.out.println("Выбери варианты ответов: подняться на мостик к капитану или отказаться подниматься на мостик");
                answer = scanner.nextLine();

            }
            else if (answer.equals("отклонить вызов")) {
                System.out.println("Ты отклонил вызов. Поражение");
                break;
            }
            else if (answer.equals("подняться на мостик к капитану")) {
                System.out.println("Ты поднялся на мостик. Ты кто?");
                System.out.println("Выбери варианты ответов: рассказать о себе правду или солгать о себе");
                answer = scanner.nextLine();
            }
            else if (answer.equals("отказаться подниматься на мостик")) {
                System.out.println("Ты не пошел на переговоры. Поражение");
                break;
            }
            else if (answer.equals("рассказать о себе правду")) {
                System.out.println("Тебя вернули домой. Победа");
                answer = scanner.nextLine();
            }
            else if (answer.equals("солгать о себе")) {
                System.out.println("Твою ложь разоблачили. Поражение");
                break;
            }
        }
    }
}

