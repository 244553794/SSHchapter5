import com.szxs.entity.Emp;
import com.szxs.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * HQL使用   投影查询 多列  使用构造方法返回对象
 */
public class TestQuery13 {
    public static void main(String[] args) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select new Emp(empno,ename) from Emp ";
        Query query = session.createQuery(sql);
        List<Emp> list = query.list();
        for (Emp e:list          ) {
            System.out.println(e);
        }

        transaction.commit();
        session.close();
    }
}
