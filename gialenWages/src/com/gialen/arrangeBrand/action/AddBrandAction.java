package com.gialen.arrangeBrand.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.struts2.ServletActionContext;

import com.gialen.OperatingMonth.service.OperatingMonthService;
import com.gialen.arrangeBrand.service.AddBrandService;
import com.gialen.arrangeBrand.service.DwrAddBrandService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.OperatingMonth;
import com.gialen.model.PlanBrand;
import com.gialen.model.PlanMoney;
import com.gialen.model.ProProjectRelationTable;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductClass;
import com.gialen.model.ProductProject;
import com.opensymphony.xwork2.ActionSupport;

public class AddBrandAction  extends ActionSupport{
	
	private String msg;
	private String operatingMonthType;
	private OperatingMonthService operatingMonthService;
	private AddBrandService addBrandService;
	private DwrAddBrandService dwrAddBrandService;
	private int	projectType;
	private String operatingMonthId;
	private String upfiles;
	private String uploadType;
	public String addBrand(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ���ڵ���һ��Ӫ����
				operatingMonthService.findNextOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		} 
		
		//System.out.println(operatingMonthId); 
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get(0);
		}//����ǰӪ���·Ž�request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		
		int pagenum = 0;  //��ҳ�±�
		int pageSize = 20; //ÿҳ������ 
		
		//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ����Ʒ�Ʒ��� ������
		 int rownums = (int)addBrandService.findthisMonthProjectBytypeRownums
						(thisOperatingMonth.getOperatingMonthId(),projectType);
				request.setAttribute("rownums",rownums);
				
		if (request.getParameter("pagenum") != null) {  //�ӵڼ��в�ѯ
					pagenum = Integer.parseInt(request.getParameter("pagenum"));
				}
				
		// ������ҳ��
		int totalPages = 0;
		if (rownums % pageSize == 0) {
			totalPages = rownums / pageSize;
		} else {
			totalPages = rownums/ pageSize + 1;
		}
		//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ����Ʒ�Ʒ���(��ҳ)
		List<ProductProject> projectList = addBrandService.findthisMonthProjectBytypeForpage
				(thisOperatingMonth.getOperatingMonthId(),projectType,pagenum,pageSize);
		
		request.setAttribute("projectList",projectList);//��ʾ����
		request.setAttribute("rownums", rownums);		//������
		request.setAttribute("totalPages", totalPages); //��ҳ��
		request.setAttribute("pagenum", pagenum);		//��ҳ�±�
		request.setAttribute("pageSize", pageSize);		//ÿҳ������ 
		
		//��ѯ��������Ӫ���� type=1  name:����01��
				List<OperatingMonth> thisYearOperatingMonthList = 
						operatingMonthService.findThisYearAllOperatingMonth
							(operatingMonthType,ToolAction.getThisYear());
				request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		return "showProductproject";
		/*//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ����Ʒ�Ʒ���
		List<ProductProject> projectList = addBrandService.findthisMonthProjectBytype
			(thisOperatingMonth.getOperatingMonthId(),projectType);*/
	}

	public String addProductProject(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ����������ڵ�Ӫ����
				operatingMonthService.findThisOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		} 
		//System.out.println("operatingMonthId:"+operatingMonthId);
		//����id��ѯָ��Ӫ����
		OperatingMonth thisoperatingMonth = 
				operatingMonthService.findMonthById(operatingMonthId);
		request.setAttribute("thisoperatingMonth",thisoperatingMonth);
		
		/*//��ѯ��������Ӫ���� type=1  name:����01��
				List<OperatingMonth> thisYearOperatingMonthList = 
						operatingMonthService.findThisYearAllOperatingMonth
							(operatingMonthType,ToolAction.getThisYear());
				request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		//����ǰӪ���·Ž�request
		OperatingMonth thisOperatingMonth = showWeekList.get(0); 
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);*/
		
		List<ProductBrand> productBigBrandList = dwrAddBrandService.findBrandByLevel("1");
		List<ProductBrand> productMiddleBrandList = dwrAddBrandService.findBrandByLevel("2");
		List<ProductBrand> productSmallBrandList = dwrAddBrandService.findBrandByLevel("3");
		
		List<ProductClass> productBigClassList = dwrAddBrandService.findClassByLevel("1");
		List<ProductClass> productMiddleClassList = dwrAddBrandService.findClassByLevel("2");
		List<ProductClass> productSmallClassList = dwrAddBrandService.findClassByLevel("3");
		
		request.setAttribute("productBigBrandList",productBigBrandList);
		request.setAttribute("productMiddleBrandList",productMiddleBrandList);
		request.setAttribute("productSmallBrandList",productSmallBrandList);
		request.setAttribute("productBigClassList", productBigClassList);
		request.setAttribute("productMiddleClassList", productMiddleClassList);
		request.setAttribute("productSmallClassList", productSmallClassList);
		
		
		return "addProductProject";
	}
	
	public String showProProjectRelationTable(){
	
		HttpServletRequest request = ServletActionContext.getRequest();
		String productProjectId = request.getParameter("productProjectId");
		//String productProjectName =	request.getParameter("productProjectName");
		
		ProductProject productProject = 
				addBrandService.findProductProjectById(productProjectId);
		request.setAttribute("productProject",productProject);
		
		//������Ŀid���� ����Ŀ��ϸ
		List<ProProjectRelationTable> ProProjectRelationTableList 
				= addBrandService.findProProjectRelationTableByProductProjectId
					(productProjectId);
		request.setAttribute("ProProjectRelationTableList",ProProjectRelationTableList);
		
		
		return "showProProjectRelationTable";
	}
	
	//���ϵ�Ʒ��Ŀ 
	public String invalidProject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String productProjectId = request.getParameter("productProjectId");
		addBrandService.invalidProject(productProjectId);
		return addBrand();
	}
	
	//���»ָ�
	public String restoreProject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String productProjectId = request.getParameter("productProjectId");
		addBrandService.updtRestoreProject(productProjectId);
		return addBrand();
	}
	
	//�޸ĵ�Ʒ��Ŀ
	public String updtProProject(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String productProjectId = request.getParameter("productProjectId");
		
		ProductProject productProject = 
				addBrandService.findProductProjectById(productProjectId);
		request.setAttribute("productProject",productProject);
		
		//������Ŀid���� ����Ŀ��ϸ
		List<ProProjectRelationTable> ProProjectRelationTableList 
				= addBrandService.findProProjectRelationTableByProductProjectId
					(productProjectId);
		request.setAttribute("ProProjectRelationTableList",ProProjectRelationTableList);
		
		//���Ҵ��� ���� С�� ��Ʒ�� ��Ʒ�� СƷ��  
		List<ProductBrand> productBigBrandList = dwrAddBrandService.findBrandByLevel("1");
		List<ProductBrand> productMiddleBrandList = dwrAddBrandService.findBrandByLevel("2");
		List<ProductBrand> productSmallBrandList = dwrAddBrandService.findBrandByLevel("3");
		
		List<ProductClass> productBigClassList = dwrAddBrandService.findClassByLevel("1");
		List<ProductClass> productMiddleClassList = dwrAddBrandService.findClassByLevel("2");
		List<ProductClass> productSmallClassList = dwrAddBrandService.findClassByLevel("3");
		
		request.setAttribute("productBigBrandList",productBigBrandList);
		request.setAttribute("productMiddleBrandList",productMiddleBrandList);
		request.setAttribute("productSmallBrandList",productSmallBrandList);
		request.setAttribute("productBigClassList", productBigClassList);
		request.setAttribute("productMiddleClassList", productMiddleClassList);
		request.setAttribute("productSmallClassList", productSmallClassList);
		
		return "updtProProject";
	}
	
	//��ѯ��ǰӪ���� ��Ϊ��
	public String showInvalidProject(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ����������ڵ�Ӫ����
				operatingMonthService.findThisOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		}
		
		//System.out.println(operatingMonthId); 
				OperatingMonth thisOperatingMonth = null;
				if(operatingMonthId != null){
					thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
				}else{
					thisOperatingMonth = showWeekList.get(0);
				}//����ǰӪ���·Ž�request
				request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		
		int pagenum = 0;  //��ҳ�±�
		int pageSize = 20; //ÿҳ������ 
		
		//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ��������Ʒ�Ʒ��� ������
		 int rownums = (int)addBrandService.findthisMonthInvalidProjectRownums
						(thisOperatingMonth.getOperatingMonthId(),projectType);
		request.setAttribute("rownums",rownums);
		
		if (request.getParameter("pagenum") != null) {  //�ӵڼ��в�ѯ
			pagenum = Integer.parseInt(request.getParameter("pagenum"));
		}
		
		// ������ҳ��
		int totalPages = 0;
		if (rownums % pageSize == 0) {
			totalPages = rownums / pageSize;
		} else {
			totalPages = rownums / pageSize + 1;
		}
		
		//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ����Ʒ�Ʒ���(��ҳ)
		List<ProductProject> projectList = addBrandService.findthisMonthInvalidProjectForpage
			(thisOperatingMonth.getOperatingMonthId(),projectType,pagenum,pageSize);
		
		request.setAttribute("projectList",projectList);//��ʾ����
		request.setAttribute("rownums", rownums);		//������
		request.setAttribute("totalPages", totalPages); //��ҳ��
		request.setAttribute("pagenum", pagenum);		//��ҳ�±�
		request.setAttribute("pageSize", pageSize);		//ÿҳ������ 
		
		
		//��ѯ��������Ӫ���� type=1  name:����01��
		List<OperatingMonth> thisYearOperatingMonthList = 
				operatingMonthService.findThisYearAllOperatingMonth
					(operatingMonthType,ToolAction.getThisYear());
		request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		
		return "showInvalidProject";
	}
	
	//��ѯ���Ӫ���µ�Ʒ��Ŀ,�ظ��ĵ�Ʒ
	public String showRepeatProProject(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		operatingMonthId = request.getParameter("operatingMonthId");
		List<ProProjectRelationTable> relationProject = addBrandService.findRepeatProProject(operatingMonthId);
		request.setAttribute("relationProject",relationProject);
		return "showRepeatProProject";
	}
	
	//ɾ�����Ӫ���µ�Ʒ��Ŀ,�ظ��ĵ�Ʒ
	public String delRepeatProProject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] proStr = request.getParameterValues("proId");
		addBrandService.delRepeatProProject(proStr);
		return addBrand();
	}
	
	
	
	
	public String getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(String operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public OperatingMonthService getOperatingMonthService() {
		return operatingMonthService;
	}

	public void setOperatingMonthService(OperatingMonthService operatingMonthService) {
		this.operatingMonthService = operatingMonthService;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AddBrandService getAddBrandService() {
		return addBrandService;
	}

	public void setAddBrandService(AddBrandService addBrandService) {
		this.addBrandService = addBrandService;
	}

	public DwrAddBrandService getDwrAddBrandService() {
		return dwrAddBrandService;
	}

	public void setDwrAddBrandService(DwrAddBrandService dwrAddBrandService) {
		this.dwrAddBrandService = dwrAddBrandService;
	}

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

	public String getOperatingMonthId() {
		return operatingMonthId;
	}

	public void setOperatingMonthId(String operatingMonthId) {
		this.operatingMonthId = operatingMonthId;
	}
	
	
	
	
// ////Alpha 2012-8-9 ��������
public String addProduct() {

	HttpServletRequest request = ServletActionContext.getRequest();
	String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
	String thisYear = ToolAction.getThisYear();

	List<OperatingMonth> showWeekList = // ���ҵ�ǰ����������ڵ�Ӫ����
	operatingMonthService.findThisOperatingMonth(operatingMonthType,
			thisDate);

	if (showWeekList == null || showWeekList.size() <= 0) {
		msg = "�����Ӫ���°��Ż�û����д,������д!";
		return "writeMonth";
	}

	/*
	 * //����ǰӪ���·Ž�request OperatingMonth thisOperatingMonth =
	 * showWeekList.get(0);
	 * request.setAttribute("thisOperatingMonth",thisOperatingMonth);
	 * 
	 * 
	 * //System.out.println(operatingMonthType); //��ѯ��������Ӫ���� type=1
	 * name:����01�� List<OperatingMonth> thisYearOperatingMonthList =
	 * operatingMonthService.findThisYearAllOperatingMonth
	 * (operatingMonthType,ToolAction.getThisYear());
	 * request.setAttribute("thisYearOperatingMonthList"
	 * ,thisYearOperatingMonthList);
	 * 
	 * //���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰� ����Ʒ�Ʒ��� List<ProductProject>
	 * projectList = addBrandService.findthisMonthProjectBytype
	 * (thisOperatingMonth.getOperatingMonthId(),projectType);
	 * request.setAttribute("projectList",projectList);
	 */
	// System.out.println(operatingMonthId);
	OperatingMonth thisOperatingMonth = null;
	if (operatingMonthId != null) {
		thisOperatingMonth = operatingMonthService
				.findMonthById(operatingMonthId);
	} else {
		thisOperatingMonth = showWeekList.get(0);
	}// ����ǰӪ���·Ž�request
	request.setAttribute("thisOperatingMonth", thisOperatingMonth);

	int pagenum = 0; // ��ҳ�±�
	int pageSize = 20; // ÿҳ������

	// ���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰� ����Ʒ�Ʒ��� ������
	int rownums = (int) addBrandService.findthisMonthProjectBytypeRownums(
			thisOperatingMonth.getOperatingMonthId(), projectType);
	request.setAttribute("rownums", rownums);

	if (request.getParameter("pagenum") != null) { // �ӵڼ��в�ѯ
		pagenum = Integer.parseInt(request.getParameter("pagenum"));
	}

	// ������ҳ��
	int totalPages = 0;
	if (rownums % pageSize == 0) {
		totalPages = rownums / pageSize;
	} else {
		totalPages = rownums / pageSize + 1;
	}

	// ���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰� ����Ʒ�Ʒ���(��ҳ)
	List<ProductProject> projectList = addBrandService
			.findthisMonthProjectBytypeForpage(
					thisOperatingMonth.getOperatingMonthId(), projectType,
					pagenum, pageSize);

	request.setAttribute("projectList", projectList);// ��ʾ����
	request.setAttribute("rownums", rownums); // ������
	request.setAttribute("totalPages", totalPages); // ��ҳ��
	request.setAttribute("pagenum", pagenum); // ��ҳ�±�
	request.setAttribute("pageSize", pageSize); // ÿҳ������

	// ��ѯ��������Ӫ���� type=1 name:����01��
	List<OperatingMonth> thisYearOperatingMonthList = operatingMonthService
			.findThisYearAllOperatingMonth(operatingMonthType,
					ToolAction.getThisYear());
	request.setAttribute("thisYearOperatingMonthList",
			thisYearOperatingMonthList);

	return "addProduct";

}

// ////Alpha 2012-8-9 Ʒ�Ʒ�������
public String addProduct_Project() {
	HttpServletRequest request = ServletActionContext.getRequest();
	String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
	List<OperatingMonth> showWeekList = // ���ҵ�ǰ����������ڵ�Ӫ����
	operatingMonthService.findThisOperatingMonth(operatingMonthType,
			thisDate);

	if (showWeekList == null || showWeekList.size() <= 0) {
		msg = "�����Ӫ���°��Ż�û����д,������д!";
		return "writeMonth";
	}
	// //��ѯ��������Ӫ���� type=1 name:����01��
	// List<OperatingMonth> thisYearOperatingMonthList =
	// operatingMonthService.findThisYearAllOperatingMonth
	// (operatingMonthType,ToolAction.getThisYear());
	// request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
 	OperatingMonth thisoperatingMonth = operatingMonthService
 			.findMonthById(operatingMonthId);
 	request.setAttribute("thisoperatingMonth", thisoperatingMonth);
	
	
	
	

	
	
	//
	// //����ǰӪ���·Ž�request
	// OperatingMonth thisOperatingMonth = showWeekList.get(0);
	// request.setAttribute("thisOperatingMonth",thisOperatingMonth);
	List<ProductBrand> productBigBrandList = dwrAddBrandService
			.findBrandByLevel("1");
	List<ProductBrand> productMiddleBrandList = dwrAddBrandService
			.findBrandByLevel("2");
	List<ProductBrand> productSmallBrandList = dwrAddBrandService
			.findBrandByLevel("3");

	List<ProductClass> productBigClassList = dwrAddBrandService
			.findClassByLevel("1");
	List<ProductClass> productMiddleClassList = dwrAddBrandService
			.findClassByLevel("2");
	List<ProductClass> productSmallClassList = dwrAddBrandService
			.findClassByLevel("3");

	request.setAttribute("productBigBrandList", productBigBrandList);
	request.setAttribute("productMiddleBrandList", productMiddleBrandList);
	request.setAttribute("productSmallBrandList", productSmallBrandList);
	request.setAttribute("productBigClassList", productBigClassList);
	request.setAttribute("productMiddleClassList", productMiddleClassList);
	request.setAttribute("productSmallClassList", productSmallClassList);

	return "addProduct_Project";
}

// ////Alpha 2012-8-9 �����
public String addPlanMoney() {
	
	HttpServletRequest request = ServletActionContext.getRequest();
	//String operatingMonthId = request.getParameter("operatingMonthId");
	String productProjectId = request.getParameter("productProjectId");		
    String productProjectName = request.getParameter("productProjectName");
	String planMoneyType =request.getParameter("planMoneyType");	
	
//	System.out.println( "operatingMonthId "+operatingMonthId);
//	
//	System.out.println( "productProjectId "+productProjectId);
	
//	System.out.println( "planMoneyType "+planMoneyType);
	List<PlanMoney> planmoneylist=addBrandService.findPlanMoney(Integer.valueOf(planMoneyType), operatingMonthId,productProjectId);	
	request.setAttribute("planmoneylist", planmoneylist);			
	request.setAttribute("planMoneyType",planMoneyType);		
	request.setAttribute("productProjectId",productProjectId);
	request.setAttribute("productProjectName",productProjectName);
	request.setAttribute("operatingMonthId",operatingMonthId);
	return "showPlanMoney";
}



 




//alpha 2012-8-15
public String writePlanMoneySuccess(){
	//List<PlanMoney> pm_list = new ArrayList<PlanMoney>();	
	HttpServletRequest request = ServletActionContext.getRequest();
	
	Integer productProjectId=1;///��˾Ԥ�ƶ���
	
	if(request.getParameter("productProjectId")!=null)
	{
		 
		productProjectId =Integer.valueOf(request.getParameter("productProjectId"));
	}
	
	 
	String operatingMonthId =request.getParameter("operatingMonthId");
	Integer planMoneyType =Integer.valueOf(request.getParameter("planMoneyType"));	
	
//	String operatingMonthId =request.getParameter("operatingMonthId");	
	
//	System.out.println("planMoneyType1 :"+planMoneyType);
//	System.out.println("operatingMonthId1 :"+operatingMonthId);
	Workbook book;
	
	try {
		book = Workbook.getWorkbook(new File(upfiles));		
		Sheet sheet = book.getSheet(0);
		int rownum = sheet.getRows();
		String [] branchid=new String[rownum-1];
		Double [] Money=   new Double[rownum-1];
		for (int Y = 1; Y < rownum; Y++) {									
//				String branchid = sheet.getCell(0, Y).getContents();			
//			Branch bh=new Branch(branchid);				
//			PlanMoney pm =new PlanMoney();
//			pm.setPlanMoneyCount(Double.valueOf(sheet.getCell(2, Y).getContents()));
//			pm.setPlanMoneyType(1);   ////0Ϊ��˾Ԥ�� ,1Ϊ �ŵ�Ʒ�����񶨶�   
//			pm.setBranch(bh);
//			pm_list.add(pm);
			branchid[Y-1]=sheet.getCell(0, Y).getContents();
			Money[Y-1]=Double.valueOf(sheet.getCell(2, Y).getContents());
			
		}
		 addBrandService.addPlanMoney(productProjectId, branchid,Money,planMoneyType,operatingMonthId);	
		

	} catch (BiffException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
//return null;
 return null;
}



//alpha 2012-8-15
//�޸�Ʒ�Ƶ�Ʒ
public String updtProProjects(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String productProjectId = request.getParameter("productProjectId");
	
	ProductProject productProject = 
			addBrandService.findProductProjectById(productProjectId);
	request.setAttribute("productProject",productProject);
		
 
	
	 /////Ʒ������
	List<PlanBrand> Product_PlanBrandlist=addBrandService.findPlanBrandByProductProjectId_planBrandType(productProjectId,1);
/////��Ʒ������
	List<PlanBrand> ProId_PlanBrandlist=addBrandService.findPlanBrandByProductProjectId_planBrandType(productProjectId,0);
	
 
	request.setAttribute("Product_PlanBrandlist",Product_PlanBrandlist);
	request.setAttribute("ProId_PlanBrandlist",ProId_PlanBrandlist);
	
	//���Ҵ��� ���� С�� ��Ʒ�� ��Ʒ�� СƷ��  
	List<ProductBrand> productBigBrandList = dwrAddBrandService.findBrandByLevel("1");
	List<ProductBrand> productMiddleBrandList = dwrAddBrandService.findBrandByLevel("2");
	List<ProductBrand> productSmallBrandList = dwrAddBrandService.findBrandByLevel("3");
	
	List<ProductClass> productBigClassList = dwrAddBrandService.findClassByLevel("1");
	List<ProductClass> productMiddleClassList = dwrAddBrandService.findClassByLevel("2");
	List<ProductClass> productSmallClassList = dwrAddBrandService.findClassByLevel("3");
	
	request.setAttribute("productBigBrandList",productBigBrandList);
	request.setAttribute("productMiddleBrandList",productMiddleBrandList);
	request.setAttribute("productSmallBrandList",productSmallBrandList);
	request.setAttribute("productBigClassList", productBigClassList);
	request.setAttribute("productMiddleClassList", productMiddleClassList);
	request.setAttribute("productSmallClassList", productSmallClassList);
	
	OperatingMonth thisoperatingMonth = operatingMonthService
			.findMonthById(operatingMonthId);
	request.setAttribute("thisoperatingMonth", thisoperatingMonth);
	
	//String operatingMonthId = request.getParameter("operatingMonthId");
	//request.setAttribute("operatingMonthId", operatingMonthId);
	
	//System.out.println("operatingMonthId "+operatingMonthId);
	return "updtProProjects";
}




//alpha 2012-8-17 �鿴��Ŀ
public String showPlanBrand(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String productProjectId = request.getParameter("productProjectId");
	
	ProductProject productProject = 
			addBrandService.findProductProjectById(productProjectId);
	request.setAttribute("productProject",productProject);	
	
	 /////Ʒ������
	List<PlanBrand> Product_PlanBrandlist=addBrandService.findPlanBrandByProductProjectId_planBrandType(productProjectId,1);
/////��Ʒ������
	List<PlanBrand> ProId_PlanBrandlist=addBrandService.findPlanBrandByProductProjectId_planBrandType(productProjectId,0);

	request.setAttribute("Product_PlanBrandlist",Product_PlanBrandlist);
	request.setAttribute("ProId_PlanBrandlist",ProId_PlanBrandlist);
	
	
	
	return "showPlanBrand";
}

 

//alpha 2012-8-17  ����Ʒ����Ŀ
	public String invalidProProjects() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String productProjectId = request.getParameter("productProjectId");

		addBrandService.invalidProject(productProjectId);

		return addProduct();
	}

 




	
	//���»ָ�
	public String restoreProProject(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String productProjectId = request.getParameter("productProjectId");
		addBrandService.updtRestoreProject(productProjectId);
		return addProduct();
	}
	
	
	
	
	
	
	
	
////alpha 2012-8-17�鿴������Ŀ	
	
	//��ѯ��ǰӪ���� ��Ϊ��
			public String showInvalidProProject(){
				
				HttpServletRequest request = ServletActionContext.getRequest();
				String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
				String thisYear = ToolAction.getThisYear();
				
				List<OperatingMonth> showWeekList =   //���ҵ�ǰ����������ڵ�Ӫ����
						operatingMonthService.findThisOperatingMonth
							(operatingMonthType,thisDate);
				
				if (showWeekList == null || showWeekList.size()<=0) {
					 msg = "�����Ӫ���°��Ż�û����д,������д!";
					return "writeMonth";
				}
				
				//System.out.println(operatingMonthId); 
						OperatingMonth thisOperatingMonth = null;
						if(operatingMonthId != null){
							thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
						}else{
							thisOperatingMonth = showWeekList.get(0);
						}//����ǰӪ���·Ž�request
						request.setAttribute("thisOperatingMonth",thisOperatingMonth);
				
				int pagenum = 0;  //��ҳ�±�
				int pageSize = 20; //ÿҳ������ 
				
				//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ��������Ʒ�Ʒ��� ������
				 int rownums = (int)addBrandService.findthisMonthInvalidProjectRownums
								(thisOperatingMonth.getOperatingMonthId(),projectType);
				request.setAttribute("rownums",rownums);
				
				if (request.getParameter("pagenum") != null) {  //�ӵڼ��в�ѯ
					pagenum = Integer.parseInt(request.getParameter("pagenum"));
				}
				
				// ������ҳ��
				int totalPages = 0;
				if (rownums % pageSize == 0) {
					totalPages = rownums / pageSize;
				} else {
					totalPages = rownums / pageSize + 1;
				}
				
				//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ����Ʒ�Ʒ���(��ҳ)
				List<ProductProject> projectList = addBrandService.findthisMonthInvalidProjectForpage
					(thisOperatingMonth.getOperatingMonthId(),projectType,pagenum,pageSize);
				
				request.setAttribute("projectList",projectList);//��ʾ����
				request.setAttribute("rownums", rownums);		//������
				request.setAttribute("totalPages", totalPages); //��ҳ��
				request.setAttribute("pagenum", pagenum);		//��ҳ�±�
				request.setAttribute("pageSize", pageSize);		//ÿҳ������ 
				
				
				//��ѯ��������Ӫ���� type=1  name:����01��
				List<OperatingMonth> thisYearOperatingMonthList = 
						operatingMonthService.findThisYearAllOperatingMonth
							(operatingMonthType,ToolAction.getThisYear());
				request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
				
				
				return "showInvalidProProject";
			}	
	
	
	
	
	
	

public String getUpfiles() {
	return upfiles;
}

public void setUpfiles(String upfiles) {
	this.upfiles = upfiles;
}

public String getUploadType() {
	return uploadType;
}

public void setUploadType(String uploadType) {
	this.uploadType = uploadType;
}





//alpha 2012-8-17 �鿴��Ŀ
	public String show_Plan_Money(){
		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String productProjectId = request.getParameter("productProjectId");
//		
//		ProductProject productProject = 
//				addBrandService.findProductProjectById(productProjectId);
//		request.setAttribute("productProject",productProject);	
//		
//		 /////Ʒ������
//		List<PlanBrand> Product_PlanBrandlist=addBrandService.findPlanBrandByProductProjectId_planBrandType(productProjectId,1);
//	/////��Ʒ������
//		List<PlanBrand> ProId_PlanBrandlist=addBrandService.findPlanBrandByProductProjectId_planBrandType(productProjectId,0);
//	
//		request.setAttribute("Product_PlanBrandlist",Product_PlanBrandlist);
//		request.setAttribute("ProId_PlanBrandlist",ProId_PlanBrandlist);
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		//ȡ�÷����Ķ���
		List<Object[]> showmoney= addBrandService.showplanmonyes(operatingMonthId);
		
		
		//ȡ���ж����������
		List<ProductProject> projectList = addBrandService.findthisMonthProjectByProjectType_ProductProjectType(operatingMonthId,1,1);
		
	  System.out.println("projectList-size "+ projectList.size());
		request.setAttribute("projectsize",projectList.size());
		request.setAttribute("showmoney",showmoney);
		request.setAttribute("projectList",projectList);
		return "show_Money";
	}
	
	
	
	
	///alpha 2012-9-18  �������
public String addBrandGroup(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
		String thisYear = ToolAction.getThisYear();
		
		List<OperatingMonth> showWeekList =   //���ҵ�ǰ���ڵ���һ��Ӫ����
				operatingMonthService.findNextOperatingMonth
					(operatingMonthType,thisDate);
		
		if (showWeekList == null || showWeekList.size()<=0) {
			 msg = "�����Ӫ���°��Ż�û����д,������д!";
			return "writeMonth";
		} 
		
		//System.out.println(operatingMonthId); 
		OperatingMonth thisOperatingMonth = null;
		if(operatingMonthId != null){
			thisOperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
		}else{
			thisOperatingMonth = showWeekList.get(0);
		}//����ǰӪ���·Ž�request
		request.setAttribute("thisOperatingMonth",thisOperatingMonth);
		
		int pagenum = 0;  //��ҳ�±�
		int pageSize = 20; //ÿҳ������ 
		
		//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ����Ʒ�Ʒ��� ������
		 int rownums = (int)addBrandService.findthisMonthProjectBytypeRownums
						(thisOperatingMonth.getOperatingMonthId(),projectType);
				request.setAttribute("rownums",rownums);
				
		if (request.getParameter("pagenum") != null) {  //�ӵڼ��в�ѯ
					pagenum = Integer.parseInt(request.getParameter("pagenum"));
				}
				
		// ������ҳ��
		int totalPages = 0;
		if (rownums % pageSize == 0) {
			totalPages = rownums / pageSize;
		} else {
			totalPages = rownums/ pageSize + 1;
		}
		//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ����Ʒ�Ʒ���(��ҳ)
		List<ProductProject> projectList = addBrandService.findthisMonthProjectBytypeForpage
				(thisOperatingMonth.getOperatingMonthId(),projectType,pagenum,pageSize);
		
		request.setAttribute("projectList",projectList);//��ʾ����
		request.setAttribute("rownums", rownums);		//������
		request.setAttribute("totalPages", totalPages); //��ҳ��
		request.setAttribute("pagenum", pagenum);		//��ҳ�±�
		request.setAttribute("pageSize", pageSize);		//ÿҳ������ 
		
		//��ѯ��������Ӫ���� type=1  name:����01��
				List<OperatingMonth> thisYearOperatingMonthList = 
						operatingMonthService.findThisYearAllOperatingMonth
							(operatingMonthType,ToolAction.getThisYear());
				request.setAttribute("thisYearOperatingMonthList",thisYearOperatingMonthList);
		
		return "showProductproject_Group";
		/*//���ݵ�ǰӪ���� ������ 0��Ʒ���� 1Ʒ�Ʒ��� 2��𷽰�   ����Ʒ�Ʒ���
		List<ProductProject> projectList = addBrandService.findthisMonthProjectBytype
			(thisOperatingMonth.getOperatingMonthId(),projectType);*/
	}
	
	
///2012-9-18 alpha 	
public String addProductProjectGroup(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String thisDate = ToolAction.getThisDate();// �ӹ������л�õ�ǰ����
	
	List<OperatingMonth> showWeekList =   //���ҵ�ǰ����������ڵ�Ӫ����
			operatingMonthService.findThisOperatingMonth
				(operatingMonthType,thisDate);
	
	if (showWeekList == null || showWeekList.size()<=0) {
		 msg = "�����Ӫ���°��Ż�û����д,������д!";
		return "writeMonth";
	} 
	OperatingMonth thisoperatingMonth = operatingMonthService.findMonthById(operatingMonthId);
	request.setAttribute("thisoperatingMonth",thisoperatingMonth);
	List<ProductBrand> productBigBrandList = dwrAddBrandService.findBrandByLevel("1");
	List<ProductBrand> productMiddleBrandList = dwrAddBrandService.findBrandByLevel("2");
	List<ProductBrand> productSmallBrandList = dwrAddBrandService.findBrandByLevel("3");	
	List<ProductClass> productBigClassList = dwrAddBrandService.findClassByLevel("1");
	List<ProductClass> productMiddleClassList = dwrAddBrandService.findClassByLevel("2");
	List<ProductClass> productSmallClassList = dwrAddBrandService.findClassByLevel("3");	
	request.setAttribute("productBigBrandList",productBigBrandList);
	request.setAttribute("productMiddleBrandList",productMiddleBrandList);
	request.setAttribute("productSmallBrandList",productSmallBrandList);
	request.setAttribute("productBigClassList", productBigClassList);
	request.setAttribute("productMiddleClassList", productMiddleClassList);
	request.setAttribute("productSmallClassList", productSmallClassList);	
	return "addProductProject_Group";
}	




}
