import com.szxs.entity.Emp;
import com.szxs.entity.Pager;
import com.szxs.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * HQL使用   分页查询
 */
public class TestQuery9 {
    public static void main(String[] args) {
        Pager<Emp> pager = queryEmps(1, 5, new Emp(0, "%E%"));
        System.out.println("第"+pager.getPageNo() +"页,每页"+ pager.getPageSize()+"行,总页数:"
                +pager.getTotalPage() +" ,总行数:"+pager.getTotalRows());
        for (Emp e:pager.getDatas()          ) {
            System.out.println(e);
        }
    }

    public static Pager<Emp> queryEmps(int pageNo, int pageSize, Emp emp){
        Pager<Emp> pager = new Pager<Emp>();
        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        pager.setTotalRows(queryEmpsRows(emp));
        //  (总行数+每页行数-1)/每页行数
        pager.setTotalPage( (pager.getTotalRows()+pageSize-1)/pageSize );
        pager.setDatas(queryEmps2(pageNo,pageSize,emp));
        return pager;
    }

    public static int queryEmpsRows(Emp emp){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select count(empno) from Emp where ename like :ename ";
        Query query = session.createQuery(sql);
        query.setProperties(emp);
        Long o =(Long) query.uniqueResult();

        transaction.commit();
        session.close();
        return o.intValue();
    }

    public static List<Emp> queryEmps2(int pageNo,int pageSize,Emp emp){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "from Emp where ename like :ename ";
        Query query = session.createQuery(sql);
        query.setProperties(emp);
        query.setFirstResult( (pageNo-1)*pageSize );
        query.setMaxResults(pageSize);

        List<Emp> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }
}
