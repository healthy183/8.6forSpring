package com.gialen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Psnaccount entity. @author MyEclipse Persistence Tools
 */

public class Psnaccount implements java.io.Serializable {

	// Fields

	private String personid;
	private Cpcjobcode cpcjobcode;
	private OrgstdStruct orgstdStruct;
	private Set<OrgstdStruct> orgstdStructs = new HashSet<OrgstdStruct>();
	private String cardnum;
	private String truename;
	private String certificatetypeid;
	private String certificatenumber;
	private Integer everinourcorp;
	private Integer accessionstate;
	private String firstname;
	private String middlename;
	private String lastname;
	private String companyemail;
	private String dateofbirth;
	private Integer gender;
	private String homeplace;
	private String nationalityid;
	private String secondnationalityid;
	private String marriageid;
	private String settleat;
	private String graduateschoolid;
	private String photoid;
	private String lastupdatetime;
	private Integer nopaystatus;
	private String mainaccstatus;
	private Integer ifmainnote;
	private String importlabel;
	private String educationallevelid;
	private String belongcorpid;
	private String positionid;
	private String responsibilityid;
	private String titleid;
	private String rankid;
	private String gradeid;
	private String jobseriesid;
	private String arrangementid;
	private String jobtypeid;
	private String employeeid;
	private String serviceseniority;
	private String startservicedate;
	private String attendondate;
	private String nativeplacepropertyid;
	private String employeetypeid;
	private Integer dlidl;
	private String probationenddate;
	private String lastadjustupdatetime;
	private String employeecharid;
	private String origemployeeid;
	private Integer istalent;
	private Integer isregulareemployee;
	private Integer isinnerretire;
	private String jobcharacter;
	private String probationmaturedate;
	private String societyensurenum;
	private String entersocietydate;
	private String firstattenddate;
	private Integer isassignhouse;
	private String assignhousedate;
	private String remark;
	private String accitem1;
	private String accitem2;
	private String accitem3;
	private String accitem4;
	private String accitem5;
	private String accitem6;
	private String accitem7;
	private String accitem8;
	private Integer regularstate;
	private Integer isadjustdel;
	private String dimissiondate;
	private String immigrationday;
	private Integer isexpatriate;
	private String expinunitid;
	private Integer expatriatestatus;
	private String expinbusinessunitid;
	private String expinbelongcorp;
	private String expinunitlabel;
	private Integer fostered;
	private String areaid;
	private String willLeaveRange;
	private String flowerEmpId;
	private String twinsuranceidentitytypeid;
	private String twlsaidentitytypeid;
	private String workyears;
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

	public Set<LastCountTable> getLastCountTables() {
		return lastCountTables;
	}

	public void setLastCountTables(Set<LastCountTable> lastCountTables) {
		this.lastCountTables = lastCountTables;
	}

	public Set<PsnaccountMonthProjectDetailsLink> getPsnaccountMonthProjectDetailsLinks() {
		return psnaccountMonthProjectDetailsLinks;
	}

	public void setPsnaccountMonthProjectDetailsLinks(
			Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks) {
		this.psnaccountMonthProjectDetailsLinks = psnaccountMonthProjectDetailsLinks;
	}

	/** default constructor */
	public Psnaccount() {
	}

	public Psnaccount(String personid, String employeeid) {
		super();
		this.personid = personid;
		this.employeeid = employeeid;
	}

	/** minimal constructor */
	public Psnaccount(Integer isexpatriate, Integer expatriatestatus,
			String areaid) {
		this.isexpatriate = isexpatriate;
		this.expatriatestatus = expatriatestatus;
		this.areaid = areaid;
	}
	
	

	/** full constructor */
	public Psnaccount(Cpcjobcode cpcjobcode, OrgstdStruct orgstdStruct,
			String cardnum, String truename, String certificatetypeid,
			String certificatenumber, Integer everinourcorp,
			Integer accessionstate, String firstname, String middlename,
			String lastname, String companyemail, String dateofbirth,
			Integer gender, String homeplace, String nationalityid,
			String secondnationalityid, String marriageid, String settleat,
			String graduateschoolid, String photoid, String lastupdatetime,
			Integer nopaystatus, String mainaccstatus, Integer ifmainnote,
			String importlabel, String educationallevelid, String belongcorpid,
			String positionid, String responsibilityid, String titleid,
			String rankid, String gradeid, String jobseriesid,
			String arrangementid, String jobtypeid, String employeeid,
			String serviceseniority, String startservicedate,
			String attendondate, String nativeplacepropertyid,
			String employeetypeid, Integer dlidl, String probationenddate,
			String lastadjustupdatetime, String employeecharid,
			String origemployeeid, Integer istalent,
			Integer isregulareemployee, Integer isinnerretire,
			String jobcharacter, String probationmaturedate,
			String societyensurenum, String entersocietydate,
			String firstattenddate, Integer isassignhouse,
			String assignhousedate, String remark, String accitem1,
			String accitem2, String accitem3, String accitem4, String accitem5,
			String accitem6, String accitem7, String accitem8,
			Integer regularstate, Integer isadjustdel, String dimissiondate,
			String immigrationday, Integer isexpatriate, String expinunitid,
			Integer expatriatestatus, String expinbusinessunitid,
			String expinbelongcorp, String expinunitlabel, Integer fostered,
			String areaid, String willLeaveRange, String flowerEmpId,
			String twinsuranceidentitytypeid, String twlsaidentitytypeid,
			String workyears) {
		this.cpcjobcode = cpcjobcode;
		this.orgstdStruct = orgstdStruct;
		this.cardnum = cardnum;
		this.truename = truename;
		this.certificatetypeid = certificatetypeid;
		this.certificatenumber = certificatenumber;
		this.everinourcorp = everinourcorp;
		this.accessionstate = accessionstate;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.companyemail = companyemail;
		this.dateofbirth = dateofbirth;
		this.gender = gender;
		this.homeplace = homeplace;
		this.nationalityid = nationalityid;
		this.secondnationalityid = secondnationalityid;
		this.marriageid = marriageid;
		this.settleat = settleat;
		this.graduateschoolid = graduateschoolid;
		this.photoid = photoid;
		this.lastupdatetime = lastupdatetime;
		this.nopaystatus = nopaystatus;
		this.mainaccstatus = mainaccstatus;
		this.ifmainnote = ifmainnote;
		this.importlabel = importlabel;
		this.educationallevelid = educationallevelid;
		this.belongcorpid = belongcorpid;
		this.positionid = positionid;
		this.responsibilityid = responsibilityid;
		this.titleid = titleid;
		this.rankid = rankid;
		this.gradeid = gradeid;
		this.jobseriesid = jobseriesid;
		this.arrangementid = arrangementid;
		this.jobtypeid = jobtypeid;
		this.employeeid = employeeid;
		this.serviceseniority = serviceseniority;
		this.startservicedate = startservicedate;
		this.attendondate = attendondate;
		this.nativeplacepropertyid = nativeplacepropertyid;
		this.employeetypeid = employeetypeid;
		this.dlidl = dlidl;
		this.probationenddate = probationenddate;
		this.lastadjustupdatetime = lastadjustupdatetime;
		this.employeecharid = employeecharid;
		this.origemployeeid = origemployeeid;
		this.istalent = istalent;
		this.isregulareemployee = isregulareemployee;
		this.isinnerretire = isinnerretire;
		this.jobcharacter = jobcharacter;
		this.probationmaturedate = probationmaturedate;
		this.societyensurenum = societyensurenum;
		this.entersocietydate = entersocietydate;
		this.firstattenddate = firstattenddate;
		this.isassignhouse = isassignhouse;
		this.assignhousedate = assignhousedate;
		this.remark = remark;
		this.accitem1 = accitem1;
		this.accitem2 = accitem2;
		this.accitem3 = accitem3;
		this.accitem4 = accitem4;
		this.accitem5 = accitem5;
		this.accitem6 = accitem6;
		this.accitem7 = accitem7;
		this.accitem8 = accitem8;
		this.regularstate = regularstate;
		this.isadjustdel = isadjustdel;
		this.dimissiondate = dimissiondate;
		this.immigrationday = immigrationday;
		this.isexpatriate = isexpatriate;
		this.expinunitid = expinunitid;
		this.expatriatestatus = expatriatestatus;
		this.expinbusinessunitid = expinbusinessunitid;
		this.expinbelongcorp = expinbelongcorp;
		this.expinunitlabel = expinunitlabel;
		this.fostered = fostered;
		this.areaid = areaid;
		this.willLeaveRange = willLeaveRange;
		this.flowerEmpId = flowerEmpId;
		this.twinsuranceidentitytypeid = twinsuranceidentitytypeid;
		this.twlsaidentitytypeid = twlsaidentitytypeid;
		this.workyears = workyears;
	}

	// Property accessors

	public String getPersonid() {
		return this.personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public Cpcjobcode getCpcjobcode() {
		return this.cpcjobcode;
	}

	public void setCpcjobcode(Cpcjobcode cpcjobcode) {
		this.cpcjobcode = cpcjobcode;
	}

	public OrgstdStruct getOrgstdStruct() {
		return this.orgstdStruct;
	}

	public void setOrgstdStruct(OrgstdStruct orgstdStruct) {
		this.orgstdStruct = orgstdStruct;
	}

	public String getCardnum() {
		return this.cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getTruename() {
		return this.truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getCertificatetypeid() {
		return this.certificatetypeid;
	}

	public void setCertificatetypeid(String certificatetypeid) {
		this.certificatetypeid = certificatetypeid;
	}

	public String getCertificatenumber() {
		return this.certificatenumber;
	}

	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}

	public Integer getEverinourcorp() {
		return this.everinourcorp;
	}

	public void setEverinourcorp(Integer everinourcorp) {
		this.everinourcorp = everinourcorp;
	}

	public Integer getAccessionstate() {
		return this.accessionstate;
	}

	public void setAccessionstate(Integer accessionstate) {
		this.accessionstate = accessionstate;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCompanyemail() {
		return this.companyemail;
	}

	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}

	public String getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getHomeplace() {
		return this.homeplace;
	}

	public void setHomeplace(String homeplace) {
		this.homeplace = homeplace;
	}

	public String getNationalityid() {
		return this.nationalityid;
	}

	public void setNationalityid(String nationalityid) {
		this.nationalityid = nationalityid;
	}

	public String getSecondnationalityid() {
		return this.secondnationalityid;
	}

	public void setSecondnationalityid(String secondnationalityid) {
		this.secondnationalityid = secondnationalityid;
	}

	public String getMarriageid() {
		return this.marriageid;
	}

	public void setMarriageid(String marriageid) {
		this.marriageid = marriageid;
	}

	public String getSettleat() {
		return this.settleat;
	}

	public void setSettleat(String settleat) {
		this.settleat = settleat;
	}

	public String getGraduateschoolid() {
		return this.graduateschoolid;
	}

	public void setGraduateschoolid(String graduateschoolid) {
		this.graduateschoolid = graduateschoolid;
	}

	public String getPhotoid() {
		return this.photoid;
	}

	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}

	public String getLastupdatetime() {
		return this.lastupdatetime;
	}

	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public Integer getNopaystatus() {
		return this.nopaystatus;
	}

	public void setNopaystatus(Integer nopaystatus) {
		this.nopaystatus = nopaystatus;
	}

	public String getMainaccstatus() {
		return this.mainaccstatus;
	}

	public void setMainaccstatus(String mainaccstatus) {
		this.mainaccstatus = mainaccstatus;
	}

	public Integer getIfmainnote() {
		return this.ifmainnote;
	}

	public void setIfmainnote(Integer ifmainnote) {
		this.ifmainnote = ifmainnote;
	}

	public String getImportlabel() {
		return this.importlabel;
	}

	public void setImportlabel(String importlabel) {
		this.importlabel = importlabel;
	}

	public String getEducationallevelid() {
		return this.educationallevelid;
	}

	public void setEducationallevelid(String educationallevelid) {
		this.educationallevelid = educationallevelid;
	}

	public String getBelongcorpid() {
		return this.belongcorpid;
	}

	public void setBelongcorpid(String belongcorpid) {
		this.belongcorpid = belongcorpid;
	}

	public String getPositionid() {
		return this.positionid;
	}

	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}

	public String getResponsibilityid() {
		return this.responsibilityid;
	}

	public void setResponsibilityid(String responsibilityid) {
		this.responsibilityid = responsibilityid;
	}

	public String getTitleid() {
		return this.titleid;
	}

	public void setTitleid(String titleid) {
		this.titleid = titleid;
	}

	public String getRankid() {
		return this.rankid;
	}

	public void setRankid(String rankid) {
		this.rankid = rankid;
	}

	public String getGradeid() {
		return this.gradeid;
	}

	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}

	public String getJobseriesid() {
		return this.jobseriesid;
	}

	public void setJobseriesid(String jobseriesid) {
		this.jobseriesid = jobseriesid;
	}

	public String getArrangementid() {
		return this.arrangementid;
	}

	public void setArrangementid(String arrangementid) {
		this.arrangementid = arrangementid;
	}

	public String getJobtypeid() {
		return this.jobtypeid;
	}

	public void setJobtypeid(String jobtypeid) {
		this.jobtypeid = jobtypeid;
	}

	public String getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getServiceseniority() {
		return this.serviceseniority;
	}

	public void setServiceseniority(String serviceseniority) {
		this.serviceseniority = serviceseniority;
	}

	public String getStartservicedate() {
		return this.startservicedate;
	}

	public void setStartservicedate(String startservicedate) {
		this.startservicedate = startservicedate;
	}

	public String getAttendondate() {
		return this.attendondate;
	}

	public void setAttendondate(String attendondate) {
		this.attendondate = attendondate;
	}

	public String getNativeplacepropertyid() {
		return this.nativeplacepropertyid;
	}

	public void setNativeplacepropertyid(String nativeplacepropertyid) {
		this.nativeplacepropertyid = nativeplacepropertyid;
	}

	public String getEmployeetypeid() {
		return this.employeetypeid;
	}

	public void setEmployeetypeid(String employeetypeid) {
		this.employeetypeid = employeetypeid;
	}

	public Integer getDlidl() {
		return this.dlidl;
	}

	public void setDlidl(Integer dlidl) {
		this.dlidl = dlidl;
	}

	public String getProbationenddate() {
		return this.probationenddate;
	}

	public void setProbationenddate(String probationenddate) {
		this.probationenddate = probationenddate;
	}

	public String getLastadjustupdatetime() {
		return this.lastadjustupdatetime;
	}

	public void setLastadjustupdatetime(String lastadjustupdatetime) {
		this.lastadjustupdatetime = lastadjustupdatetime;
	}

	public String getEmployeecharid() {
		return this.employeecharid;
	}

	public void setEmployeecharid(String employeecharid) {
		this.employeecharid = employeecharid;
	}

	public String getOrigemployeeid() {
		return this.origemployeeid;
	}

	public void setOrigemployeeid(String origemployeeid) {
		this.origemployeeid = origemployeeid;
	}

	public Integer getIstalent() {
		return this.istalent;
	}

	public void setIstalent(Integer istalent) {
		this.istalent = istalent;
	}

	public Integer getIsregulareemployee() {
		return this.isregulareemployee;
	}

	public void setIsregulareemployee(Integer isregulareemployee) {
		this.isregulareemployee = isregulareemployee;
	}

	public Integer getIsinnerretire() {
		return this.isinnerretire;
	}

	public void setIsinnerretire(Integer isinnerretire) {
		this.isinnerretire = isinnerretire;
	}

	public String getJobcharacter() {
		return this.jobcharacter;
	}

	public void setJobcharacter(String jobcharacter) {
		this.jobcharacter = jobcharacter;
	}

	public String getProbationmaturedate() {
		return this.probationmaturedate;
	}

	public void setProbationmaturedate(String probationmaturedate) {
		this.probationmaturedate = probationmaturedate;
	}

	public String getSocietyensurenum() {
		return this.societyensurenum;
	}

	public void setSocietyensurenum(String societyensurenum) {
		this.societyensurenum = societyensurenum;
	}

	public String getEntersocietydate() {
		return this.entersocietydate;
	}

	public void setEntersocietydate(String entersocietydate) {
		this.entersocietydate = entersocietydate;
	}

	public String getFirstattenddate() {
		return this.firstattenddate;
	}

	public void setFirstattenddate(String firstattenddate) {
		this.firstattenddate = firstattenddate;
	}

	public Integer getIsassignhouse() {
		return this.isassignhouse;
	}

	public void setIsassignhouse(Integer isassignhouse) {
		this.isassignhouse = isassignhouse;
	}

	public String getAssignhousedate() {
		return this.assignhousedate;
	}

	public void setAssignhousedate(String assignhousedate) {
		this.assignhousedate = assignhousedate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAccitem1() {
		return this.accitem1;
	}

	public void setAccitem1(String accitem1) {
		this.accitem1 = accitem1;
	}

	public String getAccitem2() {
		return this.accitem2;
	}

	public void setAccitem2(String accitem2) {
		this.accitem2 = accitem2;
	}

	public String getAccitem3() {
		return this.accitem3;
	}

	public void setAccitem3(String accitem3) {
		this.accitem3 = accitem3;
	}

	public String getAccitem4() {
		return this.accitem4;
	}

	public void setAccitem4(String accitem4) {
		this.accitem4 = accitem4;
	}

	public String getAccitem5() {
		return this.accitem5;
	}

	public void setAccitem5(String accitem5) {
		this.accitem5 = accitem5;
	}

	public String getAccitem6() {
		return this.accitem6;
	}

	public void setAccitem6(String accitem6) {
		this.accitem6 = accitem6;
	}

	public String getAccitem7() {
		return this.accitem7;
	}

	public void setAccitem7(String accitem7) {
		this.accitem7 = accitem7;
	}

	public String getAccitem8() {
		return this.accitem8;
	}

	public void setAccitem8(String accitem8) {
		this.accitem8 = accitem8;
	}

	public Integer getRegularstate() {
		return this.regularstate;
	}

	public void setRegularstate(Integer regularstate) {
		this.regularstate = regularstate;
	}

	public Integer getIsadjustdel() {
		return this.isadjustdel;
	}

	public void setIsadjustdel(Integer isadjustdel) {
		this.isadjustdel = isadjustdel;
	}

	public String getDimissiondate() {
		return this.dimissiondate;
	}

	public void setDimissiondate(String dimissiondate) {
		this.dimissiondate = dimissiondate;
	}

	public String getImmigrationday() {
		return this.immigrationday;
	}

	public void setImmigrationday(String immigrationday) {
		this.immigrationday = immigrationday;
	}

	public Integer getIsexpatriate() {
		return this.isexpatriate;
	}

	public void setIsexpatriate(Integer isexpatriate) {
		this.isexpatriate = isexpatriate;
	}

	public String getExpinunitid() {
		return this.expinunitid;
	}

	public void setExpinunitid(String expinunitid) {
		this.expinunitid = expinunitid;
	}

	public Integer getExpatriatestatus() {
		return this.expatriatestatus;
	}

	public void setExpatriatestatus(Integer expatriatestatus) {
		this.expatriatestatus = expatriatestatus;
	}

	public String getExpinbusinessunitid() {
		return this.expinbusinessunitid;
	}

	public void setExpinbusinessunitid(String expinbusinessunitid) {
		this.expinbusinessunitid = expinbusinessunitid;
	}

	public String getExpinbelongcorp() {
		return this.expinbelongcorp;
	}

	public void setExpinbelongcorp(String expinbelongcorp) {
		this.expinbelongcorp = expinbelongcorp;
	}

	public String getExpinunitlabel() {
		return this.expinunitlabel;
	}

	public void setExpinunitlabel(String expinunitlabel) {
		this.expinunitlabel = expinunitlabel;
	}

	public Integer getFostered() {
		return this.fostered;
	}

	public void setFostered(Integer fostered) {
		this.fostered = fostered;
	}

	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getWillLeaveRange() {
		return this.willLeaveRange;
	}

	public void setWillLeaveRange(String willLeaveRange) {
		this.willLeaveRange = willLeaveRange;
	}
	

	public Set<OrgstdStruct> getOrgstdStructs() {
		return orgstdStructs;
	}

	public void setOrgstdStructs(Set<OrgstdStruct> orgstdStructs) {
		this.orgstdStructs = orgstdStructs;
	}

	public String getFlowerEmpId() {
		return this.flowerEmpId;
	}

	public void setFlowerEmpId(String flowerEmpId) {
		this.flowerEmpId = flowerEmpId;
	}

	public String getTwinsuranceidentitytypeid() {
		return this.twinsuranceidentitytypeid;
	}

	public void setTwinsuranceidentitytypeid(String twinsuranceidentitytypeid) {
		this.twinsuranceidentitytypeid = twinsuranceidentitytypeid;
	}

	public String getTwlsaidentitytypeid() {
		return this.twlsaidentitytypeid;
	}

	public void setTwlsaidentitytypeid(String twlsaidentitytypeid) {
		this.twlsaidentitytypeid = twlsaidentitytypeid;
	}

	public String getWorkyears() {
		return this.workyears;
	}

	public void setWorkyears(String workyears) {
		this.workyears = workyears;
	}

}