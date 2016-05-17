package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.wangfj.product.core.controller.support.base.para.BasePara;

/**
 * 管理界面添加供应商的参数
 * 
 * @Class Name PcmSupplyInfoInAdminPara
 * @Author wangxiang
 * @Create In 2015年7月30日
 */
public class PcmSupplyInfoInAdminPara extends BasePara {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -4724253845255599085L;

	private Long sid;

	@NotNull(message = "{PcmSupplyInfoInAdminPara.supplyName.isNotNull}")
	private String supplyName;// 供应商名称

	private String postcode;// 邮编

	private String city;// 城市

	private String country;// 国家

	private String zone;// 地区代码

	private String shopRegion;// 门店地区（转成文字信息上传，即企业所在地区信息，例如”北京市”）

	private String address;// 企业地址

	private String phone;// 联系电话

	private String fax;// 传真

	private String email;// 邮件

	private String lastOptUser;// 操作人

	@NotNull(message = "{PcmSupplyInfoInAdminPara.shopSid.isNotNull}")
	@Pattern(regexp = "^[0-9]\\d+$", message = "{PcmSupplyInfoInAdminPara.shopSid.isNotNumber}")
	private String shopSid;// 门店sid(组织结构编码)

	@NotNull(message = "{PcmSupplyInfoInAdminPara.supplyCode.isNotNull}")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "{PcmSupplyInfoInAdminPara.supplyCode.formatError}")
	private String supplyCode;// 供应商编码

	@NotNull(message = "{PcmSupplyInfoInAdminPara.supplyType.isNotNull}")
	private Integer supplyType;// 供应商类型:0，门店供应商；1，电商供应商（默认0）

	private String status;// 供应商状态：

	private String shortName;// 供应商简称（别名）

	@NotNull(message = "{PcmSupplyInfoInAdminPara.businessPattern.isNotNull}")
	private Integer businessPattern;// 经营方式

	private String street;// 通讯地址

	private String orgCode;// 企业代码

	private String industry;// 所属行业（转成文字信息上传，即企业所在行业信息，例如”制造业”）

	private String bizCertificateNo;// 营业执照号

	private String taxType;// 纳税类别（门店ERP用的是：1增值税一般纳税人；2：小规模纳税人；3：交纳营业税；4：零税率；5自然人。需要转成文字上传给主数据ERP）

	private String taxNumbe;// 税号

	private String bank;// 银行（开户行名称）

	private String bankNo;// 银行帐号

	private String registeredCapital;// 注册资本

	private String enterpriseProperty;// 企业性质（转成文字信息上传）

	private String businessCategory;// 企业类别（转成文字信息上传）

	private String legalPerson;// 法人代表

	private String legalPersonIcCode;// 法人身份证号

	private String legalPersonContact;// 法人联系方式

	private String agent;// 代理人

	private String agentIcCode;// 代理人身份证号

	private String agentContact;// 代理人联系方式

	private String contact;// 联系人

	private String contactTitle;// 联系人职务

	private String contactIcCode;// 联系人身份证号

	private String contactWay;// 联系人联系方式

	private String businessScope;// 经营范围

	private Integer keySupplier;// 重点供应商 1：是 0：不是

	private String taxRates;// 税率（如果对于17%的税率，传过来的值就是17）

	private String inOutCity;// 市内外（1市内；2省内市外；3国内省外；4国外）转成文字信息上传

	private String admissionDate;// 准入日期，只到日期（yyyymmdd）

	private Integer returnSupply;// 退货至供应商0:不是 1：是

	private String joinSite;// 联营商品客退地点

	private Integer apartOrder;// 拆单标识0: 不是 1：是

	private Integer dropship;// 区分奥莱和其它虚库标识（0:不是 1：是）

	private String erpSupplierCode;// 供应商的门店ERP或者电商ERP编码

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName == null ? null : supplyName.trim();
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode == null ? null : postcode.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country == null ? null : country.trim();
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone == null ? null : zone.trim();
	}

	public String getShopRegion() {
		return shopRegion;
	}

	public void setShopRegion(String shopRegion) {
		this.shopRegion = shopRegion == null ? null : shopRegion.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax == null ? null : fax.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getLastOptUser() {
		return lastOptUser;
	}

	public void setLastOptUser(String lastOptUser) {
		this.lastOptUser = lastOptUser == null ? null : lastOptUser.trim();
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid == null ? null : shopSid.trim();
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode == null ? null : supplyCode.trim();
	}

	public Integer getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Integer supplyType) {
		this.supplyType = supplyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName == null ? null : shortName.trim();
	}

	public Integer getBusinessPattern() {
		return businessPattern;
	}

	public void setBusinessPattern(Integer businessPattern) {
		this.businessPattern = businessPattern;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street == null ? null : street.trim();
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry == null ? null : industry.trim();
	}

	public String getBizCertificateNo() {
		return bizCertificateNo;
	}

	public void setBizCertificateNo(String bizCertificateNo) {
		this.bizCertificateNo = bizCertificateNo == null ? null : bizCertificateNo.trim();
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType == null ? null : taxType.trim();
	}

	public String getTaxNumbe() {
		return taxNumbe;
	}

	public void setTaxNumbe(String taxNumbe) {
		this.taxNumbe = taxNumbe == null ? null : taxNumbe.trim();
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank == null ? null : bank.trim();
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo == null ? null : bankNo.trim();
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital == null ? null : registeredCapital.trim();
	}

	public String getEnterpriseProperty() {
		return enterpriseProperty;
	}

	public void setEnterpriseProperty(String enterpriseProperty) {
		this.enterpriseProperty = enterpriseProperty == null ? null : enterpriseProperty.trim();
	}

	public String getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory == null ? null : businessCategory.trim();
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson == null ? null : legalPerson.trim();
	}

	public String getLegalPersonIcCode() {
		return legalPersonIcCode;
	}

	public void setLegalPersonIcCode(String legalPersonIcCode) {
		this.legalPersonIcCode = legalPersonIcCode == null ? null : legalPersonIcCode.trim();
	}

	public String getLegalPersonContact() {
		return legalPersonContact;
	}

	public void setLegalPersonContact(String legalPersonContact) {
		this.legalPersonContact = legalPersonContact == null ? null : legalPersonContact.trim();
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent == null ? null : agent.trim();
	}

	public String getAgentIcCode() {
		return agentIcCode;
	}

	public void setAgentIcCode(String agentIcCode) {
		this.agentIcCode = agentIcCode == null ? null : agentIcCode.trim();
	}

	public String getAgentContact() {
		return agentContact;
	}

	public void setAgentContact(String agentContact) {
		this.agentContact = agentContact == null ? null : agentContact.trim();
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact == null ? null : contact.trim();
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle == null ? null : contactTitle.trim();
	}

	public String getContactIcCode() {
		return contactIcCode;
	}

	public void setContactIcCode(String contactIcCode) {
		this.contactIcCode = contactIcCode == null ? null : contactIcCode.trim();
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay == null ? null : contactWay.trim();
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope == null ? null : businessScope.trim();
	}

	public Integer getKeySupplier() {
		return keySupplier;
	}

	public void setKeySupplier(Integer keySupplier) {
		this.keySupplier = keySupplier;
	}

	public String getTaxRates() {
		return taxRates;
	}

	public void setTaxRates(String taxRates) {
		this.taxRates = taxRates == null ? null : taxRates.trim();
	}

	public String getInOutCity() {
		return inOutCity;
	}

	public void setInOutCity(String inOutCity) {
		this.inOutCity = inOutCity == null ? null : inOutCity.trim();
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate == null ? null : admissionDate.trim();
	}

	public Integer getReturnSupply() {
		return returnSupply;
	}

	public void setReturnSupply(Integer returnSupply) {
		this.returnSupply = returnSupply;
	}

	public String getJoinSite() {
		return joinSite;
	}

	public void setJoinSite(String joinSite) {
		this.joinSite = joinSite == null ? null : joinSite.trim();
	}

	public Integer getApartOrder() {
		return apartOrder;
	}

	public void setApartOrder(Integer apartOrder) {
		this.apartOrder = apartOrder;
	}

	public Integer getDropship() {
		return dropship;
	}

	public void setDropship(Integer dropship) {
		this.dropship = dropship;
	}

	public String getErpSupplierCode() {
		return erpSupplierCode;
	}

	public void setErpSupplierCode(String erpSupplierCode) {
		this.erpSupplierCode = erpSupplierCode == null ? null : erpSupplierCode.trim();
	}

}
