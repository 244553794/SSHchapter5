import com.szxs.entity.Emp;
import com.szxs.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * HQL使用  使用名称赋值   要根据不同的数据类型调用 不同的赋值方法
 */
public class TestQuery3 {
    public static void main(String[] args) {
        List<Emp> list = query("%E%",7499);
        for (Emp e:list          ) {
            System.out.println(e);
        }
    }

    public static List<Emp> query(String name,int empno){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "from Emp where ename like :ename and empno = :empno";
        Query query = session.createQuery(sql);
        query.setString("ename",name);
        query.setInteger("empno",empno);
        List<Emp> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
