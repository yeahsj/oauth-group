package net.suntec.framework.iauto.client.service;

import java.io.IOException;

import net.suntec.framework.iauto.client.dto.IautoConfigDTO;
import net.suntec.framework.iauto.client.dto.IautoHeaderDTO;
import net.suntec.framework.iauto.client.dto.param.IautoGetDeviceUserInfoParamDTO;
import net.suntec.framework.iauto.client.dto.result.IautoPhoneLoginResultDTO;

import org.xml.sax.SAXException;

public class IautoGetPhoneUserInfoServiceTest extends
		IautoPhoneLoginServiceTest {
	/**
	 * response body : {"code":1000,"expires_in":86399,"refresh_token":
	 * "bb4a536f-e87b-4759-96ec-8c62c785fb14"
	 * ,"access_token":"35aa1220-3269-4b88-b4bd-738177af93d6"}
	 */
	public void test() throws SAXException, IOException {
		IautoPhoneLoginResultDTO result = super.login();
		IautoConfigDTO configDTO = instance.getIautoConfigDTO();
		IautoGetDeviceUserInfoParamDTO paramDTO = new IautoGetDeviceUserInfoParamDTO();
		paramDTO.setClientId(configDTO.getPhoneClientId());
		paramDTO.setLanguageCode(configDTO.getLanguageCode());
		IautoHeaderDTO headerDTO = new IautoHeaderDTO();
		headerDTO.setIfVersion(configDTO.getIfVersion());
		headerDTO.setSessionToken(result.getAccessToken());
		IautoGetPhoneUserInfoService service = new IautoGetPhoneUserInfoService(
				configDTO, paramDTO, headerDTO);
		try {
			service.service();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}
}
