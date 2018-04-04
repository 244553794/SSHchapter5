import com.szxs.entity.Emp;
import com.szxs.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * HQL使用  动态SQL语句
 */
public class TestQuery8 {
    public static void main(String[] args) {

        List<Emp> list = query(new Emp(-1,""));
        for (Emp e:list          ) {
            System.out.println(e);
        }
    }

    public static List<Emp> query(Emp emp){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "from Emp where 1=1 ";
        if(emp.getEmpno() > 0){
            sql += " and empno = :empno ";
        }
        if(emp.getEname() != null && !"".equals(emp.getEname())){
            sql += " and ename like :ename ";
        }
        Query query = session.createQuery(sql);
        query.setProperties(emp);
        List<Emp> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
