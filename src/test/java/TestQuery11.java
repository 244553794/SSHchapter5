import com.szxs.entity.Emp;
import com.szxs.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * HQL使用   投影查询 单列
 */
public class TestQuery11 {
    public static void main(String[] args) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select ename from Emp ";
        Query query = session.createQuery(sql);
        List<Object> list = query.list();
        for (Object e:list          ) {
            System.out.println(e);
        }

        transaction.commit();
        session.close();
    }
}
