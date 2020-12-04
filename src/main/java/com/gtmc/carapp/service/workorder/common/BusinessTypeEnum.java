package com.gtmc.carapp.service.workorder.common;

/**
 * 电子签名业务类型
 * 
 * @author huangza1@yonyou.com
 * @date 2020年6月8日
 */
public enum BusinessTypeEnum {

	BUY_CAR_CONTRACT(1, "购车合同","/buyCarContract"),
	BUY_CAR_PROTOCOL(2, "新能源补充协议","/buyCarProtocol"),
	CONSTRUCTION_ORDER(3, "电子施工单","/constructionOrder");


	// 成员变量
	private Integer code;
	private String codeName;
	private String filePath;

	// 构造方法
	private BusinessTypeEnum(Integer code, String codeName, String filePath) {
		this.code = code;
		this.codeName = codeName;
		this.filePath = filePath;
	}

	// 判断合法性
	public static boolean checkCode(Integer code) {
		for (BusinessTypeEnum e : BusinessTypeEnum.values()) {
			if (e.getCode() == code) {
				return true;
			}
		}
		return false;
	}

	// 判断是否相等
	public boolean equalsCode(Integer code) {
		return this.code.equals(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
