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
			return "没有对应单品,请确认你的查询条件!";
		}
		
		List<Product> list = new ArrayList<Product>(); 
		list.addAll(productList);
		
		JSONArray jsarry = new JSONArray();
		jsarry = JSONArray.fromObject(list);
		String msg  = jsarry.toString();
		return msg;
		
	}
	
	//根据品牌类型1 2 3 查询品牌大中小类
	public Map<String,String> getbrandByLevel(String brandLevel){
		Map<String,String> mapBrandLevel = dwrAddBrandService.getbrandByLevel(brandLevel);
		return mapBrandLevel;
	}
	//根据品牌类型1 2 3 查询类别大中小类
	public Map<String,String> getClassByLevel(String classLevel){
		Map<String,String> mapClassLevel = dwrAddBrandService.getClassByLevel(classLevel);
		return mapClassLevel;
	}

	//根据父品牌查子品牌 
	public Map<String,String> findBrandByParentBrand(String bigBrandId,String level){
		return dwrAddBrandService.findBrandByParentBrand(bigBrandId,level);
	}
	
	//根据父类别查子类别
	public  Map<String,String> findClassByParentClass(String parentClassId,String level){
		return dwrAddBrandService.findClassByParentClass(parentClassId,level);
	}
	//在页面上搜索单品返回json对象
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
	
	
	
	String msg  = "没有查询出对应单品或者单品已经陈列在页面上,请确认您的查询条件!";
	
	if(productList.size()>0){
	
		List<Object[]> list = new ArrayList<Object[]>(); 
		list.addAll(productList);
		
		JSONArray jsarry = new JSONArray();
		jsarry = JSONArray.fromObject(list);
		msg = jsarry.toString();
	}
	
	return msg;
	}
	//保存单品方案
	public String addProductProject(String operatingMonthIdVar,String checkboxVarstr,
			String productProjectNameVal,String productProjectTypeVal,
				String productProjectVal,String isAddBrandWagesVal,String projectTypeVar){
		return dwrAddBrandService.addProductProject(operatingMonthIdVar,checkboxVarstr,
				productProjectNameVal,productProjectTypeVal,
					productProjectVal,isAddBrandWagesVal,projectTypeVar);
	}
	
	//查询当前单品项目下的所有单品
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
			jsonProduct = "该单品方案没有单品安排,请添加!";
		}
		return jsonProduct;
	}
	
	//更新单品项目
	public void updtProductProject(String checkboxVarstr,String productProjectIdVar,
			String productProjectNameVal,String productProjectTypeVal,
			String productProjectVal,String isAddBrandWagesVal){
		
		 dwrAddBrandService.updtProductProject(checkboxVarstr,productProjectIdVar,
				productProjectNameVal,productProjectTypeVal,
				productProjectVal,isAddBrandWagesVal);
	}
	
	//删除单品项目
	public void delPro(String productProjectId){
		dwrAddBrandService.delPro(productProjectId);
	}
	
	
	public DwrAddBrandService getDwrAddBrandService() {
		return dwrAddBrandService;
	}

	public void setDwrAddBrandService(DwrAddBrandService dwrAddBrandService) {
		this.dwrAddBrandService = dwrAddBrandService;
	}
	// //Alpha 2012-8-9 搜寻品牌
		public String findSmallProduct(String bigBrandVar, String middleBrandVar,String smallBrandVar, String proIdClassStr) {

			//  System.out.println("abc");
			// System.out.println(ProStatusStr);

			List<ProductBrand> productList = null;
			productList = dwrAddBrandService.findSmallProductReturnMap(bigBrandVar,middleBrandVar,smallBrandVar,proIdClassStr);

			String msg = "没有对应品牌,请确认您的查询条件!";

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
		//保存品牌方案
		public String addProduct_Project(String operatingMonthIdVar,String sql,String sql_brand,String productProjectNameVar,String planBrandtaskType ){
			
			
			// System.out.println("ProjectName :"+productProjectNameVar);
			return dwrAddBrandService.addProduct_Project(operatingMonthIdVar,sql,
					sql_brand,productProjectNameVar,
					planBrandtaskType,"","");
		}
		
		
		
		
	//////Alpha 2012-8-16
		//更新品牌方案	
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

		// alpha 2012-8-15   定额导入检验
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
	 						//System.out.println(  "(E," + (Y + 1)+ ")的值不正确!");
	 							String errorMsg = "(E," + (Y + 1)+ ")的值不正确!";
	 							msgList.add(errorMsg);
	 						}
	 					}							
							//System.out.println();
							for (int X = 0; X < 3; X++)
							{
								String Str = sheet.getCell(X, Y).getContents();
								if( Y<rownum && Str.equals("")){												
									String errorMsg = "(E," + (Y + 1)+ ")的值不允许为空!";
									msgList.add(errorMsg);
								}					
								// System.out.print("Str :"+X+"-"+Y+" :"+Str);
								//System.out.print("-" + Str);
								if (Str.equals("")) {
									String BraIdJ = sheet.getCell(X, Y + 1).getContents();// 获取下一行记录
									if (BraIdJ.equals("")) {
										break;// 如果连续两条记录未空,则跳出循环
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

	 		
				//alpha 2012-9-18  保存组合方案
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
