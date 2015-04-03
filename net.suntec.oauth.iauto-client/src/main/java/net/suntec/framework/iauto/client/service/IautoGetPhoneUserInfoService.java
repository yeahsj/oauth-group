package net.suntec.framework.iauto.client.service;

import net.suntec.framework.iauto.client.constant.IautoErrorCodeConstant;
import net.suntec.framework.iauto.client.dto.IautoConfigDTO;
import net.suntec.framework.iauto.client.dto.IautoHeaderDTO;
import net.suntec.framework.iauto.client.dto.param.IautoGetDeviceUserInfoParamDTO;
import net.suntec.framework.iauto.client.exception.ASIautoException;

import org.scribe.model.OAuthRequest;

import com.openjava.core.util.StrUtil;

/**
 * 
 * @项目名称: OauthSrv
 * @功能描述: 根据clientId以及sessionToken 获取终端用户信息
 * @当前版本： 1.0
 * @创建时间: 2014-6-17 上午11:49:27
 * @author: <a href="mailto:yeahsj@gmail.com">yeahsj</a>
 * @修改历史:
 */
public class IautoGetPhoneUserInfoService extends IautoGetUserInfoService {
	private static String USER_INFO_PHONE_URL = "/aswapi/getUserInfoForPhone";

	public IautoGetPhoneUserInfoService(IautoConfigDTO configDTO,
			IautoGetDeviceUserInfoParamDTO paramDTO, IautoHeaderDTO headerDTO) {
		super(configDTO, paramDTO, headerDTO);
		super.loginUrl = USER_INFO_PHONE_URL;
	}

	@Override
	public void checkParams() {
		if (StrUtil.isEmpty(headerDTO.getSessionToken())) {
			throw new ASIautoException(errMsg,
					IautoErrorCodeConstant.IT_GUI_ERR_NO_SESSIONTOKEN);
		}
	}

	@Override
	public void addBodyParameters(OAuthRequest request) {
		request.addBodyParameter("client_id", paramDTO.getClientId());
		request.addBodyParameter("languageCode", paramDTO.getLanguageCode());
	}

	

}
