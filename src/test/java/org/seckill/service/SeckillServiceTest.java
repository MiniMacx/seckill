package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.rmi.runtime.Log;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by airmacx on 17-3-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list=seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        long id=1000;
        Seckill seckill=seckillService.getById(id);
        logger.info("Seckill={}",seckill);
    }

    @Test
    public void testSeckillLogic() throws Exception{
        long id=1001L;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("Exposer={}",exposer);
            long phone=13601992756L;
            String md5=exposer.getMd5();
            SeckillExecution seckillExecution=seckillService.executeSeckill(id,phone,md5);
            logger.info("seckillExecution={}",seckillExecution);
        }
        else
        {
            logger.warn("Exposer={}",exposer);
        }


    }

    @Test
    public void exportSeckillUrl() throws Exception {

        long id=1001L;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        logger.info("Exposer={}",exposer);
    }

    @Test
    public void executeSeckill() throws Exception {

        long id=1001L;
        long phone=13601992766L;
        String md5="1f4f1aac9b729ad2adff8d7228093fd5";
        SeckillExecution seckillExecution=seckillService.executeSeckill(id,phone,md5);
        logger.info("seckillExecution={}",seckillExecution);

    }

    @Test
    public void executeSeckillProcedure(){
        long seckillId=1003;
        long phone=13601992766L;
        String md5=null;
        Exposer exposer=seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            md5=exposer.getMd5();
            SeckillExecution seckillExecution=seckillService.executeSeckillProcedure(seckillId,phone,md5);
            logger.info(seckillExecution.getStateInfo());
        }
    }
}