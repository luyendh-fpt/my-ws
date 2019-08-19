package example.main;

import example.entity.Account;
import example.service.MyService;
import example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.ws.Endpoint;
import java.util.Calendar;
import java.util.logging.Level;

public class MainThread {

    public static void main(String[] argv) {
        Object implementor = new MyService();
        String address = "http://localhost:9000/my-service";
        Endpoint.publish(address, implementor);

//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSession()) {
//            transaction = session.beginTransaction();
//            Account account = new Account();
//            account.setUsername("hung1");
//            account.setPassword("hồng luyến");
//            account.setId(Calendar.getInstance().getTimeInMillis());
//            account.setStatus(1);
//            session.save(account);
//            transaction.commit();
//        } catch (Exception ex) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
    }
}
