package net.suntec.framework.iauto.client.service;

import net.suntec.framework.iauto.client.dto.IautoConfigDTO;
import net.suntec.framework.iauto.client.dto.IautoHeaderDTO;
import net.suntec.framework.iauto.client.dto.param.IautoPhoneLoginParamDTO;

/**
 * 
 * 
 * @项目名称: OauthSrv
 * @功能描述:
 * @当前版本： 1.0
 * @创建时间: 2014年12月4日 下午5:48:53
 * @author: <a href="mailto:yeahsj@yahoo.com.cn">yeahsj</a>
 * @修改历史:
 */
public class IautoDeviceLoginService extends IautoLoginService {

	static String PHONE_LOGIN_URL = "/auth/oauth/v2.0/token";

	public IautoDeviceLoginService(IautoConfigDTO configDTO,
			IautoPhoneLoginParamDTO paramDTO, IautoHeaderDTO headerDTO) {
		super(configDTO, paramDTO, headerDTO);
	}

	@Override
	public void service() {
		super.login(PHONE_LOGIN_URL);
	}

}
