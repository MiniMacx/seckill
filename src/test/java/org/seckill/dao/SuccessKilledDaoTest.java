package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by airmacx on 17-3-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;
    @Test
    public void testInsertSuccessKilled() throws Exception{
        System.out.println(successKilledDao.insertSuccessKilled(1000l,13601992766l));
        System.out.println(successKilledDao.insertSuccessKilled(1000l,13671798127l));
        System.out.println(successKilledDao.insertSuccessKilled(1002l,13601992766l));
    }

    @Test
    public void testQueryByIdWithSeckillTest() throws Exception{
        SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(1000l,1360);

    }
}
