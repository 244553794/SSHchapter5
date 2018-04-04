import com.szxs.entity.Emp;
import com.szxs.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * HQL使用
 */
public class TestQuery {
    public static void main(String[] args) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "from Emp where ename like '%E%'";
        Query query = session.createQuery(sql);
        List<Emp> list = query.list();
        for (Emp e:list          ) {
            System.out.println(e);
        }

        transaction.commit();
        session.close();
    }
}
