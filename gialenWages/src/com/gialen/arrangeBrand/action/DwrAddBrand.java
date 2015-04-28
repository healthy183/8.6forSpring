package com.gialen.arrangeBrand.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.directwebremoting.io.FileTransfer;

import net.sf.json.JSONArray;

import com.gialen.arrangeBrand.service.DwrAddBrandService;
import com.gialen.main.action.ToolAction;
import com.gialen.model.Product;
import com.gialen.model.ProductBrand;

public class DwrAddBrand {

	private	DwrAddBrandService dwrAddBrandService;
	private String upfiles;
	public String findProductByClassAndKeyWord(String proIdKeywordVal, 
			String proNameKeyWordVal,String brandLevelVal,String showbrandVal,
				   String classLevelVal,String showClassVal){
	
		List productList = null;
		if(productList.size() == 0){
			return "û�ж�Ӧ��Ʒ,��ȷ����Ĳ�ѯ����!";
		}
		
		List<Product> list = new ArrayList<Product>(); 
		list.addAll(productList);
		
		JSONArray jsarry = new JSONArray();
		jsarry = JSONArray.fromObject(list);
		String msg  = jsarry.toString();
		return msg;
		
	}
	
	//����Ʒ������1 2 3 ��ѯƷ�ƴ���С��
	public Map<String,String> getbrandByLevel(String brandLevel){
		Map<String,String> mapBrandLevel = dwrAddBrandService.getbrandByLevel(brandLevel);
		return mapBrandLevel;
	}
	//����Ʒ������1 2 3 ��ѯ������С��
	public Map<String,String> getClassByLevel(String classLevel){
		Map<String,String> mapClassLevel = dwrAddBrandService.getClassByLevel(classLevel);
		return mapClassLevel;
	}

	//���ݸ�Ʒ�Ʋ���Ʒ�� 
	public Map<String,String> findBrandByParentBrand(String bigBrandId,String level){
		return dwrAddBrandService.findBrandByParentBrand(bigBrandId,level);
	}
	
	//���ݸ����������
	public  Map<String,String> findClassByParentClass(String parentClassId,String level){
		return dwrAddBrandService.findClassByParentClass(parentClassId,level);
	}
	//��ҳ����������Ʒ����json����
	public String findProductForjson
		(String proIdKeywordVar,String barcodeKeywordVar,String proNameKeyWordVar,
				String bigBrandVar,String middleBrandVar,String smallBrandVar,
					String bigClassVar,String middleClassVar,String smallClassVar,
						String ProFlagStr,String ProStatusStr,String proIdClassStr){
	
	/*System.out.println(ProFlagStr);
	System.out.println(ProStatusStr);
	System.out.println(proIdClassStr);*/
	
	List<Object[]> productList = null;
	productList  = dwrAddBrandService.findProductReturnMap
		(proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,bigBrandVar,middleBrandVar,smallBrandVar,
			bigClassVar,middleClassVar,smallClassVar,ProFlagStr,ProStatusStr,proIdClassStr);
	
	
	
	String msg  = "û�в�ѯ����Ӧ��Ʒ���ߵ�Ʒ�Ѿ�������ҳ����,��ȷ�����Ĳ�ѯ����!";
	
	if(productList.size()>0){
	
		List<Object[]> list = new ArrayList<Object[]>(); 
		list.addAll(productList);
		
		JSONArray jsarry = new JSONArray();
		jsarry = JSONArray.fromObject(list);
		msg = jsarry.toString();
	}
	
	return msg;
	}
	//���浥Ʒ����
	public String addProductProject(String operatingMonthIdVar,String checkboxVarstr,
			String productProjectNameVal,String productProjectTypeVal,
				String productProjectVal,String isAddBrandWagesVal,String projectTypeVar){
		return dwrAddBrandService.addProductProject(operatingMonthIdVar,checkboxVarstr,
				productProjectNameVal,productProjectTypeVal,
					productProjectVal,isAddBrandWagesVal,projectTypeVar);
	}
	
	//��ѯ��ǰ��Ʒ��Ŀ�µ����е�Ʒ
	public String findProductByProject(String productProjectId){
		
		List<Product> productList = dwrAddBrandService.findProductByProject(productProjectId);
		
		String jsonProduct = null;
		if(productList.size()>0){
			List<Product> list = new ArrayList<Product>(); 
			list.addAll(productList);
			
			JSONArray jsarry = new JSONArray();
			jsarry = JSONArray.fromObject(list);
			jsonProduct = jsarry.toString();
			
			/*List<Product> list = new ArrayList<Product>(); 
			list.addAll(productList);
			
			JSONArray jsarry = new JSONArray();
			jsarry = JSONArray.fromObject(list);
			msg = jsarry.toString();*/
		}else{
			jsonProduct = "�õ�Ʒ����û�е�Ʒ����,�����!";
		}
		return jsonProduct;
	}
	
	//���µ�Ʒ��Ŀ
	public void updtProductProject(String checkboxVarstr,String productProjectIdVar,
			String productProjectNameVal,String productProjectTypeVal,
			String productProjectVal,String isAddBrandWagesVal){
		
		 dwrAddBrandService.updtProductProject(checkboxVarstr,productProjectIdVar,
				productProjectNameVal,productProjectTypeVal,
				productProjectVal,isAddBrandWagesVal);
	}
	
	//ɾ����Ʒ��Ŀ
	public void delPro(String productProjectId){
		dwrAddBrandService.delPro(productProjectId);
	}
	
	
	public DwrAddBrandService getDwrAddBrandService() {
		return dwrAddBrandService;
	}

	public void setDwrAddBrandService(DwrAddBrandService dwrAddBrandService) {
		this.dwrAddBrandService = dwrAddBrandService;
	}
	// //Alpha 2012-8-9 ��ѰƷ��
		public String findSmallProduct(String bigBrandVar, String middleBrandVar,String smallBrandVar, String proIdClassStr) {

			//  System.out.println("abc");
			// System.out.println(ProStatusStr);

			List<ProductBrand> productList = null;
			productList = dwrAddBrandService.findSmallProductReturnMap(bigBrandVar,middleBrandVar,smallBrandVar,proIdClassStr);

			String msg = "û�ж�ӦƷ��,��ȷ�����Ĳ�ѯ����!";

			if (productList.size() > 0) {

				List<ProductBrand> list = new ArrayList<ProductBrand>();
				list.addAll(productList);

				JSONArray jsarry = new JSONArray();
				jsarry = JSONArray.fromObject(list);
				msg = jsarry.toString();
			}

			return msg;
		}
		
		
	//alpha 2012-8-11 	
		public String addProductProject( ) {
			  List<ProductBrand> planBrand; 
//			return dwrAddBrandService.addProductProject(checkboxVarstr,
//					thisTableId, productProjectNameVal, productProjectTypeVal,
//					productProjectVal, isAddBrandWagesVal);
			  return null;
		}
		
		
		
		
		
		
	 
	//////Alpha 2012-8-13
		//����Ʒ�Ʒ���
		public String addProduct_Project(String operatingMonthIdVar,String sql,String sql_brand,String productProjectNameVar,String planBrandtaskType ){
			
			
			// System.out.println("ProjectName :"+productProjectNameVar);
			return dwrAddBrandService.addProduct_Project(operatingMonthIdVar,sql,
					sql_brand,productProjectNameVar,
					planBrandtaskType,"","");
		}
		
		
		
		
	//////Alpha 2012-8-16
		//����Ʒ�Ʒ���	
		public String updtProduct_Project(String operatingMonthIdVar,String sql,String sql_brand,String productProjectNameVar,String productProjectIdVar,String planBrandtaskType ){
			
			
			
		//	System.out.print(sql.length()+"-");
		//	System.out.print(sql_brand.length());
			
			// System.out.println("ProjectName :"+productProjectNameVar);
	 		return dwrAddBrandService.updtProduct_Project(operatingMonthIdVar,sql,
	 				sql_brand,productProjectNameVar,
	 				productProjectIdVar,planBrandtaskType,"");
			//return null;
		}
		
		
		
		
		
		
		
		
		// alpha 2012-8-15	
		public String uploadExecl(FileTransfer fileVar) {
			String fileString = null;
			try {
				fileString = ToolAction.upload(fileVar.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return fileString;
		}

		// alpha 2012-8-15   ��������
				public List<String> showPlanMoneyError(String fileName) {
					List<String> msgList = new ArrayList<String>();
					Workbook book;
					try {
						book = Workbook.getWorkbook(new File(fileName));
						Sheet sheet = book.getSheet(0);
						int rownum = sheet.getRows();
						for (int Y = 2; Y < rownum; Y++) {					
	 						String braId = sheet.getCell(0, Y-1).getContents();					
	 					if(!braId.equals("")){
	 					if(dwrAddBrandService.findbraid(braId)==null){ 						 						
	 						//System.out.println(  "(E," + (Y + 1)+ ")��ֵ����ȷ!");
	 							String errorMsg = "(E," + (Y + 1)+ ")��ֵ����ȷ!";
	 							msgList.add(errorMsg);
	 						}
	 					}							
							//System.out.println();
							for (int X = 0; X < 3; X++)
							{
								String Str = sheet.getCell(X, Y).getContents();
								if( Y<rownum && Str.equals("")){												
									String errorMsg = "(E," + (Y + 1)+ ")��ֵ������Ϊ��!";
									msgList.add(errorMsg);
								}					
								// System.out.print("Str :"+X+"-"+Y+" :"+Str);
								//System.out.print("-" + Str);
								if (Str.equals("")) {
									String BraIdJ = sheet.getCell(X, Y + 1).getContents();// ��ȡ��һ�м�¼
									if (BraIdJ.equals("")) {
										break;// �������������¼δ��,������ѭ��
									}
								}
							}
						
						}

					} catch (BiffException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				//return null;
				  return msgList;
				}

				public String getUpfiles() {
					return upfiles;
				}

				public void setUpfiles(String upfiles) {
					this.upfiles = upfiles;
				}

	 		
				//alpha 2012-9-18  ������Ϸ���
				public String addProductProject_Group(String operatingMonthIdVar,
						String checkboxVarstr, String productProjectNameVal,
						String productProjectTypeVal, String productProjectVal,
						String isAddBrandWagesVal, String projectTypeVar) {
					
					//System.out.println("sql:"+checkboxVarstr);
					
					//return null;
					
			 return dwrAddBrandService.addProductProject_Group(operatingMonthIdVar,
			 				checkboxVarstr, productProjectNameVal, productProjectTypeVal,
			 				productProjectVal, isAddBrandWagesVal, projectTypeVar);
				}
					
}
