package models;

import db.DBConsole;
import db.DBGame;
import db.DBHelper;

import java.util.List;

public class Runner {
    public static void main(String[] args) {

        Console console1 = new Console("Sony", "PS4", "Europe" );
        Console console2 = new Console("MS", "Xbox", "US" );
        Console console3 = new Console("Nintendo", "Wii", "Japan" );

        DBHelper.save(console1);
        DBHelper.save(console2);
        DBHelper.save(console3);

        Game game1 = new Game("Pack-Man", Genre.ARCADE, console1);
        Game game2 = new Game("Skyrin", Genre.RPG, console2);
        Game game3 = new Game("Doom", Genre.FPS, console3);
        Game game4 = new Game("Pinball", Genre.ARCADE, console1);
        Game game5 = new Game("Monster Hunter", Genre.RPG, console2);
        DBHelper.save(game1);
        DBHelper.save(game2);
        DBHelper.save(game3);
        DBHelper.save(game4);
        DBHelper.save(game5);

        DBConsole.addGameToConsole(game1, console1);
        DBConsole.addGameToConsole(game1, console2);
        DBConsole.addGameToConsole(game3, console3);
        DBConsole.addGameToConsole(game4, console1);
        DBConsole.addGameToConsole(game5, console2);

        Owner player1 = new Owner("Jay", game1);
        Owner player2 = new Owner("Tia", game2);
        Owner player3 = new Owner("Aryan", game2);
        DBHelper.save(player1);
        DBHelper.save(player2);
        DBHelper.save(player3);

        List<Game> allGamesOfAConsole = DBConsole.allGamesOfAConsole(console1);

//        Game allGamesOfAConsole = DBConsole.allGamesOfAConsole(console1);

        List<Console> allConsolesOfAGame = DBGame.allConsolesOfAGame(game1);

//        Console allConsolesOfAGame = DBGame.allConsolesOfAGame(game1);

        List<Owner> allOwnersWithAFavGame = DBGame.allOwnersWithAFavGame(game2);

    }
}
