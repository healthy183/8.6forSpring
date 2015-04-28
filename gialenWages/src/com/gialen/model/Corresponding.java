package com.gialen.model;

import java.util.HashSet;
import java.util.Set;



/**
 * Corresponding entity. @author MyEclipse Persistence Tools
 */

public class Corresponding  implements java.io.Serializable {


    // Fields    
     private Integer correspondingId;
     private Branch branch;
     private OrgstdStruct orgstdStruct;
     private String braName;
     private String unitname;
     private String storeType;
     private String storeTypeName;
     private String remark1;
     private String remark2;
     private Set<StoreCount> storeCounts = new HashSet<StoreCount>();

     ///2012 -9-20 C6  µÇÂ¼ÕÊºÅ
      private String LoginCode;
      //¿µ 09 24
      private Integer peopleCount;
      private Integer correspondingType;
      
      
      
      
    // Constructors

    public Corresponding(Branch branch, OrgstdStruct orgstdStruct,
			String braName, String unitname, String storeType,
			String storeTypeName, String remark1, String remark2,
			Set<StoreCount> storeCounts, String LoginCode) {
		super();
		this.branch = branch;
		this.orgstdStruct = orgstdStruct;
		this.braName = braName;
		this.unitname = unitname;
		this.storeType = storeType;
		this.storeTypeName = storeTypeName;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.storeCounts = storeCounts;
		this.LoginCode = LoginCode;
	}


	/** default constructor */
    public Corresponding() {
    }

   
    public Corresponding(Integer correspondingId, Branch branch,
			OrgstdStruct orgstdStruct, String braName, String unitname,
			String storeType, String remark1, String remark2) {
		super();
		this.correspondingId = correspondingId;
		this.branch = branch;
		this.orgstdStruct = orgstdStruct;
		this.braName = braName;
		this.unitname = unitname;
		this.storeType = storeType;
		this.remark1 = remark1;
		this.remark2 = remark2;
	}
    
    
    
    
    
	public Integer getPeopleCount() {
		return peopleCount;
	}


	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}


	public Integer getCorrespondingType() {
		return correspondingType;
	}


	public void setCorrespondingType(Integer correspondingType) {
		this.correspondingType = correspondingType;
	}


	// Property accessors
    public Branch getBranch() {
        return this.branch;
    }
    
    public String getStoreTypeName() {
		return storeTypeName;
	}

	public void setStoreTypeName(String storeTypeName) {
		this.storeTypeName = storeTypeName;
	}


	public Integer getCorrespondingId() {
		return correspondingId;
	}

	public void setCorrespondingId(Integer correspondingId) {
		this.correspondingId = correspondingId;
	}

	public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public OrgstdStruct getOrgstdStruct() {
        return this.orgstdStruct;
    }
    
    public void setOrgstdStruct(OrgstdStruct orgstdStruct) {
        this.orgstdStruct = orgstdStruct;
    }

    public String getBraName() {
        return this.braName;
    }
    
    public void setBraName(String braName) {
        this.braName = braName;
    }

    public String getUnitname() {
        return this.unitname;
    }
    
    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getStoreType() {
        return this.storeType;
    }
    
    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getRemark1() {
        return this.remark1;
    }
    
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return this.remark2;
    }
    
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }


	public Set<StoreCount> getStoreCounts() {
		return storeCounts;
	}


	public void setStoreCounts(Set<StoreCount> storeCounts) {
		this.storeCounts = storeCounts;
	}


	public String getLoginCode() {
		return LoginCode;
	}


	public void setLoginCode(String loginCode) {
		LoginCode = loginCode;
	}


	
   

    







}