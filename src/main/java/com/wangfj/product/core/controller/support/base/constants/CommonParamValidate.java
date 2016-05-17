package com.wangfj.product.core.controller.support.base.constants;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 用于接口的 公共校验
 * @author soyona
 *
 */
public class CommonParamValidate {
	public static final String PIS = "PIS";// 导入中端
	public static final String OMS = "OMS";// 订单
	public static final String ACCOUNTCENTER = "ACCOUNTCENTER";// 账户中心
	public static final String SUPPLIERCENTER = "SUPPLIERCENTER";// 供应商中心
	public static final String CUSTOMERCENTER = "CUSTOMERCENTER";// 客服中心
	public static final String OSS = "OSS";// 运营支撑中心
	public static final String EMONEY = "EMONEY";// 电子币中心
	public static final String PAYMENTCAR = "PAYMENTCAR";// 购物车系统
	public static final String PAYMENTPLATFORM = "PAYMENTPLATFORM";// 支付平台系统
	public static final String EFUTURECRM = "EFUTURECRM";// 会员中心
	public static final String EFUTUREPROMOTION = "EFUTUREPROMOTION";// 营销中心
	public static final String TMS = "TMS";// 交付中心
	public static final String PES = "PES";// 商品信息编辑系统
	public static final String WCS = "WCS";// 云店主站
	public static final String MYPROFILE = "MYPROFILE";// 云店个人中心
	public static final String PAD = "PAD";// 移动工作台
	public static final String VCOUNTER = "VCOUNTER";// 虚拟专柜
	public static final String MSHOPPING = "MSHOPPING";// 移动商城
	public static final String MOBILEAPP = "MOBILEAPP";// 移动APP
	public static final String SAPERP = "SAPERP";// 电商ERP
	public static final String EFUTUREERP = "EFUTUREERP";// 门店ERP
	public static final String EDI = "EDI";// 第三方平台EDI
	
	public static final String  OMSADMIN = "OMSADMIN";//订单后台
	public static final String  POS = "POS";//POS
	public static final String  APP = "APP";//移动app平台
	public static final String  WEIXIN = "WEIXIN";//移动app平台
	public static final String  PC = "PC";//网站
	public static final String  LOGISTICS = "LOGISTICS";//物流
	
	public static final String PCM = "PCM";// 第三方平台EDI

	public static final Map fromSystemMap = new HashMap();
	
	static{
		fromSystemMap.put(PIS, PIS);
		fromSystemMap.put(PIS, PIS);
		fromSystemMap.put(OMS, OMS);
		fromSystemMap.put(ACCOUNTCENTER, ACCOUNTCENTER);
		fromSystemMap.put(SUPPLIERCENTER, SUPPLIERCENTER);
		fromSystemMap.put(CUSTOMERCENTER, CUSTOMERCENTER);
		fromSystemMap.put(OSS, OSS);
		fromSystemMap.put(EMONEY, EMONEY);
		fromSystemMap.put(PAYMENTCAR, PAYMENTCAR);
		fromSystemMap.put(PAYMENTPLATFORM, PAYMENTPLATFORM);
		fromSystemMap.put(EFUTURECRM, EFUTURECRM);
		fromSystemMap.put(EFUTUREPROMOTION, EFUTUREPROMOTION);
		fromSystemMap.put(TMS, TMS);
		fromSystemMap.put(PES, PES);
		fromSystemMap.put(WCS, WCS);
		fromSystemMap.put(MYPROFILE, MYPROFILE);
		fromSystemMap.put(PAD, PAD);
		fromSystemMap.put(VCOUNTER, VCOUNTER);
		fromSystemMap.put(MSHOPPING, MSHOPPING);
		fromSystemMap.put(MOBILEAPP, MOBILEAPP);
		fromSystemMap.put(SAPERP, SAPERP);
		fromSystemMap.put(EFUTUREERP, EFUTUREERP);
		fromSystemMap.put(EDI, EDI);
		fromSystemMap.put(PCM, PCM);
		
		fromSystemMap.put(OMSADMIN, OMSADMIN);
		fromSystemMap.put(POS, POS);
		fromSystemMap.put(APP, APP);
		fromSystemMap.put(WEIXIN, WEIXIN);
		fromSystemMap.put(PC, PC);
		fromSystemMap.put(LOGISTICS, LOGISTICS);
	}
	public static boolean valiateResult(String requestJson){
		String result = (String) fromSystemMap.get(requestJson);
		if(StringUtils.isNotBlank(result)){
			return true;
		}
		return false;
	}
	
}
