package net.suntec.framework.iauto.client.service;

import java.io.IOException;

import net.suntec.framework.iauto.client.IautoTest;
import net.suntec.framework.iauto.client.dto.IautoConfigDTO;
import net.suntec.framework.iauto.client.dto.IautoHeaderDTO;
import net.suntec.framework.iauto.client.dto.param.IautoPhoneLoginParamDTO;
import net.suntec.framework.iauto.client.dto.result.IautoPhoneLoginResultDTO;

import org.xml.sax.SAXException;

public class IautoDeviceLoginServiceTest extends IautoTest {
	public IautoPhoneLoginResultDTO login() throws SAXException, IOException {
		IautoPhoneLoginResultDTO result = null;
		IautoConfigDTO configDTO = instance.getIautoConfigDTO();
		IautoPhoneLoginParamDTO paramDTO = new IautoPhoneLoginParamDTO();
		paramDTO.setClientId(configDTO.getDeviceClientId());
		paramDTO.setClientSercet(configDTO.getDeviceClientSercet());
		paramDTO.setGrantType("password");
		paramDTO.setUsername("zpf");
		paramDTO.setPassword("111111");
		
		IautoHeaderDTO headerDTO = new IautoHeaderDTO();

		IautoLoginService iautoDeviceLoginService = new IautoDeviceLoginService(
				configDTO, paramDTO, headerDTO);
		iautoDeviceLoginService.service();
		if (iautoDeviceLoginService.isSuccess()) {
			result = iautoDeviceLoginService.getResult();
			logger.info(result.getAccessToken());
		} else {
			logger.info(iautoDeviceLoginService.getErrMsg());
		}

		return result;
	}

	public void test() throws SAXException, IOException {
		login();
	}
}
