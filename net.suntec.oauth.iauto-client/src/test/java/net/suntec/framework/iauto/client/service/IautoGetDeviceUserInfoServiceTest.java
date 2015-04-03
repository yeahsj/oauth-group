package net.suntec.framework.iauto.client.service;

import java.io.IOException;

import net.suntec.framework.iauto.client.dto.IautoConfigDTO;
import net.suntec.framework.iauto.client.dto.IautoHeaderDTO;
import net.suntec.framework.iauto.client.dto.IautoUserDTO;
import net.suntec.framework.iauto.client.dto.param.IautoGetDeviceUserInfoParamDTO;
import net.suntec.framework.iauto.client.dto.result.IautoPhoneLoginResultDTO;

import org.xml.sax.SAXException;

public class IautoGetDeviceUserInfoServiceTest extends
		IautoDeviceLoginServiceTest {

	public IautoUserDTO getUserInfo() throws SAXException, IOException {
		IautoPhoneLoginResultDTO result = super.login();
		IautoConfigDTO configDTO = instance.getIautoConfigDTO();
		IautoGetDeviceUserInfoParamDTO paramDTO = new IautoGetDeviceUserInfoParamDTO();
		paramDTO.setClientId(configDTO.getDeviceClientId());
		paramDTO.setLanguageCode(configDTO.getLanguageCode());
		// paramDTO.setDeviceNo("7AB9FAC353FA8F4D234E46DCD11C9F6A");
		paramDTO.setDeviceNo("7ADF1323450F5F364A64A4EA4BCF3EE4");
		paramDTO.setPlatformVersion("01000000");
		IautoHeaderDTO headerDTO = new IautoHeaderDTO();
		headerDTO.setIfVersion(configDTO.getIfVersion());
		// deviceNo=
		// CarDevice_726ff9b40d40aa1e6e798abc701b4e06
		headerDTO.setSessionToken(result.getAccessToken());
		IautoGetDeviceUserInfoService service = new IautoGetDeviceUserInfoService(
				configDTO, paramDTO, headerDTO);
		try {
			service.service();
			return service.getResult();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

	/**
	 * response body : {"code":1000,"expires_in":86399,"refresh_token":
	 * "bb4a536f-e87b-4759-96ec-8c62c785fb14"
	 * ,"access_token":"35aa1220-3269-4b88-b4bd-738177af93d6"}
	 */
	public void test() throws SAXException, IOException {
		getUserInfo();
	}
}
