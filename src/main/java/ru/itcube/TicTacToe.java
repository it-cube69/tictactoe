package ru.itcube;

import ru.itcube.game.Game;

public class TicTacToe {
    public static void main(String[] args) {
        Game game = new Game();
        game.print();
        while (!game.user1Won() && !game.user2Won() && !game.noWinner()) {
            if (game.whoNext() == 1) {
                if (game.user1Turn()) {
                    game.print();
                    game.changeNext(2);
                }
            } else {
                if (game.user2Turn()) {
                    game.print();
                    game.changeNext(1);
                }
            }
        }

        if (game.user1Won()) {
            System.out.println("Пользователь 1 выиграл");
        } else if (game.user2Won()) {
            System.out.println("Пользователь 2 выиграл");
        } else if (game.noWinner()) {
            System.out.println("Ничья");
        }
    }
}
