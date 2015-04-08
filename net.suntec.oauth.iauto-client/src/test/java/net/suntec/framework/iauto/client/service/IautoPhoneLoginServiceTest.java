package net.suntec.framework.iauto.client.service;

import java.io.IOException;

import net.suntec.framework.iauto.client.IautoTest;
import net.suntec.framework.iauto.client.dto.IautoConfigDTO;
import net.suntec.framework.iauto.client.dto.IautoHeaderDTO;
import net.suntec.framework.iauto.client.dto.param.IautoPhoneLoginParamDTO;
import net.suntec.framework.iauto.client.dto.result.IautoPhoneLoginResultDTO;

import org.xml.sax.SAXException;

public class IautoPhoneLoginServiceTest extends IautoTest {

	public IautoPhoneLoginResultDTO login() {
		IautoPhoneLoginResultDTO result = null;
		IautoConfigDTO configDTO = instance.getIautoConfigDTO();
		IautoPhoneLoginParamDTO paramDTO = new IautoPhoneLoginParamDTO();
		paramDTO.setClientId(configDTO.getPhoneClientId());
		paramDTO.setClientSercet(configDTO.getPhoneClientSercet());
		paramDTO.setGrantType("password");
		paramDTO.setUsername("zpf");
		paramDTO.setPassword("111111");
		paramDTO.setDeviceNo("h24ci19j2dh29bhf");
		IautoHeaderDTO headerDTO = new IautoHeaderDTO();

		IautoPhoneLoginService iautoDeviceLoginService = new IautoPhoneLoginService(
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
