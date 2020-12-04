package com.gtmc.carapp.service.workorder.common;

/**
 * 电子施工单状态
 * 
 * @author huangza1@yonyou.com
 * @date 2020年5月26日
 */
public enum ConstructionOrderStatusEnum {
	NOT_GENERATE(0, "待生成"),
	NOT_SIGN(1, "待签字"),
	SIGNED_ONLINE(2, "已签字"),
	SIGNED_OFFLINE(3, "线下已签"),
	INVALID(4, "已失效");

	// 成员变量
	private Integer code;
	private String codeName;

	// 构造方法
	private ConstructionOrderStatusEnum(Integer code, String codeName) {
		this.code = code;
		this.codeName = codeName;
	}

	// 判断合法性
	public static boolean checkCode(Integer code) {
		for (ConstructionOrderStatusEnum e : ConstructionOrderStatusEnum.values()) {
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
}
