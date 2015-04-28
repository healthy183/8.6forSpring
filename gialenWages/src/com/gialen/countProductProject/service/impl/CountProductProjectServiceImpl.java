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

	     //ִ�в���ͳ���ϸ�Ӫ����ÿ��Ա���������ܽ�� ���
	public void saveThisMonthPsnaccountMonthSaleCountLink(
			OperatingMonth thisOperatingMonth,String unitcode) {
		//Ӫ����id 
		String thisMonthId = thisOperatingMonth.getOperatingMonthId();
		//�������и�ʽ������
		List<Date> dateList = ToolAction.getMyFormatDate
				(thisOperatingMonth.getOperatingStartDate(),thisOperatingMonth.getOperatingEndDate());
		
		//��ѯ��ǰ�����������ŵ� 
		List<OrgstdStruct> orgstdStructDAOList = 
				orgstdStructDAO.findStoreByAreaUnitCode(unitcode,24);
		
		/*//��ѯ��Ѷ���������ŵ�
		List<Branch> branchList = branchDAO.findNowBrand();*/
		/*branchList.clear();
		  branchList.add(branchDAO.findById("02002"));*/
		
		//��ѯ��������е�Ʒ�����е�Ʒ������ 
		List<String> thisMonthProjectsProidList = 
				productDAO.findThisMonthProjectsProid(thisMonthId);
		
		//��ѯ��������е�Ʒ���������м�� 
		List<ProProjectRelationTable> relationTableList = 
			proProjectRelationTableDAO.findByProIdAndMonthId
				(thisMonthProjectsProidList,thisMonthId);
		
		Psnaccount	publicPsnaccount =	 //��ȡ�����˺�
				(Psnaccount) psnaccountDAO.findByEmployeeid("00000").get(0);
		
		//��Ż���PsnaccountMonthProjectDetailsLink�� list
		List<PsnaccountMonthProjectDetailsLink> allLink = 
				new LinkedList<PsnaccountMonthProjectDetailsLink>();
		
		for(OrgstdStruct thisOrgstdStruct : orgstdStructDAOList){
			/*//���ݼ�Ѷ�ŵ��� ��ѯ�м����Ϣ
			List<Corresponding>  correspondingList = 
					correspondingDAO.findCorrespondingByBrandId(branch.getBraId());
			if(correspondingList.size() == 0){
				continue; //����ŵ�û�ж�Ӧ,������
			}*/
			//����hr�ŵ��� ��ѯ�м���Ѷ�ŵ�
			Branch branch = correspondingDAO.findBranchByUnitid(thisOrgstdStruct.getUnitid());
			if(branch == null){
				continue; //����ŵ�û�ж�Ӧ,������
			}
			
			System.out.println("��ʼ��!");
			/*//����hr�ŵ꣬��ȡ�ŵ�������Ա��
			List<Psnaccount> psnaccountList = psnaccountDAO.getThisStorePerson(thisOrgstdStruct.getUnitid());*/
			
			//�Ӽ�Ѷϵͳ��ǰ�ŵ�������ˮ�в���Ա��,������employeeid���hrԱ�� (���������˺�)
			 List<Psnaccount>  psnaccountList = psnaccountDAO.getThisStorePersonBySaleDailyYymm
				(dateList.get(0),dateList.get(1),branch.getBraId(),thisMonthId,thisMonthProjectsProidList);
			//psnaccountList.add(publicPsnaccount);//��ӹ����˺ŵ��ŵ�Ա��list
			
			//������ǰ�ŵ깫���˺�javaBean
			/**/PsnaccountMonthProjectDetailsLink parentPublicAccount =	newparentPublicAccount
					(thisOrgstdStruct,branch,publicPsnaccount,thisOperatingMonth);
			
			//��ѯ�ϸ�Ӫ���� ���ŵ�  �����н���0��,�����ǵ�Ʒ��Ŀ�е����ۼ�¼ 
			List<SaleDailyYymm>	saleDailyYymmList =  
				saleDailyYymmDAO.findThisMonthSale(dateList.get(0),dateList.get(1),
					branch.getBraId(),thisMonthId,thisMonthProjectsProidList);
			
			for(Psnaccount psnaccount : psnaccountList){ //����Ա��
				//���� ��ǰӪ���� ��ǰԱ�� ����� ������ javaBean
				PsnaccountMonthProjectDetailsLink  parentLink =	
						newparentPublicAccount(thisOrgstdStruct,branch,psnaccount,thisOperatingMonth);
				//hrԱ�����
				String thisEmployeeid =	psnaccount.getEmployeeid();
				
				//�������ۼ�¼ 
				for(int i =0;i<saleDailyYymmList.size();i++){  
					//��ǰ��ˮ��¼
					SaleDailyYymm thissaleDailyYymm = saleDailyYymmList.get(i);
					String saleEmployeeid = thissaleDailyYymm.getEmployee().getPersonalId();//��ǰ��ˮԱ��id	
					//����ǵ�ǰԱ�����۵�
					if(thisEmployeeid.equals(saleEmployeeid)){
						newChlidlink(thissaleDailyYymm,thisOperatingMonth,thisOrgstdStruct,branch,psnaccount,parentLink,saleDailyYymmList,i,relationTableList);
					//�ж��Ƿ�Ϊ�����˺�
					}else if("00000".equals(saleEmployeeid)){
						newChlidlink(thissaleDailyYymm,thisOperatingMonth,thisOrgstdStruct,branch,psnaccount,parentPublicAccount,saleDailyYymmList,i,relationTableList);
					}/**/
				}
				//��Ա������javabean��ӵ�list
				allLink.add(parentLink);
			}
			allLink.add(parentPublicAccount);
		}
		
		System.out.println("���ͳ��!");
		//
		for(PsnaccountMonthProjectDetailsLink thisParentLink : allLink){
			
			 Integer saleNum = 0;
			 Double saleCount = 0.0;
			 Double saleWages = 0.0;
			
		    Set<PsnaccountMonthProjectDetailsLink> thisLinkSet = 
		    		thisParentLink.getPsnaccountMonthProjectDetailsLinks();
		    
		    	for(PsnaccountMonthProjectDetailsLink childLink : thisLinkSet){
		    		saleNum = childLink.getSaleNum()+saleNum; //��������������
		    		saleCount = Arith.add(childLink.getSaleCount(), saleCount);//���������۽��
		    		saleWages = Arith.add(childLink.getSaleWages(),saleWages);//���������۽���
		    	}
		    	
		    	thisParentLink.setSaleNum(saleNum);
		    	thisParentLink.setSaleCount(saleCount);
		    	thisParentLink.setSaleWages(saleWages);
		}
		
		psnaccountMonthProjectDetailsLinkDAO.saveAllLink(allLink);
	}

	//����Ա�� ���ܱ�
	private PsnaccountMonthProjectDetailsLink newparentPublicAccount(
			OrgstdStruct thisOrgstdStruct, Branch branch,
			Psnaccount publicPsnaccount, OperatingMonth thisOperatingMonth) {

		//��ǰ�ŵ깫���˺� ��ǰӪ���� �����ܽ�� ������ �ܽ���
		PsnaccountMonthProjectDetailsLink parentPublicAccount = new PsnaccountMonthProjectDetailsLink();
		
		parentPublicAccount.setOrgstdStruct(thisOrgstdStruct); //hr�ŵ�һ�Զ�
		thisOrgstdStruct.getPsnaccountMonthProjectDetailsLinks().add(parentPublicAccount);
		
		parentPublicAccount.setBranch(branch); //��Ѷ�ŵ� һ�Զ��ϵ 
		branch.getPsnaccountMonthProjectDetailsLinks().add(parentPublicAccount);
		
		parentPublicAccount.setPsnaccount(publicPsnaccount); //hrԱ��  һ�Զ��ϵ
		publicPsnaccount.getPsnaccountMonthProjectDetailsLinks().add(parentPublicAccount);
		
		parentPublicAccount.setOperatingMonth(thisOperatingMonth); //Ӫ���� һ�Զ��ϵ
		thisOperatingMonth.getPsnaccountMonthProjectDetailsLinks().add(parentPublicAccount);
		parentPublicAccount.setSaleCount(0.0);//�����ܽ��
		parentPublicAccount.setSaleWages(0.0);//�����ܽ���
		parentPublicAccount.setSaleNum(0);//��������
		parentPublicAccount.setLinktype(0);//����0
		System.out.println("һ�� parent������!");
		return parentPublicAccount;
	}
	
	//������ϸ ��Ʒ������ϸjavaBean
	private void newChlidlink(SaleDailyYymm thissaleDailyYymm,OperatingMonth thisOperatingMonth,
			OrgstdStruct thisOrgstdStruct,Branch branch,Psnaccount psnaccount,
				PsnaccountMonthProjectDetailsLink  parentLink,
					List<SaleDailyYymm> saleDailyYymmList,int i,
						List<ProProjectRelationTable> relationTableList){
		//�����ˮ��Ʒ   
		Product thisPro = thissaleDailyYymm.getId().getProduct();
		/*//��ѯ�õ�Ʒ �� ��ǰӪ���� �Ƿ���ĳ����Ʒ������
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
		
		//����õ�Ʒ�з��� ��ִ��ͳ��
		//if(proProjectRelationTableList.size()>0){}
			//��Ʒ�ڵ�Ʒ���������ظ���  ���Ի����Ψһ�ķ���
			//ProductProject productProject =	proProjectRelationTableList.get(0).getProductProject();
			//Ӫ������ Ա��ÿ����Ʒ���۽�� ���� ���� ��ϸ�� 
			PsnaccountMonthProjectDetailsLink childLink = new PsnaccountMonthProjectDetailsLink();
			
			Double saleCount = thissaleDailyYymm.getSaleAmt();    //���۽��
			Double saleNum = thissaleDailyYymm.getSaleQty();    //��������
			Double pcashPayAmt = thissaleDailyYymm.getPcashPayAmt();//�ֽ�ȯ���õĽ��
			
			childLink.setSaleNum(saleNum.intValue());//������������ 
			childLink.setSaleCount(saleCount); //���۽��
			childLink.setPcashPayAmt(pcashPayAmt);//�ֽ�ȯ���õĽ��
			
			Double saleWages = 0.0;//����ˮ����
			Double productProjectVal = productProject.getProductProjectVal();//���
			
			//���ٷֱ���ɻ��Ǽ� 0�ٷֱ�       1��
			Integer productProjectType = productProject.getProductProjectType();
			if(productProjectType == 0){ //saleWages = (���۽�� - �ֽ�ȯ���õĽ��)*��� 
				saleWages = Arith.mul(Arith.sub(saleCount, pcashPayAmt),Arith.div(productProjectVal,100));
			}else{ //saleWages = ��������*��� 
				saleWages =	Arith.mul(saleNum,productProjectVal);
			}
			//�������
			childLink.setSaleWages(saleWages);
			
			childLink.setOrgstdStruct(thisOrgstdStruct); //hr�ŵ�һ�Զ�
			thisOrgstdStruct.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			childLink.setBranch(branch); //��Ѷ�ŵ� һ�Զ��ϵ 
			branch.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setProductProject(productProject); //��Ʒ����
			productProject.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setPsnaccount(psnaccount); //hrԱ�� һ�Զ�
			psnaccount.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setSaleDailyYymm(thissaleDailyYymm);//��ˮһ�Զ�
			thissaleDailyYymm.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setProduct(thisPro); //��Ʒһ�Զ�
			thisPro.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			//��øõ�Ʒ����СƷ�� 
			ProductBrand productBrand = thisPro.getProductBrand();
			childLink.setProductBrand(productBrand);//СƷ��һ�Զ�
			productBrand.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setOperatingMonth(thisOperatingMonth); //Ӫ���� һ�Զ�
			thisOperatingMonth.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			
			childLink.setPsnaccountMonthProjectDetailsLink(parentLink);//���ø��� һ�Զ�
			parentLink.getPsnaccountMonthProjectDetailsLinks().add(childLink);
			childLink.setLinktype(1);//��ϸ1
		
		}	
		//�ж����Ƴ� ��ˮ
		saleDailyYymmList.remove(thissaleDailyYymm);
		i--;//���µ���
		System.out.println("����һ��");
	}

	
	      //��ѯ�ϸ�Ӫ���µĵ�Ա����Ʒ������ɱ�
	public List<PsnaccountMonthProjectDetailsLink> findLastMonthPsnaccountMonthSaleCountLink(
			String operatingMonthId) {
		return psnaccountMonthProjectDetailsLinkDAO.findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId);
	}

	        //��ѯ�ϸ�Ӫ���µĵ�ָ������Ա����Ʒ������ɱ�
	public List<PsnaccountMonthProjectDetailsLink> findLastMonthPsnaccountMonthSaleCountLink(
			String operatingMonthId, String unitcode) {
		return psnaccountMonthProjectDetailsLinkDAO.
				findLastMonthPsnaccountMonthSaleCountLink(operatingMonthId,unitcode);
	}

	//��ѯ��һ���½������ݵ�ͳ��
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

	//1,����м��
	public void delMiddleTable() {
		saleDailyProductPeopleSumDAO.delMiddleTable();
	}

	//��������ˮ��SaleDailyYymm.java�г�ȡ��Ʒ��Ŀ�к��е�������ˮ��saleDaily_product
	public void getSaleToSaleDailyProduct(OperatingMonth thisOperatingMonth) {
		//��ѯ��������е�Ʒ�����е�Ʒ������ 
	List<String> thisMonthProjectsProidList = 
		productDAO.findThisMonthProjectsProid(thisOperatingMonth.getOperatingMonthId());
	
	//�������и�ʽ������
	List<Date> dateList = ToolAction.getMyFormatDate
		(thisOperatingMonth.getOperatingStartDate(),thisOperatingMonth.getOperatingEndDate());
	
	//ִ�г�ȡ
	saleDailyProductPeopleSumDAO.getSaleToSaleDailyProduct
		(dateList.get(0),dateList.get(1),thisMonthProjectsProidList);
	}

	
	//3,�� �ŵ� Ա�� ÿ����Ʒ���� ͳ�� �����ܽ�� ������ �ܽ���
	public void saveCountPubId(OperatingMonth thisOperatingMonth) {
		
		String operatingMonthId = thisOperatingMonth.getOperatingMonthId();
		
		//ִ��ͳ�� groud by �ŵ� Ա���˺�  ��Ʒ
		List<Object[]> saleDailyProductList 
			= saleDailyProductDAO.getSaleByBrandPulIdPro();
		
		List<SaleDailyProductPeopleVo> saleDailyProductPeopleVoList 
			= new ArrayList<SaleDailyProductPeopleVo>();
		
		//int i =0;
		///for(Object[] ObjArray : saleDailyProductList){}
		for(int i = 0;i<saleDailyProductList.size();i++){
			
			
			Object[] ObjArray = saleDailyProductList.get(i);
			
			String proId = (String) ObjArray[1];//��Ʒid
			//���ݵ�Ʒid Ӫ����id ���ҵ�Ʒ����
			List<ProductProject> productProjectList = 
					productProjectDAO.findProjectByProIdAndMonthId(proId,operatingMonthId);
			
			Double saleWages = 0.0;//����ˮ����
		
			if(productProjectList.size()>0){
				
				SaleDailyProductPeopleVo saleDailyProductPeopleVo = 
						new SaleDailyProductPeopleVo();
				
				//Ĭ��һ����Ʒֻ��һ����Ʒ������
				ProductProject productProject =	productProjectList.get(0);
				
				saleWages = 0.0;//���
				//����
				Integer	productProjectType = productProject.getProductProjectType();//���
				//���ٷֱ���ɻ��Ǽ� 0�ٷֱ�       1��
				Double productProjectVal =	productProject.getProductProjectVal();
				
				System.out.println("SaleAmt-PCashPayAmt:"+ObjArray[3].toString());
				System.out.println("SaleQty:"+ObjArray[2].toString());
				
				/*BigDecimal SaleAmt = (BigDecimal) ObjArray[3];
				BigDecimal SaleQty = (BigDecimal) ObjArray[2];
				Double d =	SaleAmt.doubleValue();
				Double c = SaleQty.doubleValue();
				
				Double a = Double.valueOf(ObjArray[3].toString());
				Double b = Double.valueOf(ObjArray[2].toString());*/
				
				if(productProjectType == 0){ //saleWages = (���۽�� - �ֽ�ȯ���õĽ��)*��� 
					saleWages = Arith.mul((Double) ObjArray[3],Arith.div(productProjectVal,100));
					//saleWages = (SaleAmt*(productProjectVal/100))*1;
					//Double a =	ObjArray[3]*(productProjectVal/100);
				}else{ //saleWages = ��������*��� 
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
		//���浽SaleDailyProductPeople
		saleDailyProductPeopleDAO.saveToSaleDailyProductPeople
			(saleDailyProductPeopleVoList,operatingMonthId);
		
		System.out.println("b");
		
		//ɾ�������˺ż�¼����߲�ѯЧ��
		//saleDailyProductDAO.delAllPubIdSale();
	}

	
	//4,�� �ŵ� Ա�� ���� ͳ�� �����ܽ�� ������ �ܽ���
	public void saveCountAll(String operatingMonthId) {
		//�� �ŵ� Ա�� ���� ͳ�� �����ܽ�� ������ �ܽ���
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
		
		
		
		//���浽saleDailyProductPeopleSum
		saleDailyProductPeopleSumDAO.saveCountAll(objList,operatingMonthId);
		
		
		
	}

	
	
	
			
	
	
}
