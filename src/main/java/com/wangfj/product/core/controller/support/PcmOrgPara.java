package com.wangfj.product.core.controller.support;

public class PcmOrgPara {

    private Long sid;

    private String parentSid;/* 所属上级sid */

    private Long groupSid;/* 所属集团sid */

    private String organizationCode;/* 机构编码 */

    private String organizationName;/* 机构名称 */

    private Integer organizationType;/* 机构类别 */

    private Integer organizationStatus;/* 机构状态 */

    private Integer storeType;/* 门店类型 */

    private String shippingPoint;/* 集货地点编码 */

    private String areaCode;/* 门店所属城市在省市区字典中的编码 */

    private String createName; /* 创建人 */

    private String updateName; /* 修改人 */

    private String actionCode;

    /**
     * 门店信息(新加表字段)
     */
    private String registeredAddress;//注册地址

    private String postCode;//邮编

    private String legalRepresentative;//法定代表人

    private String agent;//委托代理人

    private String taxRegistrationNumber;//税务登记号

    private String bank;//开户行

    private String bankAccount;//开户行账号

    private String telephoneNumber;//电话(区号+座机)

    private String faxNumber;//传真(区号+座机)

    private String field1;//预留字段

    private String field2;//预留字段

    private String field3;//预留字段

    private String field4;//预留字段

    private String field5;//预留字段

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getParentSid() {
        return parentSid;
    }

    public void setParentSid(String parentSid) {
        this.parentSid = parentSid;
    }

    public Long getGroupSid() {
        return groupSid;
    }

    public void setGroupSid(Long groupSid) {
        this.groupSid = groupSid;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(Integer organizationType) {
        this.organizationType = organizationType;
    }

    public Integer getOrganizationStatus() {
        return organizationStatus;
    }

    public void setOrganizationStatus(Integer organizationStatus) {
        this.organizationStatus = organizationStatus;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getShippingPoint() {
        return shippingPoint;
    }

    public void setShippingPoint(String shippingPoint) {
        this.shippingPoint = shippingPoint;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    @Override
    public String toString() {
        return "PcmOrgPara{" +
                "sid=" + sid +
                ", parentSid='" + parentSid + '\'' +
                ", groupSid=" + groupSid +
                ", organizationCode='" + organizationCode + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", organizationType=" + organizationType +
                ", organizationStatus=" + organizationStatus +
                ", storeType=" + storeType +
                ", shippingPoint='" + shippingPoint + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", createName='" + createName + '\'' +
                ", updateName='" + updateName + '\'' +
                ", actionCode='" + actionCode + '\'' +
                ", registeredAddress='" + registeredAddress + '\'' +
                ", postCode='" + postCode + '\'' +
                ", legalRepresentative='" + legalRepresentative + '\'' +
                ", agent='" + agent + '\'' +
                ", taxRegistrationNumber='" + taxRegistrationNumber + '\'' +
                ", bank='" + bank + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", faxNumber='" + faxNumber + '\'' +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                ", field4='" + field4 + '\'' +
                ", field5='" + field5 + '\'' +
                '}';
    }
}
