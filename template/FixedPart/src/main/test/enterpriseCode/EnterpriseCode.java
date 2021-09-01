package enterpriseCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zee.app.start.Starter;
import com.zee.bll.extend.unity.da.DaEnterpriseInfoUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaEnterpriseInfo;
import com.zee.utl.FileUtil;
import com.zee.utl.Tools;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Starter.class)
@WebAppConfiguration
public class EnterpriseCode{
	
	private int PAGEINDEX = 1;
	
	private int PAGESIZE = 100000000;
	
	@Autowired
	@Qualifier("daEnterpriseInfoUntBll")
	protected DaEnterpriseInfoUntBll daEnterpriseInfoUntBll;
	
	@Autowired
	protected FileUtil fileUtil;
	
	@Test
    public void test(){
		addCode();
	}
	
	private void addCode() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		StringBuffer selectBuffer1 = new StringBuffer();
		selectBuffer1.append("	SELECT A.enterprise_code enterpriseCode FROM da_enterprise_info A       ");
		JSONObject pageObject = new JSONObject();
		pageObject.put("pageIndex", PAGEINDEX);
		pageObject.put("pageSize", PAGESIZE);
		map1.put("Page", pageObject);
		map1.put("Sql", selectBuffer1.toString());
		ResultModel resultModel1 = daEnterpriseInfoUntBll.getListBySQL(map1);
		List<String> codeList = new ArrayList<String>();
		List<Map<String, Object>> modelList1 = (List<Map<String, Object>>) resultModel1.getData();
		for (Map<String, Object> mMap1 : modelList1) {
			if(mMap1.get("enterpriseCode") != null){
				codeList.add(mMap1.get("enterpriseCode").toString());
			}
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2.append("	SELECT A.id id FROM da_enterprise_info A WHERE A.enterprise_code IS NULL     ");
		map2.put("Page", pageObject);
		map2.put("Sql", selectBuffer2.toString());
		ResultModel resultModel = daEnterpriseInfoUntBll.getListBySQL(map2);
		List<Map<String, Object>> modelList2 = (List<Map<String, Object>>) resultModel.getData();
		for (Map<String, Object> mMap2 : modelList2) {
			ResultModel result = null;
			try {
				result = daEnterpriseInfoUntBll.getModel(mMap2.get("id").toString());
			} catch (Exception e) {
				fileUtil.writeText("F://", "id.txt", false, mMap2.get("id").toString() + "\n");
				System.out.println(mMap2.get("id").toString());
				e.printStackTrace();
			}
			if(result != null && result.getIsSuccess()){
				DaEnterpriseInfo daEnterpriseInfo = (DaEnterpriseInfo)result.getData();
				daEnterpriseInfo.setEnterpriseCode(getUniEnterpriseCode(codeList));
				daEnterpriseInfoUntBll.update(daEnterpriseInfo);
			}
		}
	}

	private String getUniEnterpriseCode(List<String> codeList){
		String enterpriseCode = Tools.getUUID().substring(0, 4);
		if(codeList.contains(enterpriseCode)){
			getUniEnterpriseCode(codeList);
		}
		return enterpriseCode;
	}
	
}