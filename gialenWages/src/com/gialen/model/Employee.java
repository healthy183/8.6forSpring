package com.gialen.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee  implements java.io.Serializable {


    // Fields    

     private String empId;
     private Branch branch;
     private String purchaseId;
     private String deptId;
     private String empName;
     private String headship;
     private String sex;
     private Timestamp birthday;
     private String addr;
     private String tel;
     private String mobile;
     private Timestamp workDate;
     private Timestamp leaveDate;
     private String empType;
     private String sectionId;
     private String headuser;
     private String status;
     private Timestamp createDate;
     private Timestamp updatedate;
     private String personalId;
     private String remark;
     private String incentiveroleid;
     private Set saleDailyYymms = new HashSet(0);
     
     private Set saleDaily = new HashSet(0);
     private Set saleDailym = new HashSet(0);
     
     private Set<SaleDailyProduct> saleDailyProducts = new HashSet<SaleDailyProduct>(0);
 	 private Set<SaleDailyProductPeopleSum> saleDailyProductPeopleSums = new HashSet<SaleDailyProductPeopleSum>(0);
 	 private Set<SaleDailyProductPeople> saleDailyProductPeoples = new HashSet<SaleDailyProductPeople>(0);

 	 private Set<StoreCount> storeCountSet = new HashSet<StoreCount>();
     private Set<SaleDailyBrandPeopleSum> saleDailyBrandPeopleSums = new HashSet<SaleDailyBrandPeopleSum>(0);
     private Set<LastCountTable> lastCountTables = new HashSet<LastCountTable>(0);
     
   //10 11Ìí¼Ó
 	private Set<CollectUsrWages> collectUsrWageses = new HashSet<CollectUsrWages>(0);
 	
 	
 	
     
     
     
     
    public Set<CollectUsrWages> getCollectUsrWageses() {
		return collectUsrWageses;
	}

	public void setCollectUsrWageses(Set<CollectUsrWages> collectUsrWageses) {
		this.collectUsrWageses = collectUsrWageses;
	}

	public Set<StoreCount> getStoreCountSet() {
		return storeCountSet;
	}

	public void setStoreCountSet(Set<StoreCount> storeCountSet) {
		this.storeCountSet = storeCountSet;
	}

	public Set<SaleDailyBrandPeopleSum> getSaleDailyBrandPeopleSums() {
		return saleDailyBrandPeopleSums;
	}

	public void setSaleDailyBrandPeopleSums(
			Set<SaleDailyBrandPeopleSum> saleDailyBrandPeopleSums) {
		this.saleDailyBrandPeopleSums = saleDailyBrandPeopleSums;
	}

	public Set<LastCountTable> getLastCountTables() {
		return lastCountTables;
	}

	public void setLastCountTables(Set<LastCountTable> lastCountTables) {
		this.lastCountTables = lastCountTables;
	}

	public Set<SaleDailyProductPeopleSum> getSaleDailyProductPeopleSums() {
		return saleDailyProductPeopleSums;
	}

	public void setSaleDailyProductPeopleSums(
			Set<SaleDailyProductPeopleSum> saleDailyProductPeopleSums) {
		this.saleDailyProductPeopleSums = saleDailyProductPeopleSums;
	}

	public Set<SaleDailyProductPeople> getSaleDailyProductPeoples() {
		return saleDailyProductPeoples;
	}

	public void setSaleDailyProductPeoples(
			Set<SaleDailyProductPeople> saleDailyProductPeoples) {
		this.saleDailyProductPeoples = saleDailyProductPeoples;
	}

	// Constructors
    public Set<SaleDailyProduct> getSaleDailyProducts() {
		return saleDailyProducts;
	}

	public void setSaleDailyProducts(Set<SaleDailyProduct> saleDailyProducts) {
		this.saleDailyProducts = saleDailyProducts;
	}

	/** default constructor */
    public Employee() {
    }

	/** minimal constructor */
    public Employee(String empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }
    
    /** full constructor */
    public Employee(String empId, Branch branch, String purchaseId, String deptId, String empName, String headship, String sex, Timestamp birthday, String addr, String tel, String mobile, Timestamp workDate, Timestamp leaveDate, String empType, String sectionId, String headuser, String status, Timestamp createDate, Timestamp updatedate, String personalId, String remark, String incentiveroleid, Set saleDailyYymms) {
        this.empId = empId;
        this.branch = branch;
        this.purchaseId = purchaseId;
        this.deptId = deptId;
        this.empName = empName;
        this.headship = headship;
        this.sex = sex;
        this.birthday = birthday;
        this.addr = addr;
        this.tel = tel;
        this.mobile = mobile;
        this.workDate = workDate;
        this.leaveDate = leaveDate;
        this.empType = empType;
        this.sectionId = sectionId;
        this.headuser = headuser;
        this.status = status;
        this.createDate = createDate;
        this.updatedate = updatedate;
        this.personalId = personalId;
        this.remark = remark;
        this.incentiveroleid = incentiveroleid;
        this.saleDailyYymms = saleDailyYymms;
    }

   
    // Property accessors

    public String getEmpId() {
        return this.empId;
    }
    
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Branch getBranch() {
        return this.branch;
    }
    
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getPurchaseId() {
        return this.purchaseId;
    }
    
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getDeptId() {
        return this.deptId;
    }
    
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getEmpName() {
        return this.empName;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getHeadship() {
        return this.headship;
    }
    
    public void setHeadship(String headship) {
        this.headship = headship;
    }

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }

    public Timestamp getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getAddr() {
        return this.addr;
    }
    
    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Timestamp getWorkDate() {
        return this.workDate;
    }
    
    public void setWorkDate(Timestamp workDate) {
        this.workDate = workDate;
    }

    public Timestamp getLeaveDate() {
        return this.leaveDate;
    }
    
    public void setLeaveDate(Timestamp leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getEmpType() {
        return this.empType;
    }
    
    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getSectionId() {
        return this.sectionId;
    }
    
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getHeaduser() {
        return this.headuser;
    }
    
    public void setHeaduser(String headuser) {
        this.headuser = headuser;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdatedate() {
        return this.updatedate;
    }
    
    public void setUpdatedate(Timestamp updatedate) {
        this.updatedate = updatedate;
    }

    public String getPersonalId() {
        return this.personalId;
    }
    
    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIncentiveroleid() {
        return this.incentiveroleid;
    }
    
    public void setIncentiveroleid(String incentiveroleid) {
        this.incentiveroleid = incentiveroleid;
    }

    public Set getSaleDailyYymms() {
        return this.saleDailyYymms;
    }
    
    public void setSaleDailyYymms(Set saleDailyYymms) {
        this.saleDailyYymms = saleDailyYymms;
    }

	public Set getSaleDaily() {
		return saleDaily;
	}

	public void setSaleDaily(Set saleDaily) {
		this.saleDaily = saleDaily;
	}

	public Set getSaleDailym() {
		return saleDailym;
	}

	public void setSaleDailym(Set saleDailym) {
		this.saleDailym = saleDailym;
	}
   








}