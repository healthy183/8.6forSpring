package com.gialen.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Branch entity. @author MyEclipse Persistence Tools
 */

public class Branch implements java.io.Serializable {
    // Fields    

     private String braId;
     private District district;
     private String hqId;
     private String braName;
     private String braSname;
     private String braType;
     private String bracode;
     private String addr;
     private String tel;
     private String fax;
     private String zipCode;
     private String master;
     private Timestamp openDate;
     private String sizeCode;
     private Double square;
     private String alloPriority;
     private Double alloDiscount;
     private String allopricelevel;
     private Integer alloPeroid;
     private Double reserveAmt;
     private Double manageamt;
     private Integer paydate;
     private String whid;
     private Double spriceQuotiety;
     private Double mpriceQuotiety;
     private Integer vehicleNum;
     private Integer empCount;
     private String managemode;
     private String place;
     private String storetype;
     private String status;
     private String serialno;
     private Timestamp createDate;
     private Timestamp updateDate;
     private String purchaseId;
     private String settleMethod;
     private String payMethod;
     private Integer settleDays;
     private String saletype;
     private String allocType;
     private Timestamp closeDate;
     private String macAddr;
     private String sysRegNo;
     private String macRegNo;
     private String useperiod;
     private String clientnum;
     private String accounttype;
     private Set employees = new HashSet(0);
     private Set saleDailyYymms = new HashSet(0);
     private Set correspondings = new HashSet(0);
     private Set StoreCounts = new HashSet(0);
     private Set planMoneies = new HashSet(0);
     private Set<PsnaccountMonthProjectDetailsLink> psnaccountMonthProjectDetailsLinks = new HashSet<PsnaccountMonthProjectDetailsLink>(0);
   //  private Set planMoneys = new HashSet(0);
     private Set Store_Counts = new HashSet(0);
     
     private Set saleDailys = new HashSet(0);
     private Set saleDailyTemps = new HashSet(0);
     
     private Set rewardBrands = new HashSet(0);
     
    // Constructors
     private Set<SaleDailyProductPeopleSum> saleDailyProductPeopleSums = new HashSet<SaleDailyProductPeopleSum>(0);
 	 private Set<SaleDailyProduct> saleDailyProducts = new HashSet<SaleDailyProduct>(0);
 	 private Set<SaleDailyProductPeople> saleDailyProductPeoples = new HashSet<SaleDailyProductPeople>(0);
	
 	private Set<SaleDailyBrandPeopleSum> saleDailyBrandPeopleSums = new HashSet<SaleDailyBrandPeopleSum>(0);
    
 	private Set<LastCountTable> lastCountTables = new HashSet<LastCountTable>(0);
 	
 	//10 11Ìí¼Ó
 	private Set<CollectUsrWages> collectUsrWageses = new HashSet<CollectUsrWages>(0); 
     
      
 	
     
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
	
	
	
	
	

	public Set<SaleDailyProductPeopleSum> getSaleDailyProductPeopleSums() {
		return saleDailyProductPeopleSums;
	}

	public void setSaleDailyProductPeopleSums(
			Set<SaleDailyProductPeopleSum> saleDailyProductPeopleSums) {
		this.saleDailyProductPeopleSums = saleDailyProductPeopleSums;
	}

	public Set<SaleDailyProduct> getSaleDailyProducts() {
		return saleDailyProducts;
	}

	public void setSaleDailyProducts(Set<SaleDailyProduct> saleDailyProducts) {
		this.saleDailyProducts = saleDailyProducts;
	}

	public Set<SaleDailyProductPeople> getSaleDailyProductPeoples() {
		return saleDailyProductPeoples;
	}

	public void setSaleDailyProductPeoples(
			Set<SaleDailyProductPeople> saleDailyProductPeoples) {
		this.saleDailyProductPeoples = saleDailyProductPeoples;
	}

	/** default constructor */
    public Branch() {
    }

	/** minimal constructor */
    public Branch(String braId, String braName, String braSname) {
        this.braId = braId;
        this.braName = braName;
        this.braSname = braSname;
    }
    
    /** full constructor */
    public Branch(String braId, District district, String hqId, String braName, String braSname, String braType, String bracode, String addr, String tel, String fax, String zipCode, String master, Timestamp openDate, String sizeCode, Double square, String alloPriority, Double alloDiscount, String allopricelevel, Integer alloPeroid, Double reserveAmt, Double manageamt, Integer paydate, String whid, Double spriceQuotiety, Double mpriceQuotiety, Integer vehicleNum, Integer empCount, String managemode, String place, String storetype, String status, String serialno, Timestamp createDate, Timestamp updateDate, String purchaseId, String settleMethod, String payMethod, Integer settleDays, String saletype, String allocType, Timestamp closeDate, String macAddr, String sysRegNo, String macRegNo, String useperiod, String clientnum, String accounttype, Set employees, Set saleDailyYymms, Set correspondings) {
        this.braId = braId;
        this.district = district;
        this.hqId = hqId;
        this.braName = braName;
        this.braSname = braSname;
        this.braType = braType;
        this.bracode = bracode;
        this.addr = addr;
        this.tel = tel;
        this.fax = fax;
        this.zipCode = zipCode;
        this.master = master;
        this.openDate = openDate;
        this.sizeCode = sizeCode;
        this.square = square;
        this.alloPriority = alloPriority;
        this.alloDiscount = alloDiscount;
        this.allopricelevel = allopricelevel;
        this.alloPeroid = alloPeroid;
        this.reserveAmt = reserveAmt;
        this.manageamt = manageamt;
        this.paydate = paydate;
        this.whid = whid;
        this.spriceQuotiety = spriceQuotiety;
        this.mpriceQuotiety = mpriceQuotiety;
        this.vehicleNum = vehicleNum;
        this.empCount = empCount;
        this.managemode = managemode;
        this.place = place;
        this.storetype = storetype;
        this.status = status;
        this.serialno = serialno;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.purchaseId = purchaseId;
        this.settleMethod = settleMethod;
        this.payMethod = payMethod;
        this.settleDays = settleDays;
        this.saletype = saletype;
        this.allocType = allocType;
        this.closeDate = closeDate;
        this.macAddr = macAddr;
        this.sysRegNo = sysRegNo;
        this.macRegNo = macRegNo;
        this.useperiod = useperiod;
        this.clientnum = clientnum;
        this.accounttype = accounttype;
        this.employees = employees;
        this.saleDailyYymms = saleDailyYymms;
        this.correspondings = correspondings;
    }
    
    
    
    
    
    
   

	// Property accessors
    public Set getPlanMoneies() {
		return planMoneies;
	}

	public void setPlanMoneies(Set planMoneies) {
		this.planMoneies = planMoneies;
	}

	public String getBraId() {
        return this.braId;
    }
    
    public void setBraId(String braId) {
        this.braId = braId;
    }

    public District getDistrict() {
        return this.district;
    }
    
    public void setDistrict(District district) {
        this.district = district;
    }

    public String getHqId() {
        return this.hqId;
    }
    
    public void setHqId(String hqId) {
        this.hqId = hqId;
    }

    public String getBraName() {
        return this.braName;
    }
    
    public void setBraName(String braName) {
        this.braName = braName;
    }

    public String getBraSname() {
        return this.braSname;
    }
    
    public void setBraSname(String braSname) {
        this.braSname = braSname;
    }

    public String getBraType() {
        return this.braType;
    }
    
    public void setBraType(String braType) {
        this.braType = braType;
    }

    public String getBracode() {
        return this.bracode;
    }
    
    public void setBracode(String bracode) {
        this.bracode = bracode;
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

    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getZipCode() {
        return this.zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMaster() {
        return this.master;
    }
    
    public void setMaster(String master) {
        this.master = master;
    }

    public Timestamp getOpenDate() {
        return this.openDate;
    }
    
    public void setOpenDate(Timestamp openDate) {
        this.openDate = openDate;
    }

    public String getSizeCode() {
        return this.sizeCode;
    }
    
    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public Double getSquare() {
        return this.square;
    }
    
    public void setSquare(Double square) {
        this.square = square;
    }

    public String getAlloPriority() {
        return this.alloPriority;
    }
    
    public void setAlloPriority(String alloPriority) {
        this.alloPriority = alloPriority;
    }

    public Double getAlloDiscount() {
        return this.alloDiscount;
    }
    
    public void setAlloDiscount(Double alloDiscount) {
        this.alloDiscount = alloDiscount;
    }

    public String getAllopricelevel() {
        return this.allopricelevel;
    }
    
    public void setAllopricelevel(String allopricelevel) {
        this.allopricelevel = allopricelevel;
    }

    public Integer getAlloPeroid() {
        return this.alloPeroid;
    }
    
    public void setAlloPeroid(Integer alloPeroid) {
        this.alloPeroid = alloPeroid;
    }

    public Double getReserveAmt() {
        return this.reserveAmt;
    }
    
    public void setReserveAmt(Double reserveAmt) {
        this.reserveAmt = reserveAmt;
    }

    public Double getManageamt() {
        return this.manageamt;
    }
    
    public void setManageamt(Double manageamt) {
        this.manageamt = manageamt;
    }

    public Integer getPaydate() {
        return this.paydate;
    }
    
    public void setPaydate(Integer paydate) {
        this.paydate = paydate;
    }

    public String getWhid() {
        return this.whid;
    }
    
    public void setWhid(String whid) {
        this.whid = whid;
    }

    public Double getSpriceQuotiety() {
        return this.spriceQuotiety;
    }
    
    public void setSpriceQuotiety(Double spriceQuotiety) {
        this.spriceQuotiety = spriceQuotiety;
    }

    public Double getMpriceQuotiety() {
        return this.mpriceQuotiety;
    }
    
    public void setMpriceQuotiety(Double mpriceQuotiety) {
        this.mpriceQuotiety = mpriceQuotiety;
    }

    public Integer getVehicleNum() {
        return this.vehicleNum;
    }
    
    public void setVehicleNum(Integer vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public Integer getEmpCount() {
        return this.empCount;
    }
    
    public void setEmpCount(Integer empCount) {
        this.empCount = empCount;
    }

    public String getManagemode() {
        return this.managemode;
    }
    
    public void setManagemode(String managemode) {
        this.managemode = managemode;
    }

    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }

    public String getStoretype() {
        return this.storetype;
    }
    
    public void setStoretype(String storetype) {
        this.storetype = storetype;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSerialno() {
        return this.serialno;
    }
    
    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getPurchaseId() {
        return this.purchaseId;
    }
    
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getSettleMethod() {
        return this.settleMethod;
    }
    
    public void setSettleMethod(String settleMethod) {
        this.settleMethod = settleMethod;
    }

    public String getPayMethod() {
        return this.payMethod;
    }
    
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getSettleDays() {
        return this.settleDays;
    }
    
    public void setSettleDays(Integer settleDays) {
        this.settleDays = settleDays;
    }

    public String getSaletype() {
        return this.saletype;
    }
    
    public void setSaletype(String saletype) {
        this.saletype = saletype;
    }

    public String getAllocType() {
        return this.allocType;
    }
    
    public void setAllocType(String allocType) {
        this.allocType = allocType;
    }

    public Timestamp getCloseDate() {
        return this.closeDate;
    }
    
    public void setCloseDate(Timestamp closeDate) {
        this.closeDate = closeDate;
    }

    public String getMacAddr() {
        return this.macAddr;
    }
    
    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getSysRegNo() {
        return this.sysRegNo;
    }
    
    public void setSysRegNo(String sysRegNo) {
        this.sysRegNo = sysRegNo;
    }

    public String getMacRegNo() {
        return this.macRegNo;
    }
    
    public void setMacRegNo(String macRegNo) {
        this.macRegNo = macRegNo;
    }

    public String getUseperiod() {
        return this.useperiod;
    }
    
    public void setUseperiod(String useperiod) {
        this.useperiod = useperiod;
    }

    public String getClientnum() {
        return this.clientnum;
    }
    
    public void setClientnum(String clientnum) {
        this.clientnum = clientnum;
    }

    public String getAccounttype() {
        return this.accounttype;
    }
    
    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public Set getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Set employees) {
        this.employees = employees;
    }

    public Set getSaleDailyYymms() {
        return this.saleDailyYymms;
    }
    
    public void setSaleDailyYymms(Set saleDailyYymms) {
        this.saleDailyYymms = saleDailyYymms;
    }

    public Set getCorrespondings() {
        return this.correspondings;
    }
    
    public void setCorrespondings(Set correspondings) {
        this.correspondings = correspondings;
    }

	public Set getStoreCounts() {
		return StoreCounts;
	}

	public void setStoreCounts(Set storeCounts) {
		StoreCounts = storeCounts;
	}

//	public Set getPlanMoneys() {
//		return planMoneys;
//	}
//
//	public void setPlanMoneys(Set planMoneys) {
//		this.planMoneys = planMoneys;
//	}

	public Set getStore_Counts() {
		return Store_Counts;
	}

	public void setStore_Counts(Set store_Counts) {
		Store_Counts = store_Counts;
	}

	public Set getSaleDailys() {
		return saleDailys;
	}

	public void setSaleDailys(Set saleDailys) {
		this.saleDailys = saleDailys;
	}

	public Set getSaleDailyTemps() {
		return saleDailyTemps;
	}

	public void setSaleDailyTemps(Set saleDailyTemps) {
		this.saleDailyTemps = saleDailyTemps;
	}

	public Set getRewardBrands() {
		return rewardBrands;
	}

	public void setRewardBrands(Set rewardBrands) {
		this.rewardBrands = rewardBrands;
	}

}