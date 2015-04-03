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
public class IautoGetWebUserInfoService extends IautoGetUserInfoService {
	private static String USER_INFO_WEB_URL = "/aswapi/getUserInfoForWeb";

	public IautoGetWebUserInfoService(IautoConfigDTO configDTO,
			IautoGetDeviceUserInfoParamDTO paramDTO, IautoHeaderDTO headerDTO) {
		super(configDTO, paramDTO, headerDTO);
		super.loginUrl = USER_INFO_WEB_URL;
	}

	@Override
	public void checkParams() {
		if (StrUtil.isEmpty(headerDTO.getSessionToken())) {
			throw new ASIautoException(errMsg,
					IautoErrorCodeConstant.IT_GUI_ERR_NO_SESSIONTOKEN);
		}
		if (StrUtil.isEmpty(paramDTO.getClientId())) {
			throw new ASIautoException(errMsg,
					IautoErrorCodeConstant.IT_GUI_DEVICE_ERR_NO_CLIENTID);
		}
	}

	@Override
	public void addBodyParameters(OAuthRequest request) {
		request.addBodyParameter("client_id", paramDTO.getClientId());
		request.addBodyParameter("languageCode", paramDTO.getLanguageCode());
	}

	
	//https://info.iauto.com/accountsync/auth/pocket?sessionToken=%3CTK%3E01zy%2FVyoVfuDFX2ijd6khR3d8Qv2op6MUYfyt%2F9vlArmg88kVXc5N3kMRfe1NNryabNnYxa3ZqvvMG5W4w2ycr4g%3D%3D&iautoClientId=CarDevice_726ff9b40d40aa1e6e798abc701b4e06&deviceNo=7ADF1323450F5F364A64A4EA4BCF3EE4&platformVersion=01000000&backurl=http%3A%2F%2Flocalhost%2Foauth2callback%2Fiauto-app%3A%2F%2Fnet.suntec.web.pocket%2Fchrome%2Fcontent%2Fcallback.html&st=0.14663751143962145

}
