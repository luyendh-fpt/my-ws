package example.service;

import example.entity.Account;
import example.entity.TransactionLog;
import example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sun.rmi.runtime.Log;

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
    public Account deposit(String username, String password, double balance, String content) {
        LOGGER.log(Level.INFO, String.format("Content to save: %s", content));
        // save transaction.
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            // get account by username, password.
            Account account = session.createQuery("from Account a where a.username = :username and a.password = :password", Account.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            if (account == null) {
                return null;
            }
            if (balance <= 0) {
                return null;
            }
            // update balance.
            account.addBalance(balance);
            session.save(account);

            TransactionLog transactionLog = TransactionLog.Builder.aTransactionLog()
                    .withSenderId(account.getId())
                    .withReceiveId(account.getId())
                    .withBalance(balance)
                    .withType(1)
                    .withContent(content)
                    .withStatus(1)
                    .build();

            session.save(transactionLog);
            transaction.commit();
            return account;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Can't submit transaction: ", ex);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }
}
