import com.szxs.entity.Emp;
import com.szxs.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * HQL使用  使用名称赋值   不要判断数据类型   按对象传,建议使用
 */
public class TestQuery6 {
    public static void main(String[] args) {

        List<Emp> list = query(new Emp(7499,"%E%"));
        for (Emp e:list          ) {
            System.out.println(e);
        }
    }

    public static List<Emp> query(Emp emp){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "from Emp where ename like :ename and empno = :empno";
        Query query = session.createQuery(sql);
        query.setProperties(emp);
        List<Emp> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
