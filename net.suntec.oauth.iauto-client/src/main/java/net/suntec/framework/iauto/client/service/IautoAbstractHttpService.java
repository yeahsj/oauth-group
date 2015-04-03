package net.suntec.framework.iauto.client.service;

import java.util.concurrent.TimeUnit;

import net.suntec.framework.iauto.client.dto.IautoConfigDTO;
import net.suntec.framework.iauto.client.dto.IautoHeaderDTO;
import net.suntec.framework.iauto.client.dto.IautoParamDTO;
import net.suntec.framework.iauto.client.dto.IautoResultDTO;

import org.scribe.model.OAuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @项目名称: OauthSrv
 * @功能描述:
 * @当前版本： 1.0
 * @创建时间: 2014年12月4日 下午5:48:47
 * @author: <a href="mailto:yeahsj@yahoo.com.cn">yeahsj</a>
 * @修改历史:
 */
public abstract class IautoAbstractHttpService<P extends IautoParamDTO, R extends IautoResultDTO>
		implements IautoHttpService {
	IautoConfigDTO configDTO;
	P paramDTO;
	IautoHeaderDTO headerDTO;
	R resultDTO;
	String errMsg;
	boolean isSuccess = false;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public IautoAbstractHttpService(IautoConfigDTO configDTO, P paramDTO,
			IautoHeaderDTO headerDTO) {
		this.configDTO = configDTO;
		this.paramDTO = paramDTO;
		this.headerDTO = headerDTO;
	}

	public void setHeader(OAuthRequest request) {
		request.getHeaders().put("IF-VERSION", headerDTO.getIfVersion());
		request.getHeaders().put("SESSION-TOKEN", headerDTO.getSessionToken());
	}

	public void setTimeOut(OAuthRequest request) {
		request.setConnectTimeout(20, TimeUnit.SECONDS);
		request.setReadTimeout(20, TimeUnit.SECONDS);
	}

	public abstract void addBodyParameters(OAuthRequest request);

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public R getResult() {
		return resultDTO;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
