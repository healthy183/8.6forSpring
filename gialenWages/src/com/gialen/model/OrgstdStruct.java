package com.gialen.model;

import java.util.HashSet;
import java.util.Set;


/**
 * OrgstdStruct entity. @author MyEclipse Persistence Tools
 */

public class OrgstdStruct  implements java.io.Serializable {


    // Fields    
     private String unitid;
     private OrgstdStruct orgstdStruct;
     private  Psnaccount psnaccount;
    // private String director;
     private String oid;
     private String label;
     private Integer labelLength;
     private Integer unitindex;
     private Integer workers;
     private Integer belongworkers;
     private String unitcode;
     private String unitname;
     private Integer istempunit;
     private String creator;
     private String createdate;
     private String killer;
     private String killdate;
     private String lastsavedate;
     private String postcode;
     private String unitduty;
     private String unitgrade;
     private Integer iscorecenter;
     private String corecenterinfo;
     private String islegal;
     private String legalinfo;
     private String fax;
    
     private String address;
     private String assistant;
     private String unitlevel;
     private Integer ismanageunit;
     private String unittype;
     private String manageunit;
     private String editionId;
     private String labelindex;
     private Integer isdelete;
     private String remark;
     private String cpccharacter;
     private String item1;
     private String item2;
     private String item3;
     private String item4;
     private String item5;
     private String item6;
     private String item7;
     private String item8;
     private String item9;
     private String item10;
     private Integer item11;
     private Integer item12;
     private Integer item13;
     private Integer item14;
     private Integer item15;
     private Integer item16;
     private Integer item17;
     private Integer item18;
     private Integer item19;
     private Integer item20;
     private String legalEntity;
     private String unitenname;
     private String costcenterid;
     private String effectdate;
     private String stopdate;
     private String stopoperationdate;
     private String stopuserid;
     //private Set storeManagersellAwards = new HashSet(0);
    
     private Set<OrgstdStruct> orgstdStructs = new HashSet<OrgstdStruct>(0);
 	 //private Set<PlanMoney> planMoneies = new HashSet<PlanMoney>(0);//·å×¢ÊÍ
 	 private Set<Psnaccount> psnaccounts = new HashSet<Psnaccount>(0);
     private Set<Corresponding> correspondings = new HashSet<Corresponding>(0);
     private Set<StoreCount> storeCounts = new HashSet<StoreCount>(0);
     private Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks = new HashSet<PsnaccountMonthProjectDetailsLink>(0);
 	
     private Set<LastCountTable> lastCountTables = new HashSet<LastCountTable>(0);
     
     
   //10 11Ìí¼Ó
 	private Set<CollectUsrWages> collectUsrWageses = new HashSet<CollectUsrWages>(0);
     
     
    // Constructors

    public Set<CollectUsrWages> getCollectUsrWageses() {
		return collectUsrWageses;
	}

	public void setCollectUsrWageses(Set<CollectUsrWages> collectUsrWageses) {
		this.collectUsrWageses = collectUsrWageses;
	}

	public Set<PsnaccountMonthProjectDetailsLink> getPsnaccountMonthProjectDetailsLinks() {
		return psnaccountMonthProjectDetailsLinks;
	}

	public void setPsnaccountMonthProjectDetailsLinks(
			Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks) {
		this.psnaccountMonthProjectDetailsLinks = psnaccountMonthProjectDetailsLinks;
	}

	/** default constructor */
    public OrgstdStruct() {
    }

	/** minimal constructor */
    public OrgstdStruct(String unitid, String costcenterid) {
        this.unitid = unitid;
        this.costcenterid = costcenterid;
    }
    
	


	public OrgstdStruct(String unitid, OrgstdStruct orgstdStruct,
			Psnaccount psnaccount, String oid, String label,
			Integer labelLength, Integer unitindex, Integer workers,
			Integer belongworkers, String unitcode, String unitname,
			Integer istempunit, String creator, String createdate,
			String killer, String killdate, String lastsavedate,
			String postcode, String unitduty, String unitgrade,
			Integer iscorecenter, String corecenterinfo, String islegal,
			String legalinfo, String fax, String address, String assistant,
			String unitlevel, Integer ismanageunit, String unittype,
			String manageunit, String editionId, String labelindex,
			Integer isdelete, String remark, String cpccharacter, String item1,
			String item2, String item3, String item4, String item5,
			String item6, String item7, String item8, String item9,
			String item10, Integer item11, Integer item12, Integer item13,
			Integer item14, Integer item15, Integer item16, Integer item17,
			Integer item18, Integer item19, Integer item20, String legalEntity,
			String unitenname, String costcenterid, String effectdate,
			String stopdate, String stopoperationdate, String stopuserid,
			Set<OrgstdStruct> orgstdStructs, Set<Psnaccount> psnaccounts,
			Set<Corresponding> correspondings, Set<StoreCount> storeCounts) {
		super();
		this.unitid = unitid;
		this.orgstdStruct = orgstdStruct;
		this.psnaccount = psnaccount;
		this.oid = oid;
		this.label = label;
		this.labelLength = labelLength;
		this.unitindex = unitindex;
		this.workers = workers;
		this.belongworkers = belongworkers;
		this.unitcode = unitcode;
		this.unitname = unitname;
		this.istempunit = istempunit;
		this.creator = creator;
		this.createdate = createdate;
		this.killer = killer;
		this.killdate = killdate;
		this.lastsavedate = lastsavedate;
		this.postcode = postcode;
		this.unitduty = unitduty;
		this.unitgrade = unitgrade;
		this.iscorecenter = iscorecenter;
		this.corecenterinfo = corecenterinfo;
		this.islegal = islegal;
		this.legalinfo = legalinfo;
		this.fax = fax;
		this.address = address;
		this.assistant = assistant;
		this.unitlevel = unitlevel;
		this.ismanageunit = ismanageunit;
		this.unittype = unittype;
		this.manageunit = manageunit;
		this.editionId = editionId;
		this.labelindex = labelindex;
		this.isdelete = isdelete;
		this.remark = remark;
		this.cpccharacter = cpccharacter;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
		this.item6 = item6;
		this.item7 = item7;
		this.item8 = item8;
		this.item9 = item9;
		this.item10 = item10;
		this.item11 = item11;
		this.item12 = item12;
		this.item13 = item13;
		this.item14 = item14;
		this.item15 = item15;
		this.item16 = item16;
		this.item17 = item17;
		this.item18 = item18;
		this.item19 = item19;
		this.item20 = item20;
		this.legalEntity = legalEntity;
		this.unitenname = unitenname;
		this.costcenterid = costcenterid;
		this.effectdate = effectdate;
		this.stopdate = stopdate;
		this.stopoperationdate = stopoperationdate;
		this.stopuserid = stopuserid;
		this.orgstdStructs = orgstdStructs;
		this.psnaccounts = psnaccounts;
		this.correspondings = correspondings;
		this.storeCounts = storeCounts;
	}
	
	public Psnaccount getPsnaccount() {
		return psnaccount;
	}

	public void setPsnaccount(Psnaccount psnaccount) {
		this.psnaccount = psnaccount;
	}

	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public OrgstdStruct getOrgstdStruct() {
		return orgstdStruct;
	}

	public void setOrgstdStruct(OrgstdStruct orgstdStruct) {
		this.orgstdStruct = orgstdStruct;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getLabelLength() {
		return labelLength;
	}

	public void setLabelLength(Integer labelLength) {
		this.labelLength = labelLength;
	}

	public Integer getUnitindex() {
		return unitindex;
	}

	public void setUnitindex(Integer unitindex) {
		this.unitindex = unitindex;
	}

	public Integer getWorkers() {
		return workers;
	}

	public void setWorkers(Integer workers) {
		this.workers = workers;
	}

	public Integer getBelongworkers() {
		return belongworkers;
	}

	public void setBelongworkers(Integer belongworkers) {
		this.belongworkers = belongworkers;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public Integer getIstempunit() {
		return istempunit;
	}

	public void setIstempunit(Integer istempunit) {
		this.istempunit = istempunit;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getKiller() {
		return killer;
	}

	public void setKiller(String killer) {
		this.killer = killer;
	}

	public String getKilldate() {
		return killdate;
	}

	public void setKilldate(String killdate) {
		this.killdate = killdate;
	}

	public String getLastsavedate() {
		return lastsavedate;
	}

	public void setLastsavedate(String lastsavedate) {
		this.lastsavedate = lastsavedate;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getUnitduty() {
		return unitduty;
	}

	public void setUnitduty(String unitduty) {
		this.unitduty = unitduty;
	}

	public String getUnitgrade() {
		return unitgrade;
	}

	public void setUnitgrade(String unitgrade) {
		this.unitgrade = unitgrade;
	}

	public Integer getIscorecenter() {
		return iscorecenter;
	}

	public void setIscorecenter(Integer iscorecenter) {
		this.iscorecenter = iscorecenter;
	}

	public String getCorecenterinfo() {
		return corecenterinfo;
	}

	public void setCorecenterinfo(String corecenterinfo) {
		this.corecenterinfo = corecenterinfo;
	}

	public String getIslegal() {
		return islegal;
	}

	public void setIslegal(String islegal) {
		this.islegal = islegal;
	}

	public String getLegalinfo() {
		return legalinfo;
	}

	public void setLegalinfo(String legalinfo) {
		this.legalinfo = legalinfo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAssistant() {
		return assistant;
	}

	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}

	public String getUnitlevel() {
		return unitlevel;
	}

	public void setUnitlevel(String unitlevel) {
		this.unitlevel = unitlevel;
	}

	public Integer getIsmanageunit() {
		return ismanageunit;
	}

	public void setIsmanageunit(Integer ismanageunit) {
		this.ismanageunit = ismanageunit;
	}

	public String getUnittype() {
		return unittype;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

	public String getManageunit() {
		return manageunit;
	}

	public void setManageunit(String manageunit) {
		this.manageunit = manageunit;
	}

	public String getEditionId() {
		return editionId;
	}

	public void setEditionId(String editionId) {
		this.editionId = editionId;
	}

	public String getLabelindex() {
		return labelindex;
	}

	public void setLabelindex(String labelindex) {
		this.labelindex = labelindex;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCpccharacter() {
		return cpccharacter;
	}

	public void setCpccharacter(String cpccharacter) {
		this.cpccharacter = cpccharacter;
	}

	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}

	public String getItem3() {
		return item3;
	}

	public void setItem3(String item3) {
		this.item3 = item3;
	}

	public String getItem4() {
		return item4;
	}

	public void setItem4(String item4) {
		this.item4 = item4;
	}

	public String getItem5() {
		return item5;
	}

	public void setItem5(String item5) {
		this.item5 = item5;
	}

	public String getItem6() {
		return item6;
	}

	public void setItem6(String item6) {
		this.item6 = item6;
	}

	public String getItem7() {
		return item7;
	}

	public void setItem7(String item7) {
		this.item7 = item7;
	}

	public String getItem8() {
		return item8;
	}

	public void setItem8(String item8) {
		this.item8 = item8;
	}

	public String getItem9() {
		return item9;
	}

	public void setItem9(String item9) {
		this.item9 = item9;
	}

	public String getItem10() {
		return item10;
	}

	public void setItem10(String item10) {
		this.item10 = item10;
	}

	public Integer getItem11() {
		return item11;
	}

	public void setItem11(Integer item11) {
		this.item11 = item11;
	}

	public Integer getItem12() {
		return item12;
	}

	public void setItem12(Integer item12) {
		this.item12 = item12;
	}

	public Integer getItem13() {
		return item13;
	}

	public void setItem13(Integer item13) {
		this.item13 = item13;
	}

	public Integer getItem14() {
		return item14;
	}

	public void setItem14(Integer item14) {
		this.item14 = item14;
	}

	public Integer getItem15() {
		return item15;
	}

	public void setItem15(Integer item15) {
		this.item15 = item15;
	}

	public Integer getItem16() {
		return item16;
	}

	public void setItem16(Integer item16) {
		this.item16 = item16;
	}

	public Integer getItem17() {
		return item17;
	}

	public void setItem17(Integer item17) {
		this.item17 = item17;
	}

	public Integer getItem18() {
		return item18;
	}

	public void setItem18(Integer item18) {
		this.item18 = item18;
	}

	public Integer getItem19() {
		return item19;
	}

	public void setItem19(Integer item19) {
		this.item19 = item19;
	}

	public Integer getItem20() {
		return item20;
	}

	public void setItem20(Integer item20) {
		this.item20 = item20;
	}

	public String getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}

	public String getUnitenname() {
		return unitenname;
	}

	public void setUnitenname(String unitenname) {
		this.unitenname = unitenname;
	}

	public String getCostcenterid() {
		return costcenterid;
	}

	public void setCostcenterid(String costcenterid) {
		this.costcenterid = costcenterid;
	}

	public String getEffectdate() {
		return effectdate;
	}

	public void setEffectdate(String effectdate) {
		this.effectdate = effectdate;
	}

	public String getStopdate() {
		return stopdate;
	}

	public void setStopdate(String stopdate) {
		this.stopdate = stopdate;
	}

	public String getStopoperationdate() {
		return stopoperationdate;
	}

	public void setStopoperationdate(String stopoperationdate) {
		this.stopoperationdate = stopoperationdate;
	}

	public String getStopuserid() {
		return stopuserid;
	}

	public void setStopuserid(String stopuserid) {
		this.stopuserid = stopuserid;
	}

	public Set<OrgstdStruct> getOrgstdStructs() {
		return orgstdStructs;
	}

	public void setOrgstdStructs(Set<OrgstdStruct> orgstdStructs) {
		this.orgstdStructs = orgstdStructs;
	}

	public Set<Psnaccount> getPsnaccounts() {
		return psnaccounts;
	}

	public void setPsnaccounts(Set<Psnaccount> psnaccounts) {
		this.psnaccounts = psnaccounts;
	}

	public Set<Corresponding> getCorrespondings() {
		return correspondings;
	}

	public void setCorrespondings(Set<Corresponding> correspondings) {
		this.correspondings = correspondings;
	}

	public Set<StoreCount> getStoreCounts() {
		return storeCounts;
	}

	public void setStoreCounts(Set<StoreCount> storeCounts) {
		this.storeCounts = storeCounts;
	}

	public Set<LastCountTable> getLastCountTables() {
		return lastCountTables;
	}

	public void setLastCountTables(Set<LastCountTable> lastCountTables) {
		this.lastCountTables = lastCountTables;
	}

    
    
    
    
	// Property accessors

   /* public Set getPlanMoneies() {
		return planMoneies;
	}

	public void setPlanMoneies(Set planMoneies) {
		this.planMoneies = planMoneies;
	}*/

	





}