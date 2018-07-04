package db;

import models.Console;
import models.Game;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBConsole {

    private static Session session;
    private static Transaction transaction;


    public static void addGameToConsole(Game game, Console console){

        game.addConsole(console);
        console.addGame(game);

        DBHelper.update(game);
    }


    public static List<Game> allGamesOfAConsole(Console console){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Game> results = null;
        try{
            Criteria cr = session.createCriteria(Game.class);
            cr.createAlias("consolesAvailableFor", "console");
            cr.add(Restrictions.eq("console.id", console.getId()));
            results = cr.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }

}


