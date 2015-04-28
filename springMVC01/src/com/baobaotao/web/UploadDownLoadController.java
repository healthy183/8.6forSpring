package com.baobaotao.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



@Scope("prototype")
@Controller
@RequestMapping("/upDown")
public class UploadDownLoadController {

	@RequestMapping("/toUpDownPage")
	public String toUpDownPage(){
		return "upDown/toUpDownPage";
	}
	
	@RequestMapping("/testUpload") //
	public String testUpload(@RequestParam("file")MultipartFile file,
				ModelMap modelMap) throws IllegalStateException, IOException{
		String msg = null;
		if(!file.isEmpty()){
			file.transferTo(new File("d:/"+file.getOriginalFilename()));
			msg = "上传成功!";
		}else{
			msg = "上传失败，文件为空!";
		}
		modelMap.put("msg",msg);
		return "upDown/toUpDownPage";
	}
	
	
	
	@RequestMapping("/testUpload2")
	public String testUpload2(HttpServletRequest request,    
			HttpServletResponse response) throws Exception{
		//, BindException errors
		MultipartHttpServletRequest multipartRequest 
			= (MultipartHttpServletRequest) request; 
		
		CommonsMultipartFile file =
				(CommonsMultipartFile) multipartRequest.getFile("file");
		
		
		//获取文件名字
		String realFileName = file.getOriginalFilename();
		
		// 获取路径    方法1
	    /*String ctxPath 
	        = request.getSession().getServletContext()
	        	.getRealPath("/")+ "images/"; */
	    
		// 获取路径    方法2
	    String ctxPath = request.getSession().getServletContext()
	    	.getRealPath("/") +"\\"+"images\\"; 

	    
	    //创建文件
	    File dirPath = new File(ctxPath);
	    
	    if(!dirPath.exists()){
	    	dirPath.mkdir();
	    }
	    
	    File uploadFile = new File(ctxPath + realFileName);    
	    FileCopyUtils.copy(file.getBytes(), uploadFile);
	   // request.setAttribute("",);
	    request.setAttribute("msg","上传成功!");
		return "upDown/toUpDownPage";
	}

	
	// 多文件上传  
	@RequestMapping("/testUpload3")
	public String testUpload3(HttpServletRequest request,    
			HttpServletResponse response) throws Exception{
		
		MultipartHttpServletRequest multipartRequest 
				= (MultipartHttpServletRequest) request;
		
		Map<String,MultipartFile> fileMap
			   = multipartRequest.getFileMap();
		
		// 获取路径 
	    String ctxPath = request.getSession().getServletContext()
	    	.getRealPath("/") +"\\"+"images\\"; 
	    
	    File file = new File(ctxPath);
	    if(!file.exists()){
	    	file.mkdir();
	    }
		
	    String fileName = null;
	    for(Map.Entry<String,MultipartFile> m : fileMap.entrySet()){
	    	
	    	MultipartFile multipartFile = m.getValue();
	    	fileName = multipartFile.getOriginalFilename();
	    	File uploadFile = new File(ctxPath + fileName);
	    	FileCopyUtils.copy(multipartFile.getBytes(), uploadFile);
	    }
	    
	    request.setAttribute("msg", "上传成功!");
	    //将文件 指定文件夹下的所有文件拿出，然后显示出来提供下载
	    request.setAttribute("files",loadFiles(request));
		
		return "upDown/toDownloadPage";
		
		// @ModelAttribute("files")
		//此属性用于初始类时调用,但上传文件后不能时时反应上传文件个数,不适合动态数据
	}
	
	public List<String> loadFiles(HttpServletRequest request){
		
		List<String> files = new ArrayList<String>();
		// 获取路径 
	    String ctxPath = request.getSession().getServletContext()
	    	.getRealPath("/") +"\\"+"images\\"; 
	    
	    File file = new File(ctxPath); 
	    
	    if(file.exists()){
	    	File[] fs =	file.listFiles();
	    	String fname = null;
	    	for(File f : fs){
	    		fname =	f.getName();
	    		files.add(fname);
	    	}
	    }
		
		return files;
	}
	
	
	@RequestMapping("/downFile/{file}")
	public String downFile(@PathVariable("file") String file,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8"); 
		java.io.BufferedInputStream bis = null; 
		java.io.BufferedOutputStream bos = null; 
		
		// 获取路径 
	    String ctxPath = request.getSession().getServletContext()
	    	.getRealPath("/") +"\\"+"images\\"; 
	    
	    String downLoadPath = ctxPath + file;
	    
	    
	    try {
			
	    	long fileLength = new File(downLoadPath).length(); 
	 	    response.setContentType("application/x-msdownload;");
	 	    response.setHeader("Content-disposition", "attachment; filename="  
	 	    		  + new String(file.getBytes("utf-8"), "ISO8859-1")); 
	 	    
	 	    response.setHeader("Content-Length", String.valueOf(fileLength)); 
	 	    bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
	 	    bos = new BufferedOutputStream(response.getOutputStream()); 
	 	    
	 	    byte[] buff = new byte[2048];
	 	    int bytesRead; 
	 	    
	 	    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	 	    	bos.write(buff, 0, bytesRead);  
	 	    } 
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if(bis != null){
				bis.close();
			}
			if(bos != null){
				bos.close();
			}
		}
		
	    
	    
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/testDownload")
	public void testDownload(HttpServletResponse response) throws IOException{
		
		File file =new File("D:/下载.txt");
		InputStream input =FileUtils.openInputStream(file);
		byte[] data = IOUtils.toByteArray(input);
		
		String fileName = URLEncoder.encode(file.getName(),"UTF-8");
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
		response.addHeader("Content-Length", ""+data.length);
		response.setContentType("application/octet-stream; charset=UTF-8"); 
		//IOUtils.write(data, response.getOutputStream());  
		//IOUtils.closeQuietly(input);  

		
	}
	
}
