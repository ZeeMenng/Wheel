//package redis;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.zee.app.start.Starter;
//import com.zee.utl.RedisUtil;
//
///**
// * @author lxy
// * @createDate 2018年4月26日 下午7:45:29
// * @updateDate 2018年4月26日 下午7:45:29
// * @description redis测试demo
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Starter.class)
//@WebAppConfiguration
//public class TestRedis {
//
//	@Autowired
//	RedisUtil redisUtil;
//	
//	@Test
//    public void test(){
//		redisUtil.set("star", "star", 5L);
//    }
//
//}