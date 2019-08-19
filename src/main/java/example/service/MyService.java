package example.service;

import example.entity.Account;
import example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService()
public class MyService {


    private static final Logger LOGGER = Logger.getLogger(MyService.class.getSimpleName());

    @WebMethod
    public Account createAccount(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            account.setId(Calendar.getInstance().getTimeInMillis());
            account.setStatus(1);
            session.save(account);
            transaction.commit();
            return account;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Can't create account: ", ex);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @WebMethod
    public Account deposit(String username, String password, double balance) {
        // get account by username, password.
        // update balance.
        // save transaction.
        Account account = new Account();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            account.setId(Calendar.getInstance().getTimeInMillis());
            account.setStatus(1);
            session.save(account);
            transaction.commit();
            return account;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Can't create account: ", ex);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }
}
