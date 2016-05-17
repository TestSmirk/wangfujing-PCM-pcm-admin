package test.com.wangfj.product.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.JedisCluster;

import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.RedisUtil;
import com.wangfj.product.constants.DomainName;
import com.wangfj.product.demo.domain.dto.UpdateUsersDto;
import com.wangfj.product.demo.domain.entity.Users;
import com.wangfj.product.demo.service.intf.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestUserService {

	@Autowired
	public UserService userService;
	// @Autowired
	// protected MongoTemplate mongoTemplate;
	@Autowired
	protected CacheUtils utils;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Test
	public void test() {
		// updateUser();
		// insertUser();
		// insert();
		// get();
		del();
	}

	public void updateUser() {
		final UpdateUsersDto para = new UpdateUsersDto();
		para.setName("ko");
		para.setSid(2);

		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {

				// 你们的功能放在这里
				System.out.println("------------------------------------");
				userService.updateUser(para);
				System.out.println(para.getSid());
				System.out.println("=====================================");
			}
		});
	}

	public void insertUser() {
		Users para = new Users();
		para.setName("hello");
		para.setSid(2);
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				// 你们的功能放在这里

			}
		});
	}

	public void insert() {
		// redisUtil.set("hello","kongxs");
		// for(int i =0;i<200;i++){
		// redisUtil.set("hu"+i,"kongxs");
		// }
		// redisUtil.set("hu","kongxs");
		String domain = "pcm_getShoppeGoodsInfo";
		String key = "skuPage";
		String value = "{'page':{'currentPage':1,'pageSize':10,'count':139,'pages':14,'start':0,'limit':10,'list':[{'sid':1000289,'spuSid':'100269','skuCode':'1000001000289','spuCode':'100100269','stanCode':'32432ewr','stanName':'32432ewr','colorCode':'23','colorCodeName':'23','proWriTime':'2015-10-21 19:36:32.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'王府井集团品牌24235432','skuName':'王府井集团品牌242354322332432ewr','modelCode':'24235432','brandGroupCode':'1000901','brandGroupName':'王府井集团品牌','category':'341','categoryName':'内衣','statCategory':'347','statCategoryName':'毛衣','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'13','colorName':'通用','industryCondition':'0'},{'sid':1000288,'spuSid':'100268','skuCode':'1000001000288','spuCode':'100100268','stanCode':'234rewr','stanName':'234rewr','colorCode':'334','colorCodeName':'334','proWriTime':'2015-10-21 19:34:46.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'测试品牌家电1423423','skuName':'测试品牌家电1423423334234rewr','modelCode':'423423','brandGroupCode':'1000159','brandGroupName':'测试品牌家电1','category':'341','categoryName':'内衣','statCategory':'347','statCategoryName':'毛衣','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'13','colorName':'通用','industryCondition':'0'},{'sid':1000287,'spuSid':'100267','skuCode':'1000001000287','spuCode':'100100267','stanCode':'354','stanName':'354','colorCode':'23','colorCodeName':'23','proWriTime':'2015-10-21 19:30:51.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'李宁242354235','skuName':'李宁24235423523354','modelCode':'242354235','brandGroupCode':'1232232','brandGroupName':'李宁','category':'341','categoryName':'内衣','statCategory':'347','statCategoryName':'毛衣','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'13','colorName':'通用','industryCondition':'0'},{'sid':1000286,'spuSid':'100266','skuCode':'1000001000286','spuCode':'100100266','stanCode':'wr23','stanName':'wr23','colorCode':'23','colorCodeName':'23','proWriTime':'2015-10-21 19:13:20.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'李宁3523','skuName':'李宁352323wr23','modelCode':'3523','brandGroupCode':'1232232','brandGroupName':'李宁','category':'341','categoryName':'内衣','statCategory':'347','statCategoryName':'毛衣','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'13','colorName':'通用','industryCondition':'0'},{'sid':1000285,'spuSid':'100265','skuCode':'1000001000285','spuCode':'100100265','stanCode':'w23','stanName':'w23','colorCode':'323','colorCodeName':'323','proWriTime':'2015-10-21 19:09:22.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'李宁2423','skuName':'李宁2423323w23','modelCode':'2423','brandGroupCode':'1232232','brandGroupName':'李宁','category':'341','categoryName':'内衣','statCategory':'347','statCategoryName':'毛衣','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'13','colorName':'通用','industryCondition':'0'},{'sid':1000284,'spuSid':'100264','skuCode':'1000001000284','spuCode':'100100264','stanCode':'334e','stanName':'334e','colorCode':'23','colorCodeName':'23','proWriTime':'2015-10-21 19:05:38.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'李宁2423523542','skuName':'李宁242352354223334e','modelCode':'2423523542','brandGroupCode':'1232232','brandGroupName':'李宁','category':'341','categoryName':'内衣','statCategory':'347','statCategoryName':'毛衣','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'13','colorName':'通用','industryCondition':'0'},{'sid':1000283,'spuSid':'100263','skuCode':'1000001000283','spuCode':'100100263','stanCode':'ww22','stanName':'ww22','colorCode':'23','colorCodeName':'23','proWriTime':'2015-10-21 19:00:55.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'李宁123343534','skuName':'李宁12334353423ww22','modelCode':'123343534','brandGroupCode':'1232232','brandGroupName':'李宁','category':'341','categoryName':'内衣','statCategory':'347','statCategoryName':'毛衣','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'13','colorName':'通用','industryCondition':'0'},{'sid':1000269,'spuSid':'100250','skuCode':'1000001000269','spuCode':'100100250','stanCode':'34er','stanName':'34er','colorCode':'23','colorCodeName':'23','proWriTime':'2015-10-21 14:21:07.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'测试品牌家电132432423','skuName':'测试品牌家电1324324232334er','modelCode':'32432423','brandGroupCode':'1000159','brandGroupName':'测试品牌家电1','category':'336','categoryName':'长袖衬衫','statCategory':'301','statCategoryName':'polo衫','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'11','colorName':'棕色','industryCondition':'0'},{'sid':1000262,'spuSid':'100243','skuCode':'1000001000262','spuCode':'100100243','stanCode':'23we3','stanName':'23we3','colorCode':'12','colorCodeName':'12','proWriTime':'2015-10-21 13:39:14.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'测试品牌家电12421421423','skuName':'测试品牌家电124214214231223we3','modelCode':'2421421423','brandGroupCode':'1000159','brandGroupName':'测试品牌家电1','category':'336','categoryName':'长袖衬衫','statCategory':'340','statCategoryName':'外套','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'2','colorName':'黄色','industryCondition':'0'},{'sid':1000251,'spuSid':'100232','skuCode':'1000001000251','spuCode':'100100232','stanCode':'46er','stanName':'46er','colorCode':'346','colorCodeName':'346','proWriTime':'2015-10-21 11:39:28.0','photoStatus':0,'proActiveBit':1,'proType':'1','skuSale':'0','spuName':'测试品牌家电57568463','skuName':'测试品牌家电5756846334646er','modelCode':'57568463','brandGroupCode':'1000158','brandGroupName':'测试品牌家电','category':'336','categoryName':'长袖衬衫','statCategory':'340','statCategoryName':'外套','spuSale':'0','awesome':'0','editFlag':'0','colorSid':'10','colorName':'粉色','industryCondition':'0'}]}}";
		// String value =
		// "{'page':{'currentPage':1,'pageSize':10,'count':139,'pages':14,'start':0,'limit':10,'list':[{'sid':1000289,'spuSid':'100269',";
		// redisUtil.setHSet(domain,key,value);
		System.out.println("开始 set: key=" + key + " value:" + value);
		// redisUtil.set("hello", value,1000);
		System.out.println("set结束,返回结果:" + redisUtil.set("hello1", value, 1000));
		// System.out.println("查询结果:"+redisUtil.get("hello", "1"));

	}

	public void get() {
		// redisUtil.set("hello","kongxs");
		// int a =0;
		// for(int i =0;i<20000;i++){
		// String str = redisUtil.get("hh"+i,"111");
		// if(StringUtils.isNotBlank(str)){
		// a++;
		// }
		// }
		redisUtil.expire("pcm_getStock", 1);
		// System.out.println(redisUtil);
		// String str = redisUtil.getHSet("panli", "1000");
		// System.out.println(str);
	}

	public void del() {
		//// redisUtil.set("hello","kongxs");
		// int a =0;
		// for(int i =0;i<20000;i++){
		// redisUtil.del("hh"+i);
		// }
		System.out.println(redisUtil.expire("pcm_getStock", 1));
		System.out.println(redisUtil.expire("pcm_setStock", 1));
		System.out.println(redisUtil.expire("pcm_getShoppeGoodsInfo", 1));
		System.out.println(redisUtil.expire("pcm_getShoppeGoodsInfoList", 1));

		System.out.println(redisUtil.expire("pcm_selectCateGory", 1));
		System.out.println(redisUtil.expire("pcm_getPrice", 1));
		System.out.println(redisUtil.expire("pcm_getCMSSHopperInfo", 1));
		System.out.println(redisUtil.expire("pcm_getCMSCategory", 1));
		// redisUtil.del("hu");
		// get();
	}

	@Test
	public void cachSet() {
		redisUtil.del("pcm_getPrice10000001");
		redisUtil.set("pcm_getPrice10000001", "1000", 2678399);// 2591999
		System.out.println(redisUtil.get("pcm_getPrice10000001", "00"));
	}

	@Test
	public void redisSpuCMSSHopperInfo() {
		// picService.redisSpuCMSSHopperInfo("200012473");
		// List<String> list = redisUtil.getKeys("pcm*");
		// CacheUtils.expire(DomainName.selectCateGory, 1);
		// for (String str : list) {
		//
		// System.out.println(redisUtil.del(str));
		// }
		System.out.println(redisUtil.del(DomainName.getCMSSHopperInfo + "1000000000007"));

		// for (int i = 1; i <= 13074; i++) {
		// System.out.println(redisUtil.del(DomainName.selectCateGory + i));
		// }

	}
}
