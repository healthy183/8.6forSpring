package com.gialen.countProductProject.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.gialen.countProductProject.service.CountProductProjectService;
import com.gialen.dao.BranchDAO;
import com.gialen.dao.CorrespondingDAO;
import com.gialen.dao.OrgstdStructDAO;
import com.gialen.dao.ProProjectRelationTableDAO;
import com.gialen.dao.ProductDAO;
import com.gialen.dao.ProductProjectDAO;
import com.gialen.dao.PsnaccountDAO;
import com.gialen.dao.PsnaccountMonthProjectDetailsLinkDAO;
import com.gialen.dao.SaleDailyProductDAO;
import com.gialen.dao.SaleDailyProductPeopleDAO;
import com.gialen.dao.SaleDailyProductPeopleSumDAO;
import com.gialen.dao.SaleDailyYymmDAO;
import com.gialen.main.action.ToolAction;
import com.gialen.model.Branch;
import com.gialen.model.Corresponding;
import com.gialen.model.OperatingMonth;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.ProProjectRelationTable;
import com.gialen.model.Product;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductProject;
import com.gialen.model.Psnaccount;
import com.gialen.model.PsnaccountMonthProjectDetailsLink;
import com.gialen.model.SaleDailyProductPeopleSum;
import com.gialen.model.SaleDailyYymm;
import com.gialen.model.vo.SaleDailyProductPeopleSumVo;
import com.gialen.model.vo.SaleDailyProductPeopleVo;
import com.gialen.tools.Arith;

public class CountProductProjectServiceImpl implements
		CountProductProjectService {

	private	SaleDailyYymmDAO saleDailyYymmDAO;
	private ProductDAO productDAO;
	private BranchDAO branchDAO;
	private CorrespondingDAO correspondingDAO;
	private PsnaccountDAO psnaccountDAO;
	private ProProjectRelationTableDAO proProjectRelationTableDAO;
	private PsnaccountMonthProjectDetailsLinkDAO psnaccountMonthProjectDetailsLinkDAO;
	private OrgstdStructDAO orgstdStructDAO;
	private SaleDailyProductDAO saleDailyProductDAO;
	private SaleDailyProductPeopleDAO saleDailyProductPeopleDAO;
	private	SaleDailyProductPeopleSumDAO saleDailyProductPeopleSumDAO;
	private ProductProjectDAO productProjectDAO;
	
	public ProductProjectDAO getProductProjectDAO() {
		return productProjectDAO;
	}

	public void setProductProjectDAO(ProductProjectDAO productProjectDAO) {
		this.productProjectDAO = productProjectDAO;
	}

	public SaleDailyProductDAO getSaleDailyProductDAO() {
		return saleDailyProductDAO;
	}

	public void setSaleDailyProductDAO(SaleDailyProductDAO saleDailyProductDAO) {
		this.saleDailyProductDAO = saleDailyProductDAO;
	}

	public SaleDailyProductPeopleDAO getSaleDailyProductPeopleDAO() {
		return saleDailyProductPeopleDAO;
	}

	public void setSaleDailyProductPeopleDAO(
			SaleDailyProductPeopleDAO saleDailyProductPeopleDAO) {
		this.saleDailyProductPeopleDAO = saleDailyProductPeopleDAO;
	}

	public SaleDailyProductPeopleSumDAO getSaleDailyProductPeopleSumDAO() {
		return saleDailyProductPeopleSumDAO;
	}

	public void setSaleDailyProductPeopleSumDAO(
			SaleDailyProductPeopleSumDAO saleDailyProductPeopleSumDAO) {
		this.saleDailyProductPeopleSumDAO = saleDailyProductPeopleSumDAO;
	}

	public OrgstdStructDAO getOrgstdStructDAO() {
		return orgstdStructDAO;
	}

	public void setOrgstdStructDAO(OrgstdStructDAO orgstdStructDAO) {
		this.orgstdStructDAO = orgstdStructDAO;
	}

	public PsnaccountMonthProjectDetailsLinkDAO getPsnaccountMonthProjectDetailsLinkDAO() {
		return psnaccountMonthProjectDetailsLinkDAO;
	}

	public void setPsnaccountMonthProjectDetailsLinkDAO(
			PsnaccountMonthProjectDetailsLinkDAO psnaccountMonthProjectDetailsLinkDAO) {
		this.psnaccountMonthProjectDetailsLinkDAO = psnaccountMonthProjectDetailsLinkDAO;
	}

	public ProProjectRelationTableDAO getProProjectRelationTableDAO() {
		return proProjectRelationTableDAO;
	}

	public void setProProjectRelationTableDAO(
			ProProjectRelationTableDAO proProjectRelationTableDAO) {
		this.proProjectRelationTableDAO = proProjectRelationTableDAO;
	}

	public PsnaccountDAO getPsnaccountDAO() {
		return psnaccountDAO;
	}

	public void setPsnaccountDAO(PsnaccountDAO psnaccountDAO) {
		this.psnaccountDAO = psnaccountDAO;
	}

	public CorrespondingDAO getCorrespondingDAO() {
		return correspondingDAO;
	}

	public void setCorrespondingDAO(CorrespondingDAO correspondingDAO) {
		this.correspondingDAO = correspondingDAO;
	}

	public BranchDAO getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public SaleDailyYymmDAO getSaleDailyYymmDAO() {
		return saleDailyYymmDAO;
	}

	public void setSaleDailyYymmDAO(SaleDailyYymmDAO saleDailyYymmDAO) {
		this.saleDailyYymmDAO = saleDailyYymmDAO;
	}

	     //执行操作统计上个营运月每个员工的销售总金额 提成
	public void saveThisMonthPsnaccountMonthSaleCountLink(
			OperatingMonth thisOperatingMonth,String unitcode) {
		//营运月id 
		String thisMonthId = thisOperatingMonth.getOperatingMonthId();
		//工具类中格式化数据
		List<Date> dateList = ToolAction.getMyFormatDate
				(thisOperatingMonth.getOperatingStartDate(),thisOperatingMonth.getOperatingEndDate());
		
		//查询当前区域下所有门店 
		List<OrgstdStruct> orgstdStructDAOList = 
				orgstdStructDAO.findStoreByAreaUnitCode(unitcode,24);
		
		/*//查询佳讯现在所有门店
		List<Branch> branchList = branchDAO.findNowBrand();*/
		/*branchList.clear();
		  branchList.add(branchDAO.findById("02002"));*/
		
		//查询今个月所有单品方案中单品的主键 
		List<String> thisMonthProjectsProidList = 
				productDAO.findThisMonthProjectsProid(thisMonthId);
		
		//查询今个月所有单品，方案的中间表 
		List<ProProjectRelationTable> relationTableList = 
			proProjectRelationTableDAO.findByProIdAndMonthId
				(thisMonthProjectsProidList,thisMonthId);
		
		Psnaccount	publicPsnaccount =	 //获取公共账号
				(Psnaccount) psnaccountDAO.findByEmployeeid("00000").get(0);
		
		//存放汇总PsnaccountMonthProjectDetailsLink的 list
		List<PsnaccountMonthProjectDetailsLink> allLink = 
				new LinkedList<PsnaccountMonthProjectDetailsLink>();
		
		for(OrgstdStruct thisOrgstdStruct : orgstdStructDAOList){
			/*//根据佳讯门店编号 查询中间表信息
			List<Corresponding>  correspondingList = 
					correspondingDAO.findCorrespondingByBrandId(branch.getBraId());
			if(correspondingList.size() == 0){
				continue; //如果门店没有对应,则跳出
			}*/
			//根据hr门店编号 查询中间表佳讯门店
			Branch branch = correspondingDAO.findBranchByUnitid(thisOrgstdStruct.getUnitid());
			if(branch == null){
				continue; //如果门店没有对应,则跳出
			}
			
			System.out.println("开始啦!");
			/*//根据hr门店，获取门店下所有员工
			List<Psnaccount> psnaccountList = psnaccountDAO.getThisStorePerson(thisOrgstdStruct.getUnitid());*/
			
			//从佳讯系统当前门店销售流水中查找员工,并根据employeeid获得hr员工 (不含公共账号)
			 List<Psnaccount>  psnaccountList = psnaccountDAO.getThisStorePersonBySaleDailyYymm
				(dateList.get(0),dateList.get(1),branch.getBraId(),thisMonthId,thisMonthProjectsProidList);
			//psnaccountList.add(publicPsnaccount);//添加公共账号到门店员工list
			
			//新增当前门店公共账号javaBean
			/**/PsnaccountMonthProjectDetailsLink parentPublicAccount =	newparentPublicAccount
					(thisOrgstdStruct,branch,publicPsnaccount,thisOperatingMonth);
			
			//查询上个营运月 本门店  的所有金额非0的,并且是单品项目中的销售记录 
			List<SaleDailyYymm>	saleDailyYymmList =  
				saleDailyYymmDAO.findThisMonthSale(dateList.get(0),dateList.get(1),
					branch.getBraId(),thisMonthId,thisMonthProjectsProidList);
			
			for(Psnaccount psnaccount : psnaccountList){ //迭代员工
				//新增 当前营运月 当前员工 总提成 总销售 javaBean
				PsnaccountMonthProjectDetailsLink  parentLink =	
						newparentPublicAccount(thisOrgstdStruct,branch,psnaccount,thisOperatingMonth);
				//hr员工编号
				String thisEmployeeid =	psnaccount.getEmployeeid();
				
				//迭代销售记录 
				for(int i =0;i<saleDailyYymmList.size();i++){  
					//当前流水记录
					SaleDailyYymm thissaleDailyYymm = saleDailyYymmList.get(i);
					String saleEmployeeid = thissaleDailyYymm.getEmployee().getPersonalId();//当前流水员工id	
					//如果是当前员工销售的
					if(thisEmployeeid.equals(saleEmployeeid)){
						newChlidlink(thissaleDailyYymm,thisOperatingMonth,thisOrgstdStruct,branch,psnaccount,parentLink,saleDailyYymmList,i,relationTableList);
					//判断是否为公共账号
					}else if("00000".equals(saleEmployeeid)){
						newChlidlink(thissaleDailyYymm,thisOperatingMonth,thisOrgstdStruct,branch,psnaccount,parentPublicAccount,saleDailyYymmList,i,relationTableList);
					}/**/
				}
				//将员工汇总javabean添加到list
				allLink.add(parentLink);
			}
			allLink.add(parentPublicAccount);
		}
		
		System.out.println("最后统计!");
		//
		for(PsnaccountMonthProjectDetailsLink thisParentLink : allLink){
			
			 Integer saleNum = 0;
			 Double saleCount = 0.0;
			 Double saleWages = 0.0;
			
		    Set<PsnaccountMonthProjectDetailsLink> thisLinkSet = 
		    		thisParentLink.getPsnaccountMonthProjectDetailsLinks();
		    
		    	for(PsnaccountMonthProjectDetailsLink childLink : thisLinkSet){
		    		saleNum = childLink.getSaleNum()+saleNum; //计算总销售数量
		    		saleCount = Arith.add(childLink.getSaleCount(), saleCount);//计算总销售金额
		    		saleWages = Arith.add(childLink.getSaleWages(),saleWages);//计算总销售奖金
		    	}
		    	
		    	thisParentLink.setSaleNum(saleNum);
		    	thisParentLink.setSaleCount(saleCount);
		    	thisParentLink.setSaleWages(saleWages);
		}
		
		psnaccountMonthProjectDetailsLinkDAO.saveAllLink(allLink);
	}

	//新增员工 汇总表
	private PsnaccountMonthProjectDetailsLink newparentPublicAccount(
			OrgstdStruct thisOrgstdStruct, Branch branch,
			Psnaccount publicPsnaccount, OperatingMonth thisOperatingMonth) {

		//当前门店公共账号 当前营运月 销售总金额 总数量 总奖金
		PsnaccountMonthProjectDetailsLink parentPublicAccount = new PsnaccountMonthProjectDetailsLink();
		
		parentPublicAccount.setOrgstdStruct(thisOrgstdStruct); //hr门店一对多
		thisOrgstdStruct.getPsnaccountMonthProjectDetailsLinks().add(parentPublicAccount);
		
		parentPublicAccount.setBranch(branch); //佳讯门店 一对多关系 
		branch.getPsnaccountMonthProjectDetailsLinks().add(parentPublicAccount);
		
		parentPublicAccount.setPsnaccount(publicPsnaccount); //hr员工  一对多关系
		publicPsnaccount.getPsnaccountMonthProjectDetailsLinks().add(parentPublicAccount);
		
		parentPublicAccount.setOperatingMonth(thisOperatingMonth); //营运月 一对多关系
		thisOperatingMonth.getPsnaccountMonthProjectDetailsLinks().add(parentPublicAccount);
		parentPublicAccount.setSaleCount(0.0);//销售总金额
		parentPublicAccount.setSaleWages(0.0);//销售总奖金
		parentPublicAccount.setSaleNum(0);//销售总量
		parentPublicAccount.setLinktype(0);//汇总0
		System.out.println("一个 parent新增完!");
		return parentPublicAccount;
	}
	
	//新增明细 单品方案明细javaBean
	private void newChlidlink(SaleDailyYymm thissaleDailyYymm,OperatingMonth thisOperatingMonth,
			OrgstdStruct thisOrgstdStruct,Branch branch,Psnaccount psnaccount,
				PsnaccountMonthProjectDetailsLink  parentLink,
					List<SaleDailyYymm> saleDailyYymmList,int i,
						List<ProProjectRelationTable> relationTableList){
		//获得流水单品   
		Product thisPro = thissaleDailyYymm.getId().getProduct();
		/*//查询该单品 在 当前营运月 是否在某个单品方案中
		List<ProProjectRelationTable> proProjectRelationTableList =	
				proProjectRelationTableDAO.getProjectByProAndMonth(thisPro.getProId(),thisOperatingMonth.getOperatingMonthId());
		*/
		
		ProductProject productProject = null;
		for(ProProjectRelationTable table : relationTableList){
			if(table.getProduct().getProId().equals(thisPro.getProId())){
				productProject = table.getProductProject();
			}
		}
		
		if(productProject != null){
		
		//如果该单品有方案 则执行统计
		//if(proProjectRelationTableList.size()>0){}
			//单品在单品方案不能重复的  所以获得其唯一的方案
			//ProductProject productProject =	proProjectRelationTableList.get(0).getProductProject();
			//营运月中 员工每条单品销售金额 数量 奖金 明细表 
			PsnaccountMonthProjectDetailsLink childLink = new PsnaccountMonthProjectDetailsLink();
			
			Double saleCount = thissaleDailyYymm.getSaleAmt();    //销售金额
			Double saleNum = thissaleDailyYymm.getSaleQty();    //销售数量
			Double pcashPayAmt = thissaleDailyYymm.getPcashPayAmt();//现金券抵用的金额
			
			childLink.setSaleNum(saleNum.intValue());//设置销售数量 
			childLink.setSaleCount(saleCount); //销售金额
			childLink.setPcashPayAmt(pcashPayAmt);//现金券抵用的金额
			
			Double saleWages = 0.0;//本流水奖金
			Double productProjectVal = productProject.getProductProjectVal();//提点
			
			//按百分比提成还是件 0百分比       1件
			Integer productProjectType = productProject.getProductProjectType();
			if(productProjectType == 0){ //saleWages = (销售金额 - 现金券抵用的金额)*提点 
				saleWages = Arith.mul(Arith.sub(saleCount, pcashPayAmt),Arith.div(productProjectVal,100));
			}else{ //saleWages = 销售数量*提点 
				saleWages =	Arith.mul(saleNum,productProjectVal);
			}
			//销售提成
			childLink.setSaleWages(saleWages);
			
			childLink.setOrgstdStruct(thisOrgstdStruct); //hr门店一对多
			thisOrgstdStruct.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			childLink.setBranch(branch); //佳讯门店 一对多关系 
			branch.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setProductProject(productProject); //单品方案
			productProject.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setPsnaccount(psnaccount); //hr员工 一对多
			psnaccount.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setSaleDailyYymm(thissaleDailyYymm);//流水一对多
			thissaleDailyYymm.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setProduct(thisPro); //单品一对多
			thisPro.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			//获得该单品所在小品牌 
			ProductBrand productBrand = thisPro.getProductBrand();
			childLink.setProductBrand(productBrand);//小品牌一对多
			productBrand.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setOperatingMonth(thisOperatingMonth); //营运月 一对多
			thisOperatingMonth.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setPsnaccountMonthProjectDetailsLink(parentLink);//设置父子 一对多
			parentLink.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			childLink.setLinktype(1);//明细1
		
		}	
		//判断完移除 流水
		saleDailyYymmList.remove(thissaleDailyYymm);
		i--;//重新迭代
		System.out.println("算完一个");
	}

	
	      //查询上个营运月的的员工单品销售提成表
	public List<PsnaccountMonthProjectDetailsLink> findLastMonthPsnaccountMonthSaleCountLink(
			String operatingMonthId) {
		return psnaccountMonthProjectDetailsLinkDAO.findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId);
	}

	        //查询上个营运月的的指定区域员工单品销售提成表
	public List<PsnaccountMonthProjectDetailsLink> findLastMonthPsnaccountMonthSaleCountLink(
			String operatingMonthId, String unitcode) {
		return psnaccountMonthProjectDetailsLinkDAO.
				findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId,unitcode);
	}

	//查询上一个月奖金数据的统计
	public List<SaleDailyProductPeopleSumVo> findThisMonthPeopleWages(
			OperatingMonth thisOperatingMonth) {
		
		List<SaleDailyProductPeopleSum> SaleDailyProductPeopleSumList 
			= saleDailyProductPeopleSumDAO.findThisMonthPeopleWages(thisOperatingMonth);
		
		List<SaleDailyProductPeopleSumVo> saleSumList = new ArrayList<SaleDailyProductPeopleSumVo>();
		
		for(SaleDailyProductPeopleSum saleSum : SaleDailyProductPeopleSumList){
			saleSumList.add(SaleDailyProductPeopleSumVo.parse(saleSum));
		}
		return saleSumList;
	}

	//1,清空中间表
	public void delMiddleTable() {
		saleDailyProductPeopleSumDAO.delMiddleTable();
	}

	//从销售流水表SaleDailyYymm.java中抽取单品项目中含有的销售流水到saleDaily_product
	public void getSaleToSaleDailyProduct(OperatingMonth thisOperatingMonth) {
		//查询今个月所有单品方案中单品的主键 
	List<String> thisMonthProjectsProidList = 
		productDAO.findThisMonthProjectsProid(thisOperatingMonth.getOperatingMonthId());
	
	//工具类中格式化数据
	List<Date> dateList = ToolAction.getMyFormatDate
		(thisOperatingMonth.getOperatingStartDate(),thisOperatingMonth.getOperatingEndDate());
	
	//执行抽取
	saleDailyProductPeopleSumDAO.getSaleToSaleDailyProduct
		(dateList.get(0),dateList.get(1),thisMonthProjectsProidList);
	}

	
	//3,按 门店 员工 每个单品分组 统计 销售总金额 总数量 总奖金
	public void saveCountPubId(OperatingMonth thisOperatingMonth) {
		
		String operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		
		//执行统计 groud by 门店 员工账号  单品
		List<Object[]> saleDailyProductList 
			= saleDailyProductDAO.getSaleByBrandPulIdPro();
		
		List<SaleDailyProductPeopleVo> saleDailyProductPeopleVoList 
			= new ArrayList<SaleDailyProductPeopleVo>();
		
		//int i =0;
		///for(Object[] ObjArray : saleDailyProductList){}
		for(int i = 0;i<saleDailyProductList.size();i++){
			
			
			Object[] ObjArray = saleDailyProductList.get(i);
			
			String proId = (String) ObjArray[1];//单品id
			//根据单品id 营运月id 查找单品方案
			List<ProductProject> productProjectList = 
					productProjectDAO.findProjectByProIdAndMonthId(proId,operatingMonthId);
			
			Double saleWages = 0.0;//本流水奖金
		
			if(productProjectList.size()>0){
				
				SaleDailyProductPeopleVo saleDailyProductPeopleVo = 
						new SaleDailyProductPeopleVo();
				
				//默认一个单品只在一个单品方案中
				ProductProject productProject =	productProjectList.get(0);
				
				saleWages = 0.0;//清空
				//类型
				Integer	productProjectType = productProject.getProductProjectType();//提点
				//按百分比提成还是件 0百分比       1件
				Double productProjectVal =	productProject.getProductProjectVal();
				
				System.out.println("SaleAmt-PCashPayAmt:"+ObjArray[3].toString());
				System.out.println("SaleQty:"+ObjArray[2].toString());
				
				/*BigDecimal SaleAmt = (BigDecimal) ObjArray[3];
				BigDecimal SaleQty = (BigDecimal) ObjArray[2];
				Double d =	SaleAmt.doubleValue();
				Double c = SaleQty.doubleValue();
				
				Double a = Double.valueOf(ObjArray[3].toString());
				Double b = Double.valueOf(ObjArray[2].toString());*/
				
				if(productProjectType == 0){ //saleWages = (销售金额 - 现金券抵用的金额)*提点 
					saleWages = Arith.mul((Double) ObjArray[3],Arith.div(productProjectVal,100));
					//saleWages = (SaleAmt*(productProjectVal/100))*1;
					//Double a =	ObjArray[3]*(productProjectVal/100);
				}else{ //saleWages = 销售数量*提点 
					//saleWages =	Arith.mul(SaleQty.doubleValue(),productProjectVal);
					saleWages =	Arith.mul((Double) ObjArray[2],productProjectVal);
				}
				
				saleDailyProductPeopleVo.setEmpId(ObjArray[0].toString());
				saleDailyProductPeopleVo.setProId(ObjArray[1].toString());
				saleDailyProductPeopleVo.setOperatingMonthId(operatingMonthId);
				//saleDailyProductPeopleVo.setSaleQty(SaleQty.doubleValue());
				//saleDailyProductPeopleVo.setSaleAmt(SaleAmt.doubleValue());
				
				saleDailyProductPeopleVo.setSaleQty((Double) ObjArray[2]);
				saleDailyProductPeopleVo.setSaleAmt((Double) ObjArray[3]);
				
				saleDailyProductPeopleVo.setSaleWages(saleWages);
				saleDailyProductPeopleVo.setBraId(ObjArray[4].toString());
				
				saleDailyProductPeopleVoList.add(saleDailyProductPeopleVo);
				
			}
			
			
			System.out.println(saleDailyProductList.size());
			System.out.println(i);
		}
		
		System.out.println("a");
		//保存到SaleDailyProductPeople
		saleDailyProductPeopleDAO.saveToSaleDailyProductPeople
			(saleDailyProductPeopleVoList,operatingMonthId);
		
		System.out.println("b");
		
		//删除公共账号记录，提高查询效率
		//saleDailyProductDAO.delAllPubIdSale();
	}

	
	//4,按 门店 员工 分组 统计 销售总金额 总数量 总奖金
	public void saveCountAll(String operatingMonthId) {
		//按 门店 员工 分组 统计 销售总金额 总数量 总奖金
		List<Object[]> objList = saleDailyProductPeopleDAO.findSaleByBraIdUsr();
		
		//List<SaleDailyProductPeopleSumVo> sumList = new ArrayList<SaleDailyProductPeopleSumVo>();
		
		/*for(Object[] obj  : objList){
			
			SaleDailyProductPeopleSumVo sum = new SaleDailyProductPeopleSumVo();
			
			sum.setEmpId(obj[0].toString());
			sum.setSaleQty((Double)obj[1]);
			sum.setSaleAmt((Double)obj[2]);
			sum.setSaleWages((Double)obj[3]);
		
			
			sumList.add(sum);
		}*/
		
		
		
		//保存到saleDailyProductPeopleSum
		saleDailyProductPeopleSumDAO.saveCountAll(objList,operatingMonthId);
		
		
		
	}

	
	
	
			
	
	
}
