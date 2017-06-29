package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by airmacx on 17-3-11.
 */

//配置spring和Mybatis整合,junit启动时加载springIOC容器
//spring-test,junit

    @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception{
            long id=1009;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }


    @Test
    public void testQueryAll() throws Exception{

        List<Seckill> listSeckill=seckillDao.queryAll(0,100);
        System.out.println(listSeckill.get(1).toString());
    }

    @Test
    public void testReduceNumber() throws Exception{
        long seckillId=1000;
        Date killTime=new Date();
        System.out.println(seckillDao.reduceNumber(seckillId,killTime));

    }



}
