import com.szxs.entity.Emp;
import com.szxs.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HQL使用  使用名称赋值   不要判断数据类型   按MAP传,建议使用
 */
public class TestQuery7 {
    public static void main(String[] args) {

        Map<String,Object> data = new HashMap<String, Object>();
        data.put("empno",7499);
        data.put("ename","%E%");
        List<Emp> list = query(data);
        for (Emp e:list          ) {
            System.out.println(e);
        }
    }

    public static List<Emp> query(Map<String,Object> data){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "from Emp where ename like :ename and empno = :empno";
        Query query = session.createQuery(sql);
        query.setProperties(data);
        List<Emp> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
