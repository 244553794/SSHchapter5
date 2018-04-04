import com.szxs.entity.Emp;
import com.szxs.entity.Pager;
import com.szxs.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * HQL使用   分页查询
 */
public class TestQuery10 {
    public static void main(String[] args) {
        Pager<Emp> pager = queryEmps(2, 5, new Emp(0, ""));
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

        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();


        Criteria criteria = session.createCriteria(Emp.class);
        //  select * from emp where ename like '%fdsa%'
        if(emp.getEname() != null && !"".equals(emp.getEname())) {
            criteria.add(Restrictions.ilike("ename", emp.getEname(), MatchMode.ANYWHERE));
        }
        //select count(empno) from emp where ename like '%fdsa%'
        criteria.setProjection(Projections.count("empno"));
        pager.setTotalRows( ((Long)criteria.uniqueResult()).intValue());  //设置总行数

        //  (总行数+每页行数-1)/每页行数
        pager.setTotalPage( (pager.getTotalRows()+pageSize-1)/pageSize );

        //select * from emp where ename like '%fdsa%'
        criteria.setProjection(null);
        criteria.setFirstResult( (pageNo-1)*pageSize ).setMaxResults(pageSize);
        pager.setDatas(criteria.list());
        return pager;
    }


}
