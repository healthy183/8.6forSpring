package com.gialen.arrangeBrand.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gialen.arrangeBrand.service.DwrAddBrandService;
import com.gialen.dao.BranchDAO;
import com.gialen.dao.OperatingMonthDAO;
import com.gialen.dao.PlanMoneyDAO;
import com.gialen.dao.ProProjectRelationTableDAO;
import com.gialen.dao.ProductBrandDAO;
import com.gialen.dao.ProductClassDAO;
import com.gialen.dao.ProductDAO;
import com.gialen.dao.ProductProjectDAO;
import com.gialen.model.Branch;
import com.gialen.model.OperatingMonth;
import com.gialen.model.PlanBrand;
import com.gialen.model.PlanMoney;
import com.gialen.model.ProProjectRelationTable;
import com.gialen.model.Product;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductClass;
import com.gialen.model.ProductProject;

public class DwrAddBrandServiceImpl implements DwrAddBrandService {

	private ProductDAO productDAO;
	private ProductBrandDAO productBrandDAO;
	private ProductClassDAO productClassDAO;
	private ProductProjectDAO productProjectDAO;
	private ProProjectRelationTableDAO proProjectRelationTableDAO;
	private OperatingMonthDAO operatingMonthDAO;
	private BranchDAO branchDAO;
	
	private PlanMoneyDAO planMoneyDAO;
	
	
	public OperatingMonthDAO getOperatingMonthDAO() {
		return operatingMonthDAO;
	}

	public void setOperatingMonthDAO(OperatingMonthDAO operatingMonthDAO) {
		this.operatingMonthDAO = operatingMonthDAO;
	}

	public ProProjectRelationTableDAO getProProjectRelationTableDAO() {
		return proProjectRelationTableDAO;
	}

	public void setProProjectRelationTableDAO(
			ProProjectRelationTableDAO proProjectRelationTableDAO) {
		this.proProjectRelationTableDAO = proProjectRelationTableDAO;
	}

	public ProductProjectDAO getProductProjectDAO() {
		return productProjectDAO;
	}

	public void setProductProjectDAO(ProductProjectDAO productProjectDAO) {
		this.productProjectDAO = productProjectDAO;
	}

	public ProductBrandDAO getProductBrandDAO() {
		return productBrandDAO;
	}

	public void setProductBrandDAO(ProductBrandDAO productBrandDAO) {
		this.productBrandDAO = productBrandDAO;
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public ProductClassDAO getProductClassDAO() {
		return productClassDAO;
	}

	public void setProductClassDAO(ProductClassDAO productClassDAO) {
		this.productClassDAO = productClassDAO;
	}
	
	     //����Ʒ������1 2 3 ��ѯ������С��
	public List<ProductClass> findClassByLevel(String classLevel) {
		return productClassDAO.getClassByLevel(classLevel);
	}
	
	        //����Ʒ������1 2 3 ��ѯ������С��
	public Map<String, String> getClassByLevel(String classLevel) {
		List<ProductClass> productClassList	= findClassByLevel(classLevel);
		Map<String, String> map = new HashMap<String, String>();
		for(ProductClass productClass : productClassList){
			map.put(productClass.getClassId(), productClass.getClassName());
		}
		return map;
	}
	
	        
	public List<ProductBrand> findBrandByLevel(String brandLevel){
	  return  productBrandDAO.getbrandByLevel(brandLevel);
	}
	
	      //����Ʒ������1 2 3 ��ѯƷ�ƴ���С��
	public Map<String, String> getbrandByLevel(String brandLevel) {
		List<ProductBrand> productBrandList	= findBrandByLevel(brandLevel);
		
		Map<String, String> map = new HashMap<String,String>();
		for(ProductBrand productBrand: productBrandList){
			map.put(productBrand.getBrandId(), productBrand.getBrandName());
		}
		return map;
	}
	
	      //���ݸ�Ʒ�Ʋ���Ʒ�� 
	public Map<String, String> findBrandByParentBrand(String bigBrandId,String level) {
		List<ProductBrand> productBrandList = productBrandDAO.findBrandByParentBrand(bigBrandId,level);
		Map<String,String> productBrandMap = new HashMap<String,String>();
		for(ProductBrand productBrand :productBrandList){
			productBrandMap.put(productBrand.getBrandId(), productBrand.getBrandName());
		}
		return  productBrandMap;
	}

	      //���ݸ����������
	public Map<String, String> findClassByParentClass(String parentClassId,
			String level) {
		Map<String, String> productClassMap = new HashMap<String,String>();
		List<ProductClass> productClassList = productClassDAO.findClassByParentClass(parentClassId,level);
		for(ProductClass productClass :productClassList){
			productClassMap.put(productClass.getClassId(),productClass.getClassName());
		}
		return productClassMap;
	}
 
	      //��ҳ����������Ʒ����json����
	public List<Object[]> findProductReturnMap(
		String proIdKeywordVar,String barcodeKeywordVar,String proNameKeyWordVar, 
			String bigBrandVar,String middleBrandVar, String smallBrandVar, 
				String bigClassVar,String middleClassVar, String smallClassVar,
					String ProFlagStr,String ProStatusStr,String proIdClassStr) {

				//��СƷ�ƿ�ʼ�ж�,ֻȡ��С����
				if(!smallBrandVar.equals("0")){ 
					bigBrandVar  = smallBrandVar;
				}else if(!middleBrandVar.equals("0")){
					bigBrandVar  = middleBrandVar;
				}
				//��С�࿪ʼ�ж�,ֻȡ��С����
				if(!smallClassVar.equals("0")){
					bigClassVar = smallClassVar;
				}else if(!middleClassVar.equals("0")){
					bigClassVar = middleClassVar;
				}
				
				
				//ҳ�����Ѿ����ڵĵ�Ʒ 
				List<String> proIdClassStrlist = Arrays.asList(proIdClassStr.split(","));
				
				//��Ʒ����
				List<String> proFlaglist = Arrays.asList(ProFlagStr.split(","));
				//��Ʒ״̬
				List<String> ProStatuslist = Arrays.asList(ProStatusStr.split(","));
				
				List<Object[]> productList = null;
				
				if(bigBrandVar.equals("0")){
					if(bigClassVar.equals("0")){
						productList = dwrFindProduct
								(proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,
										proFlaglist,ProStatuslist,proIdClassStrlist);
					}else{
						productList = findProductByClass
								(proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,
										bigClassVar,proFlaglist,ProStatuslist,proIdClassStrlist);
					}
				}else{
					if(bigClassVar.equals("0")){
						productList = dwrFindProductByBrand
								(proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,
										bigBrandVar,proFlaglist,ProStatuslist,proIdClassStrlist);
					}else{
						productList =dwrFindProductByBrandAndClass
								(proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,
										bigBrandVar,bigClassVar,
											proFlaglist,ProStatuslist,proIdClassStrlist);
					}
				}
				
				
				List<Object[]> Repeat_list = new ArrayList<Object[]>();
				
				for(Object[] p : productList){
					for(String s : proIdClassStrlist){
						if(p[0].toString().equals(s)){
							Repeat_list.add(p);
						}
					}
				}
				
				//System.out.println(l);
				
				productList.removeAll(Repeat_list);
				
				
		return productList;
	}

	//���ݵ�Ʒ���,��Ʒ���� ��� Ʒ�� ���ҵ�Ʒ
	private List<Object[]> dwrFindProductByBrandAndClass(
			String proIdKeywordVal,String barcodeKeywordVar,String proNameKeyWordVal,
			String showbrandVal, String showClassVal,
				List<String> proFlaglist,List<String> ProStatuslist,List<String> proIdClassStrlist) {
		return productDAO.dwrFindProductByBrandAndClass
				(proIdKeywordVal,barcodeKeywordVar,
						proNameKeyWordVal,showbrandVal,showClassVal,proFlaglist,ProStatuslist,proIdClassStrlist);
	}

	//���ݵ�Ʒ���,��Ʒ���� Ʒ�� ��ѯ��Ʒ
	private List<Object[]> dwrFindProductByBrand(String proIdKeywordVal,String barcodeKeywordVar,
			String proNameKeyWordVal, String showbrandVal,
				List<String> proFlaglist,List<String> ProStatuslist,List<String> proIdClassStrlist) {
		return productDAO.dwrFindProductByBrand
				(proIdKeywordVal,barcodeKeywordVar,proNameKeyWordVal,showbrandVal,proFlaglist,ProStatuslist,proIdClassStrlist);
	}
	
	//��Ʒ������� ��� ģ����ѯ
	private List<Object[]> findProductByClass(String proIdKeywordVal,String barcodeKeywordVar,
			String proNameKeyWordVal, String showClassVal,
				List<String> proFlaglist,List<String> ProStatuslist,List<String> proIdClassStrlist) {
		return productDAO.findProductByClass
				(proIdKeywordVal,barcodeKeywordVar,proNameKeyWordVal,showClassVal,proFlaglist,ProStatuslist,proIdClassStrlist);
	}
	
	//��Ʒ������� ģ����ѯ
	private List<Object[]> dwrFindProduct(String proIdKeywordVal,String barcodeKeywordVar,
			String proNameKeyWordVal,List<String> proFlaglist,
				List<String> ProStatuslist,List<String> proIdClassStrlist) {
		return productDAO.dwrFindProduct
				(proIdKeywordVal,barcodeKeywordVar,proNameKeyWordVal,proFlaglist,ProStatuslist,proIdClassStrlist);
	}

	
	      //��� ��Ʒ��ɷ����� �͹�ϵ��
	public String addProductProject(String operatingMonthIdVar,String checkboxVarstr,
			String productProjectNameVal,String productProjectTypeVal,
			String productProjectVal,String isAddBrandWagesVal,String projectTypeVar) {
		
	/*	Date date = new Date();
		SimpleDateFormat smdt  = new SimpleDateFormat("yyyy.MM.dd");
		String todayString = smdt.format(date);*/
		
		/*//���ҽ������ڵ�Ӫ���� 
		List<OperatingMonth> operatingMonth =
				operatingMonthDAO.findThisOperatingMonth("0",todayString);
		if(operatingMonth.size()<=0){
			return "û����дӪ���°���!";
		}*/
		
		//����ָ��Ӫ����
		OperatingMonth thisOperatingMonth = operatingMonthDAO.findById(operatingMonthIdVar);
		
		//�½���Ŀ
		ProductProject productProject = new ProductProject();
		
		productProject.setProductProjectName(productProjectNameVal); //����
		productProject.setProductProjectType(Integer.valueOf(productProjectTypeVal));//0���ٷֱ����  1�������
		productProject.setProductProjectVal(Double.valueOf(productProjectVal));//���ֵ 
		productProject.setIsAddBrandWages(Integer.valueOf(isAddBrandWagesVal));//�Ƿ�����Ʒ������ 0���� 1��
		productProject.setProjectType(Integer.valueOf(projectTypeVar));//0��Ʒ����
		productProject.setProjectType(0);//״̬����0
		productProject.setProjectStatus(0);//
		
		productProject.setOperatingMonth(thisOperatingMonth);
		thisOperatingMonth.getProductProjects().add(productProject);
		productProjectDAO.save(productProject); //���� Ȼ���ȡ��ǰ��������id
		Integer ProductProjectId = productProject.getProductProjectId();
		
		String[] checkboxVarstrForString = checkboxVarstr.split(",");
		//�����Ӧ��  �ɷ�ʽ,��Ȼ������������Ч�ʸ�,���Զ���������Ч
		proProjectRelationTableDAO.saveProProjectRelationTables
				(ProductProjectId,checkboxVarstrForString);
		/*
		Product thisProduct = null;
		List<ProProjectRelationTable> tableList = new ArrayList<ProProjectRelationTable>();
		for(String productId : checkboxVarstrForString){
			
			ProProjectRelationTable proProjectRelationTable = new ProProjectRelationTable();
			
			thisProduct = productDAO.findById(productId);
			proProjectRelationTable.setProduct(thisProduct);
			thisProduct.getProProjectRelationTables().add(proProjectRelationTable);
			
			proProjectRelationTable.setProductProject(productProject);
			productProject.getProProjectRelationTables().add(proProjectRelationTable);
			
			tableList.add(proProjectRelationTable);
		}
		
		proProjectRelationTableDAO.saveAllTable(tableList);	*/
		return ProductProjectId+"";
	}

	
	      //��ѯ��ǰ��Ʒ��Ŀ�µ����е�Ʒ
	public List<Product> findProductByProject(String productProjectId) {
		return productDAO.findProductByProject(productProjectId);
	}

	      //���µ�Ʒ��Ŀ
	public void updtProductProject(String checkboxVarstr,
		String productProjectIdVar, String productProjectNameVal,
			String productProjectTypeVal, String productProjectVal,
				String isAddBrandWagesVal) {
		
		
		/*Date date = new Date();
		SimpleDateFormat smdt  = new SimpleDateFormat("yyyy.MM.dd");
		String todayString = smdt.format(date);
		
		//���ҽ������ڵ�Ӫ���� 
		List<OperatingMonth> operatingMonth =
						operatingMonthDAO.findThisOperatingMonth("0",todayString);
		if(operatingMonth.size()<=0){
					//return "û����дӪ���°���!";
			}*/
		
		//���µ�Ʒ����
		ProductProject productProject = productProjectDAO.findById(Integer.valueOf(productProjectIdVar));
		Integer proProjectId =	productProject.getProductProjectId();//���ص�Ʒ����id ��ʵ productProjectIdVar==proProjectId
		productProject.setProductProjectName(productProjectNameVal);
		productProject.setProductProjectType(Integer.valueOf(productProjectTypeVal));
		productProject.setProductProjectVal(Double.valueOf(productProjectVal));
		productProject.setIsAddBrandWages(Integer.valueOf(isAddBrandWagesVal));
		productProjectDAO.merge(productProject);
		
		//ɾ����ǰ��Ʒ��ɷ������й�ϵ��
		proProjectRelationTableDAO.delAllChlidren(proProjectId);
		String[] checkboxVarstrForString = checkboxVarstr.split(",");
		//List<String> checkboxVarList = Arrays.asList(checkboxVarstrForString);
		proProjectRelationTableDAO.saveProProjectRelationTables(proProjectId,checkboxVarstrForString);
		
	}

	     //ɾ����ǰ��Ʒ��ɷ���
	public void delPro(String productProjectId) {
		productProjectDAO.delete(productProjectDAO.findById
				(Integer.valueOf(productProjectId)));
		proProjectRelationTableDAO.delAllChlidren(Integer.valueOf(productProjectId));
		
		
	}

	public BranchDAO getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}
	// /alpha 2012-8-9����Ʒ��
	    
	public List<ProductBrand> findSmallProductReturnMap(String bigBrandVar,
			String middleBrandVar, String smallBrandVar, String proIdClassStr) {
		// String proIdKeywordVar,String barcodeKeywordVar,String
		// proNameKeyWordVar,
		// String bigBrandVar,String middleBrandVar, String smallBrandVar,
		// String bigClassVar,String middleClassVar, String smallClassVar,
		// String ProFlagStr,String ProStatusStr) {

		// ��СƷ�ƿ�ʼ�ж�,ֻȡ��С����
		if (!smallBrandVar.equals("0")) {
			bigBrandVar = smallBrandVar;
		} else if (!middleBrandVar.equals("0")) {
			bigBrandVar = middleBrandVar;
		}
		// //��С�࿪ʼ�ж�,ֻȡ��С����
		// if(!smallClassVar.equals("0")){
		// bigClassVar = smallClassVar;
		// }else if(!middleClassVar.equals("0")){
		// bigClassVar = middleClassVar;
		// }

		// //��Ʒ����
		// List<String> proFlaglist = Arrays.asList(ProFlagStr.split(","));
		// //��Ʒ״̬
		// List<String> ProStatuslist = Arrays.asList(ProStatusStr.split(","));
		// ҳ�����Ѿ����ڵ�Ʒ��
		List<String> proIdClassStrlist = Arrays
				.asList(proIdClassStr.split(","));
		List<ProductBrand> productList = null;

		// if(bigBrandVar.equals("0")){
		// if(bigClassVar.equals("0")){
		// productList = dwrFindProduct
		// (proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,proFlaglist,ProStatuslist);
		// }else{
		// productList = findProductByClass
		// (proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,bigClassVar,proFlaglist,ProStatuslist);
		// }
		// }else{
		// if(bigClassVar.equals("0")){
		// productList = dwrFindProductByBrand
		// (proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,bigBrandVar,proFlaglist,ProStatuslist);
		// }else{
		// productList =dwrFindProductByBrandAndClass
		// (proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,bigBrandVar,bigClassVar);
		// }
		// }

		productList = dwrFindProduct(bigBrandVar, middleBrandVar,
				smallBrandVar, proIdClassStrlist);

		// System.out.println("size :"+productList.size());

		return productList;

		// }
	}

	// /alpha 2012-8-10
	private List<ProductBrand> dwrFindProduct(String bigBrandVar,
			String middleBrandVar, String smallBrandVar,
			List<String> proIdClassStrlist) {
		return productDAO.dwrFindProduct(bigBrandVar, middleBrandVar,
				smallBrandVar, proIdClassStrlist);
	}

	//     
	// public List<ProductBrand> findSmallProductReturnMap(String bigBrandVar,
	// String middleBrandVar, String smallBrandVar) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	// ///alpha 2012-8-13
	public String addProduct_Project(String operatingMonthIdVar,
			String sql_product, String sql_brand, String productProjectNameVar,
			String planBrandtaskTypVar, String isAddBrandWagesVal,
			String projectTypeVar) {
		// //����ָ��Ӫ����
		// OperatingMonth thisOperatingMonth =
		// operatingMonthDAO.findById(operatingMonthIdVar);
		//
		// //�½���Ŀ
		// ProductProject productProject = new ProductProject();
		//
		// productProject.setProductProjectName(productProjectNameVal); //����
		// productProject.setProductProjectType(Integer.valueOf(productProjectTypeVal));//0���ٷֱ����
		// 1�������
		// productProject.setProductProjectVal(Double.valueOf(productProjectVal));//���ֵ
		// productProject.setIsAddBrandWages(Integer.valueOf(isAddBrandWagesVal));//�Ƿ�����Ʒ������
		// 0���� 1��
		// productProject.setProjectType(Integer.valueOf(projectTypeVar));//0��Ʒ����
		// productProject.setProjectType(0);//״̬����0
		// productProject.setProjectStatus(0);//
		//
		// productProject.setOperatingMonth(thisOperatingMonth);
		// thisOperatingMonth.getProductProjects().add(productProject);
		//
		// productProjectDAO.save(productProject); //���� Ȼ���ȡ��ǰ��������id
		// Integer ProductProjectId = productProject.getProductProjectId();
		//
		// String[] checkboxVarstrForString = checkboxVarstr.split(",");
		// //�����Ӧ��
		// proProjectRelationTableDAO.saveProProjectRelationTables
		// (ProductProjectId,checkboxVarstrForString);
		//
		// return ProductProjectId+"";
		OperatingMonth thisOperatingMonth = operatingMonthDAO.findById(operatingMonthIdVar);
		ProductProject productProject = new ProductProject();
		productProject.setProductProjectName(productProjectNameVar); // ����
		productProject.setProductProjectType(Integer.valueOf(planBrandtaskTypVar));//0��δ���� 
		// 1�������
		// productProject.setProductProjectVal(Double.valueOf(productProjectVal));//���ֵ
		  //productProject.setIsAddBrandWages(Integer.valueOf(planBrandtaskTypVar));//�Ƿ�����Ʒ������
		// 0���� 1��
		productProject.setProjectType(1);// 1Ʒ������
		//productProject.setProjectType(0);// ״̬����0
		productProject.setProjectStatus(0);//
		//productProject.setIsAddBrandWages(Integer.valueOf(planBrandtaskTypVar));//�Ƿ�������
		productProject.setOperatingMonth(thisOperatingMonth);
		thisOperatingMonth.getProductProjects().add(productProject);

		productProjectDAO.save(productProject); // ���� Ȼ���ȡ��ǰ��������id
		Integer ProductProjectId = productProject.getProductProjectId();

		// int length=getInstances(checkboxVarstrForString,"-");
		// OperatingMonth thisOperatingMonth =
		// operatingMonthDAO.findById(operatingMonthIdVar);
		List<PlanBrand> planbrandList = new ArrayList<PlanBrand>();
		String[] sql_productString = sql_product.split("-"); // //Ʒ������
		for (int i = 1; i < sql_productString.length; i++) {
			PlanBrand pb = new PlanBrand();
			String[] planbrand = sql_productString[i].split(",");
			ProductBrand Product = productProjectDAO.findById(planbrand[7]);
			//System.out.println("Keyid_product " + planbrand[7]);
			pb.setProductBrand(Product);		
			pb.setPlanBrandfinishedPoint(Double.valueOf(planbrand[3]));     //100%������
			pb.setPlanBrandfinishedQuota(Double.valueOf(planbrand[6]));//
			pb.setPlanBrandunFinishedPoint(Double.valueOf(planbrand[4]));		//δ������	
			pb.setPlanBrandfinishedType(Integer.valueOf(planbrand[5]));         //   �Ƿ�����ɶ���
			pb.setPlanBrandunFinishedPointQuota(Double.valueOf(planbrand[11]));//  δ��ɶ������
			pb.setPlanBrandfinishedPointQuota(Double.valueOf(planbrand[10]));//   ���85% �������
			pb.setPlanBrandqverQuota(Double.valueOf(planbrand[12]));          //����			
			pb.setPlanBrandqverQuotaPoint(Double.valueOf(planbrand[13]));     //�������
			// pb.setPlanBrandKeyword(planBrandKeyword);
			// pb.setPlanBrandId(planBrandId)
			// pb.setPlanBrandmainPlan();
			ProductProject productproject = productProjectDAO.findById(ProductProjectId);
			pb.setProductProject(productproject);
			productproject.getPlanBrands().add(pb);
			// pb.setPlanBrandName(planbrand[t]);
			// pb.setPlanBrands(planbrand[t]);
			pb.setPlanBrandsecondPlan(planbrand[1]);    ///����������
			pb.setPlanBrandtaskType(Integer.valueOf(planbrand[2]));		//   �Ƿ�������
			pb.setPlanBrandType(Integer.valueOf(planbrand[8]));         //   ����    0Ϊ��Ʒ��  ,1Ʒ��
			// pb.setPlanBrandKeyword(planbrand[9]);
			pb.setOperatingMonth(thisOperatingMonth);
			//thisOperatingMonth.getProductProjects().add(pb);
			productProjectDAO.save(pb);
			//planbrandList.add(pb);
			//System.out.println("--");
		}
		String[] sql_brandString = sql_brand.split("-"); // //��Ʒ����
		for (int i = 1; i < sql_brandString.length; i++) {						
			PlanBrand pb = new PlanBrand();
			String[] planbrand = sql_brandString[i].split(",");			
			if(planbrand.length<11) continue;
			Product Product = productProjectDAO.find_productById(planbrand[7]);
			//System.out.println("Keyid_brand " + planbrand[7]);
			pb.setProduct(Product);
			// pb.setProductBrand(Product);			
			pb.setPlanBrandfinishedPoint(Double.valueOf(planbrand[3]));
			pb.setPlanBrandfinishedQuota(Double.valueOf(planbrand[6]));
			pb.setPlanBrandunFinishedPoint(Double.valueOf(planbrand[4]));		
			pb.setPlanBrandfinishedType(Integer.valueOf(planbrand[5]));		
			pb.setPlanBrandqverQuota(Double.valueOf(planbrand[12]));          //����			
			pb.setPlanBrandqverQuotaPoint(Double.valueOf(planbrand[13]));     //�������
			pb.setProductBrand(Product.getProductBrand());	
			/*Product product=find_Product_Brandid_ById(checkboxVarstrForString[i]);			
			String Brandid=product.getProductBrand().getId();
			System.out.println("Brandid "+Brandid);
			pb.setProductBrand(Product);*/			
			// pb.setPlanBrandKeyword(planBrandKeyword);
			// pb.setPlanBrandId(planBrandId)
			ProductProject productproject = productProjectDAO
					.findById(ProductProjectId);
			pb.setProductProject(productproject);
 			pb.setPlanBrandunFinishedPointQuota(Double.valueOf(planbrand[11]));
			pb.setPlanBrandfinishedPointQuota(Double.valueOf(planbrand[10]));
//			pb.setPlanBrandqverQuota(Double.valueOf(planbrand[12]));
//			
//			pb.setPlanBrandqverQuotaPoint(Double.valueOf(planbrand[13]));
			productproject.getPlanBrands().add(pb);			
		//	pb.setPlanBrandmainPlan(planbrand[0]);
			// pb.setPlanBrandName(planbrand[t]);
			// pb.setPlanBrands(planbrand[t]);
			pb.setPlanBrandsecondPlan(planbrand[1]);
			pb.setPlanBrandtaskType(Integer.valueOf(planbrand[2]));
			pb.setPlanBrandunFinishedPoint(Double.valueOf(planbrand[4]));
			pb.setPlanBrandType(Integer.valueOf(planbrand[8]));
			// pb.setPlanBrandKeyword(planbrand[9]);
			pb.setOperatingMonth(thisOperatingMonth);
			//thisOperatingMonth.getProductProjects().add(pb);
			productProjectDAO.save(pb);
		//	planbrandList.add(pb);
		//	System.out.println("--");
		}
		// proProjectRelationTableDAO.saveProProject_RelationTables(83,null,planbrandList);
		return null;

	}
	
	
	
	
///////alpha 2012-8-16����Ʒ�ƣ�
 
	public String updtProduct_Project(String operatingMonthIdVar,
			String sql_product, String sql_brand, String productProjectNameVar,
			String productProjectIdVar,
			String planBrandtaskTypVar, String isAddBrandWagesVal) {				
//		System.out.println("productProjectIdVar " + productProjectIdVar);		
//		System.out.println("operatingMonthIdVar " + operatingMonthIdVar);			
//		System.out.println("sql_product " + sql_product);	
//		System.out.println("sql_brand " + sql_brand);
//		System.out.println("productProjectNameVar " + productProjectNameVar);				
//		System.out.println("productProjectIdVar " + productProjectIdVar);			
		System.out.println("��ʼɾ��");
		productProjectDAO.delAllChlidren(Integer.valueOf(productProjectIdVar));		
		 System.out.println("��ʼ����");		
		ProductProject productProject = productProjectDAO.findById(Integer.valueOf(productProjectIdVar));
		Integer proProjectId =	productProject.getProductProjectId();//���ص�Ʒ����id ��ʵ productProjectIdVar==proProjectId
		productProject.setProductProjectName(productProjectNameVar);
		
		Integer productProjectType=Integer.valueOf(planBrandtaskTypVar);
		//productProject.setProductProjectType(1);	
		
		//System.out.println("---"+planBrandtaskTypVar);
		
		  productProject.setProductProjectType(productProjectType);   //0Ϊ������
		//productProject.setProductProjectVal(Double.valueOf(productProjectVal));
		//productProject.setIsAddBrandWages(Integer.valueOf(isAddBrandWagesVal));
		productProjectDAO.merge(productProject);			
		 List<PlanMoney> pm_list=planMoneyDAO.findByProductProjectId(proProjectId, productProject.getOperatingMonth().getOperatingMonthId());			
			for(PlanMoney pm_s:pm_list){
				
				if(productProjectType==1)
				{
				PlanMoney pm =planMoneyDAO.findById(pm_s.getPlanMoneyId());				
				pm.setProductProjectName(productProjectNameVar);
				planMoneyDAO.merge(pm);///���¶����� ProductProjectName�� �������ƶ�Ӧ
				
				}
				
				
				else{
					
					PlanMoney pm =planMoneyDAO.findById(pm_s.getPlanMoneyId());	
					
					planMoneyDAO.delete(pm);
					
					System.out.println("������ ɾ������");
				}
				
				
			}
		//Integer ProductProjectId = productProject.getProductProjectId();
 		OperatingMonth thisOperatingMonth = operatingMonthDAO.findById(operatingMonthIdVar);		
		List<PlanBrand> planbrandList = new ArrayList<PlanBrand>();
		String[] sql_productString = sql_product.split("-"); // //Ʒ���޸�
		for (int i = 1; i < sql_productString.length; i++) {
			PlanBrand pb = new PlanBrand();
			String[] planbrand = sql_productString[i].split(",");			
			ProductBrand Product = productProjectDAO.findById(planbrand[7]);
//	        System.out.println("Keyid_product " + planbrand[7]);
			pb.setProductBrand(Product);		
			pb.setPlanBrandfinishedPoint(Double.valueOf(planbrand[3]));
			pb.setPlanBrandfinishedQuota(Double.valueOf(planbrand[6]));
			pb.setPlanBrandunFinishedPoint(Double.valueOf(planbrand[4]));			
			pb.setPlanBrandfinishedType(Integer.valueOf(planbrand[5]));
			pb.setPlanBrandqverQuota(Double.valueOf(planbrand[12]));          			
			pb.setPlanBrandqverQuotaPoint(Double.valueOf(planbrand[13]));
//			pb.setPlanBrandunFinishedPointQuota(Double.valueOf(planbrand[11]));
			pb.setPlanBrandfinishedPointQuota(Double.valueOf(planbrand[10]));
			ProductProject productproject = productProjectDAO.findById(Integer.valueOf(productProjectIdVar));
			pb.setProductProject(productproject);
			productproject.getPlanBrands().add(pb);
			pb.setPlanBrandsecondPlan(planbrand[1]);
			pb.setPlanBrandtaskType(Integer.valueOf(planbrand[2]));		
			pb.setPlanBrandType(Integer.valueOf(planbrand[8]));
			pb.setOperatingMonth(thisOperatingMonth);
			productProjectDAO.save(pb);
		}
		String[] sql_brandString = sql_brand.split("-"); // //��Ʒ�޸�
		for (int i = 1; i < sql_brandString.length; i++) {
			PlanBrand pb = new PlanBrand();
			String[] planbrand = sql_brandString[i].split(",");			
			if(planbrand.length<11) continue;			
//			System.out.println("sql_brandString_size: "+sql_brandString.length+" ");
//			System.out.println("size: "+planbrand.length);
// 			System.out.println(""+planbrand[7]);
			Product Product = productProjectDAO.find_productById(planbrand[7]);			
			pb.setProductBrand(Product.getProductBrand());
//	        System.out.println("Keyid_brand " + planbrand[7]);
			pb.setProduct(Product);		
			pb.setPlanBrandfinishedPoint(Double.valueOf(planbrand[3]));
			pb.setPlanBrandfinishedQuota(Double.valueOf(planbrand[6]));
			pb.setPlanBrandunFinishedPoint(Double.valueOf(planbrand[4]));		
			pb.setPlanBrandfinishedType(Integer.valueOf(planbrand[5]));
			pb.setPlanBrandqverQuota(Double.valueOf(planbrand[12]));          			
			pb.setPlanBrandqverQuotaPoint(Double.valueOf(planbrand[13]));
// 	 	    pb.setPlanBrandunFinishedPointQuota(Double.valueOf(planbrand[11]));
  			pb.setPlanBrandfinishedPointQuota(Double.valueOf(planbrand[10]));
			ProductProject productproject = productProjectDAO.findById(Integer.valueOf(productProjectIdVar));
			pb.setProductProject(productproject);
			productproject.getPlanBrands().add(pb);			
			pb.setPlanBrandsecondPlan(planbrand[1]);
			pb.setPlanBrandtaskType(Integer.valueOf(planbrand[2]));
			pb.setPlanBrandunFinishedPoint(Double.valueOf(planbrand[4]));
			pb.setPlanBrandType(Integer.valueOf(planbrand[8]));
			pb.setOperatingMonth(thisOperatingMonth);	
			productProjectDAO.save(pb);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return null;

	}
	
	
	
	
	
	
	
	
	
	
	public Branch findbraid( String braid){
				
		return  branchDAO.findById(braid);
	}

	public PlanMoneyDAO getPlanMoneyDAO() {
		return planMoneyDAO;
	}

	public void setPlanMoneyDAO(PlanMoneyDAO planMoneyDAO) {
		this.planMoneyDAO = planMoneyDAO;
	}

	
	   //alpha  2012-9-18 ��� �����ɷ����� �͹�ϵ��
		public String addProductProject_Group(String operatingMonthIdVar,String checkboxVarstr,
				String productProjectNameVal,String productProjectTypeVal,
				String productProjectVal,String isAddBrandWagesVal,String projectTypeVar) {
	 
			//����ָ��Ӫ����
			OperatingMonth thisOperatingMonth = operatingMonthDAO.findById(operatingMonthIdVar);
			
			//�½���Ŀ
			ProductProject productProject = new ProductProject();
			
			productProject.setProductProjectName(productProjectNameVal); //����
			productProject.setProductProjectType(Integer.valueOf(productProjectTypeVal));//0���ٷֱ����  1�������
			productProject.setProductProjectVal(Double.valueOf(productProjectVal));//���ֵ 
			productProject.setIsAddBrandWages(Integer.valueOf(isAddBrandWagesVal));//�Ƿ�����Ʒ������ 0���� 1��
			//productProject.setProjectType(Integer.valueOf(projectTypeVar));//0��Ʒ����
			productProject.setProjectType(2);//�������
			productProject.setProjectStatus(0);//
			
			productProject.setOperatingMonth(thisOperatingMonth);
			thisOperatingMonth.getProductProjects().add(productProject);
		    productProjectDAO.save(productProject); //���� Ȼ���ȡ��ǰ��������id
			Integer ProductProjectId = productProject.getProductProjectId();
			
			String[] checkboxVarstrForString = checkboxVarstr.split("-");
			//�����Ӧ��  �ɷ�ʽ,��Ȼ������������Ч�ʸ�,���Զ���������Ч
			//proProjectRelationTableDAO.saveProProjectRelationTables_Group(ProductProjectId,checkboxVarstrForString);
			 
			
			
			
			for (int i = 1; i < checkboxVarstrForString.length; i++) {

				String[] RewardQverQuota = checkboxVarstrForString[i].split(",");

//				planBrandRelationTableDAO.saveRewardQverQuota(RewardQverQuota, BraId,
//						operatingMonthId);
				
				//System.out.println("Brandid "+Brandid);	
				//System.out.println(":"+RewardQverQuota[0] +" "+RewardQverQuota[1]);
				
				 proProjectRelationTableDAO.saveProProjectRelationTables_Group(ProductProjectId,RewardQverQuota);
			}
			
			
			
			
			
			
			return ProductProjectId+"";
		}
		 
}
