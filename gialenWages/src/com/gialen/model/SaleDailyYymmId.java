package com.gialen.model;

import java.sql.Timestamp;
import java.util.Date;


/**
 * SaleDailyYymmId entity. @author MyEclipse Persistence Tools
 */

public class SaleDailyYymmId  implements java.io.Serializable {


    // Fields    

     private Branch branch;
     private Date saleDate;
     private Product product;
     private String saleType;
     private String saleId;
     private Date inputDate;


    // Constructors

    /** default constructor */
    public SaleDailyYymmId() {
    }

    
    

   
    public SaleDailyYymmId(Branch branch, Date saleDate, Product product,
			String saleType, String saleId, Date inputDate) {
		super();
		this.branch = branch;
		this.saleDate = saleDate;
		this.product = product;
		this.saleType = saleType;
		this.saleId = saleId;
		this.inputDate = inputDate;
	}





	// Property accessors

    public Branch getBranch() {
        return this.branch;
    }
    
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

   

    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSaleType() {
        return this.saleType;
    }
    
    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getSaleId() {
        return this.saleId;
    }
    
    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    
    

	public Date getSaleDate() {
		return saleDate;
	}


	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}


	public Date getInputDate() {
		return inputDate;
	}


	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}


	    
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result
				+ ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result
				+ ((saleDate == null) ? 0 : saleDate.hashCode());
		result = prime * result + ((saleId == null) ? 0 : saleId.hashCode());
		result = prime * result
				+ ((saleType == null) ? 0 : saleType.hashCode());
		return result;
	}


	    
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleDailyYymmId other = (SaleDailyYymmId) obj;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (inputDate == null) {
			if (other.inputDate != null)
				return false;
		} else if (!inputDate.equals(other.inputDate))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (saleDate == null) {
			if (other.saleDate != null)
				return false;
		} else if (!saleDate.equals(other.saleDate))
			return false;
		if (saleId == null) {
			if (other.saleId != null)
				return false;
		} else if (!saleId.equals(other.saleId))
			return false;
		if (saleType == null) {
			if (other.saleType != null)
				return false;
		} else if (!saleType.equals(other.saleType))
			return false;
		return true;
	}

   
   


  





}