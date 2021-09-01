package com.zee.utl;

import com.alibaba.fastjson.JSONObject;
import com.zee.app.base.BaseApp;
import com.zee.bll.extend.unity.gp.GpLoginLogUntBll;
import com.zee.bll.extend.unity.gp.GpTokenUntBll;
import com.zee.bll.extend.unity.gp.GpUserUntBll;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.extend.gp.GpUser;
import com.zee.set.symbolic.CustomSymbolic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lxy
 * @createDate 2018年4月20日 下午3:46:52
 * @updateDate 2018年4月20日 下午3:46:52
 * @description 多线程处理类
 */
//@Component
public class Executors extends BaseApp{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("gpTokenUntBll")
	protected GpTokenUntBll gpTokenUntBll;
	
	@Autowired
	@Qualifier("gpLoginLogUntBll")
	protected GpLoginLogUntBll gpLoginLogUntBll;
	
	@Autowired
	@Qualifier("gpUserUntBll")
	protected GpUserUntBll gpUserUntBll;
	

	
	/**
	 * 处理登陆信息
	 * @param pMap
	 */
	@Async("executor")
	public void dealLogin(Map<String, Object> pMap) {
		String token = pMap.get("token") == null ? "" : pMap.get("token").toString();
		String userId = pMap.get("userId") == null ? "" : pMap.get("userId").toString();
		String userName = pMap.get("userName") == null ? "" : pMap.get("userName").toString();
		String ip = pMap.get("ip") == null ? "" : pMap.get("ip").toString();
		String browser = pMap.get("browser") == null ? "" : pMap.get("browser").toString();
		String os = pMap.get("os") == null ? "" : pMap.get("os").toString();
		Date loginTime = DateUtils.getCurrentTime();
		
		//处理用户token表
		GpToken gpToken = new GpToken();
		gpToken.setUserId(userId);
		gpToken.setUserName(userName);
		gpToken.setAccessToken(token);
		gpToken.setAddTime(loginTime);
		gpTokenUntBll.add(gpToken);
		
		// 处理登陆日志
		GpLoginLog gpLoginLog = new GpLoginLog();
		gpLoginLog.setId(token);
		gpLoginLog.setUserName(userName);
		gpLoginLog.setIp(ip);
		gpLoginLog.setBrowser(browser);
		gpLoginLog.setOs(os);
		gpLoginLog.setLoginTime(loginTime);
		gpLoginLog.setIsSuccessCode((byte) 0);
		gpLoginLogUntBll.add(gpLoginLog);
		
		// 处理用户登陆次数
		GpUser gpUser = new GpUser();
		gpUser.setId(pMap.get("id") == null ? "" : pMap.get("id").toString());
		Integer loginCount = pMap.get("loginCount") == null ? 0 : Integer.parseInt(pMap.get("loginCount").toString());
		gpUser.setLoginCount(loginCount + 1);
		gpUserUntBll.update(gpUser);
	}
	
	/**
	 * 获取post参数
	 * @return
	 */
	private JSONObject getPostData(Map<String, Object> paramMap){
		JSONObject postData = new JSONObject();
		postData.put("secretkey", CustomSymbolic.SENTIMENT_SECRETKEY);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewName", "hour");//视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "false");
		map.put("pastNum", "1");
		List<String> timesList = TimesView.getTimesView(map);
		String startTime = DateUtils.date2String(DateUtils.string2Date(timesList.get(0), "yyyy-MM-dd HH"), "yyyyMMddHH") + "0000";
		String endTime = DateUtils.date2String(DateUtils.string2Date(timesList.get(0), "yyyy-MM-dd HH"), "yyyyMMddHH") + "5959";
//		postData.put("publishTime", "20180801000000-20180830235959");
//		System.out.println("publishTime===" + startTime + "-" + endTime);
		postData.put("publishTime", startTime + "-" + endTime);
		postData.put("queryKeys", paramMap.get("keyName").toString());
		postData.put("noQueryKeys", paramMap.get("excludeName").toString());
		postData.put("pageNum", "1");
		postData.put("pageSize", paramMap.get("frequency").toString());
//		postData.put("mediaTypes", paramMap.get("mediaTypeCodeSet").toString());//暂时注掉，类型少了爬不到数据
		postData.put("sort", "time");
		postData.put("desc", "true");
		postData.put("red", "false");
		postData.put("merge", "true");
		
		
		return postData;
	}
	

	
}