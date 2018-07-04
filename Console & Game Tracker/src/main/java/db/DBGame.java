package db;

import com.sun.xml.internal.ws.handler.HandlerException;
import models.Console;
import models.Game;
import models.Owner;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBGame {

    public static Session session;
    public static Transaction transaction;

    public static List<Console> allConsolesOfAGame(Game game){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Console> results = null;
        try{
            Criteria cr = session.createCriteria(Console.class);
            cr.createAlias("games", "game");
            cr.add(Restrictions.eq("game.id", game.getId()));
            results = cr.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }


    public static List<Owner> allOwnersWithAFavGame(Game game){
        session = HibernateUtil.getSessionFactory().openSession();

        List<Owner> results = null;

        try{
            Criteria cr = session.createCriteria(Owner.class);
            cr.add(Restrictions.eq("favouriteGame", game));
            results = cr.list();
        }catch (HandlerException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }

}
