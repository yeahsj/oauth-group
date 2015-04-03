package net.suntec.framework.iauto.client.service;

import java.io.IOException;

import net.suntec.framework.iauto.client.dto.IautoConfigDTO;
import net.suntec.framework.iauto.client.dto.IautoHeaderDTO;
import net.suntec.framework.iauto.client.dto.param.IautoGetDeviceUserInfoParamDTO;
import net.suntec.framework.iauto.client.dto.result.IautoPhoneLoginResultDTO;

import org.xml.sax.SAXException;

public class IautoGetWebUserInfoServiceTest extends IautoDeviceLoginServiceTest {
	/**
	 * response body : {"code":1000,"expires_in":86399,"refresh_token":
	 * "bb4a536f-e87b-4759-96ec-8c62c785fb14"
	 * ,"access_token":"35aa1220-3269-4b88-b4bd-738177af93d6"}
	 */
	public void test() throws SAXException, IOException {
		IautoPhoneLoginResultDTO result = super.login();
		IautoConfigDTO configDTO = instance.getIautoConfigDTO();
		IautoGetDeviceUserInfoParamDTO paramDTO = new IautoGetDeviceUserInfoParamDTO();
		paramDTO.setClientId(configDTO.getDeviceClientId());
		paramDTO.setLanguageCode(configDTO.getLanguageCode());
		IautoHeaderDTO headerDTO = new IautoHeaderDTO();
		headerDTO.setIfVersion(configDTO.getIfVersion());
		// <TK>01NWVNkLH6AG0C7JpmKD9NARiNSHJGj1iWv3dD4kbJ3XE=
		headerDTO.setSessionToken(result.getAccessToken());
		IautoGetWebUserInfoService service = new IautoGetWebUserInfoService(
				configDTO, paramDTO, headerDTO);
		try {
			service.service();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		// sessionToken=<TK>01PxD8dT7mfadhhqEYaL6ZYfwnw6HdrkT4EEDuwfyLP6E=&iautoClientId=CarDevice_726ff9b40d40aa1e6e798abc701b4e06&deviceNo=7AB9FAC353FA8F4D234E46DCD11C9F6A&platformVersion=01000000
	}

}
