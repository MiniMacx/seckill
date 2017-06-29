CREATE DATABASE seckill;


use seckill;

CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` timestamp NOT NULL COMMENT '秒杀开启时间',
`end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

insert into seckill (name,number,start_time,end_time)
VALUES
('1000元秒杀iphone6',100,'2017-3-20 10:00:00','2017-5-18 00:00:00'),
('500元秒杀ipad2',100,'2017-3-23 00:00:00','2017-5-18 00:00:00'),
('300元秒杀小米4',100,'2017-3-9 00:00:00','2017-5-18 00:00:00'),
('200元秒杀红米note',100,'2017-3-9 00:00:00','2017-5-18 00:00:00');

--秒杀成功明细表
--用户登录认证相关的信息
create table success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识:-1无效　０成功　１已付款　２已发货',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(seckill_id,user_phone),/*联合主键*/
key idx_create_time(create_time)

)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

--链接数据库控制台
mysql -uroot -p