package com.zee.bll.base;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zee.bll.base.Interface.IBaseBll;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpOperLog;
import com.zee.ent.extend.gp.GpUser;
import com.zee.set.symbolic.CustomSymbolic;

@Service
@Transactional
public class BaseBll implements IBaseBll {
	@Value("${isLog.write}")
	protected boolean isLogWrite;

	@Value("${isLog.read}")
	protected boolean isLogRead;

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	public static final Logger logger = LoggerFactory.getLogger(BaseBll.class);

	@Autowired
	protected IBaseUntDal<GpOperLog> operationLogDal;

	protected void addOperationLog(ResultModel result) {
		Long incomeCount = 0L;
		if (result.getIncomeCount() != null)
			incomeCount = Long.valueOf(result.getIncomeCount());

		if (result.getIsSuccess())
			if (incomeCount != 0 && incomeCount > result.getTotalCount())
				result.setResultMessage(result.getResultMessage() + " 传入数据" + incomeCount + " 条，实际操作 " + result.getTotalCount() + " 。");
		GpUser gpUser = getCurrentUser();
		if (gpUser != null) {
			GpDomain currentDomain = gpUser.getCurrentDomain();
			if (currentDomain != null) {
				result.setDomainId(currentDomain.getId());
				result.setDomainName(currentDomain.getName());
			}
		}
		operationLogDal.add(result);
	}

	protected GpUser getCurrentUser() {
		Object object = request.getAttribute(CustomSymbolic.REQUEST_CURRENT_USER);
		return object == null ? null : (GpUser) object;
	}

	@PostConstruct
	public void init() {

		if (RequestContextHolder.getRequestAttributes() != null) {
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

		}

	}
}
