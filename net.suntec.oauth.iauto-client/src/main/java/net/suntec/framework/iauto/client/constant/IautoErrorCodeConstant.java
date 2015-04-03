package net.suntec.framework.iauto.client.constant;

public interface IautoErrorCodeConstant {
	/******************* IAUTO exception *********************/
	// COMMON
	int IT_ERR = 3001;
	int IT_ERR_CONN = 3002;
	int IT_ERR_NO_CODE = 3003;

	// LOGIN
	int IT_LG_ERR = 3101;
	int IT_LG_ERR_PARAM = 3102;

	// Get User Info
	int IT_GUI_ERR_PARAM = 3201;
	int IT_GUI_ERR_NO_SESSIONTOKEN = 3202;
	int IT_GUI_ERR_TOKEN_EXPIRE = 3203;
	int IT_GUI_DEVICE_ERR_NO_CLIENTID = 3211;
	int IT_GUI_DEVICE_ERR_NO_DEVICENO = 3212;
	int IT_GUI_DEVICE_ERR_NO_IF = 3213;

}
