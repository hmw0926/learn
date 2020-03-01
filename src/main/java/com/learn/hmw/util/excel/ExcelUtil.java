package com.learn.hmw.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * excel文件读取工具类,支持xls,xlsx两种格式
 * @author Andrew
 *
 */
public class ExcelUtil {
    //2003- 版本的excel
    private final static String excel2003L =".xls";
    //2007+ 版本的excel
    private final static String excel2007U =".xlsx";
    
    public static void main(String[] args) throws Exception{
    	
    	Workbook wb = ExcelUtil.getWB(null, "C:/Users/HP/Desktop/学员评估情况导入模板.xlsx");
    	Map<Integer, String> fieldMap = new HashMap<Integer, String>();
    	fieldMap.put(0, "stuName");
    	fieldMap.put(1, "score");
    	
    	List<ExcelVo> list = ExcelUtil.analyzeExcel(wb, ExcelVo.class, fieldMap);
    	System.out.println("end");
	}

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    private static Workbook getWorkbook(InputStream inStr, String fileName) throws IOException, Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }
    
    /**
     * HttpServletRequest解析WB，或直接从文件解析WB
     * 
     * @author hmw
     */
    public static Workbook getWB(MultipartFile multipartFile, String localFile) throws Exception{
    	
    	InputStream is = null;
    	String fileName = null;
    	if(localFile == null) {
		    is = multipartFile.getInputStream();
		    fileName = multipartFile.getOriginalFilename();
    	}else {
    		File file = new File(localFile);
    		is = new FileInputStream(file);
    		fileName = file.getName();
    	}
      
    	Workbook wb = getWorkbook(is, fileName);
    	return wb;
	}
    
    private static MultipartFile getFile(HttpServletRequest request) throws Exception{
		MultipartFile file = null;
    	try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (!multipartResolver.isMultipart(request)) throw new Exception("上传失败,没有接收到文件!");
            
            file = ((MultipartHttpServletRequest) request).getFile("file");
        } catch (Exception e) {
            throw new Exception("excel解析异常!");
        }
		return file;
	}
    
    /**
     * @author hmw
     * @param Map<Integer, String> fieldMap  Integer = colNum，String = fieldName
     */
    public static <T> List<T> analyzeExcel(Workbook wb, Class<T> clazz, Map<Integer, String> fieldMap) throws Exception{
    	if(clazz == null) throw new Exception("类名为空！");
    	
        //读取sheet(从0计数)
        Sheet sheet = wb.getSheetAt(0);
        //读取行数(从0计数)
        int rowNum  =sheet.getLastRowNum();
        
        List<T> list = new ArrayList<>();
        for(int i=1; i<=rowNum; i++){
        	Object vo = clazz.newInstance();
            //获得行
            Row row = sheet.getRow(i);
            //获得当前行的列数
            int colNum = row.getLastCellNum();
            
            for(int j=0; j<colNum; j++){
                //获取单元格
                Cell cell = row.getCell(j);
                String fieldName = fieldMap.get(j);
                Field field = clazz.getDeclaredField(fieldName);
                
                Object cellValue = null;
                if(field.getType() == Double.class){
                	cellValue = cell.getNumericCellValue();
                }else if(field.getType() == Long.class) {
                	Double doubleValue = cell.getNumericCellValue();
                	cellValue = doubleValue != null ? doubleValue.longValue() : null;
                }else if(field.getType() == Integer.class){
                	Double doubleValue = cell.getNumericCellValue();
                	cellValue = doubleValue != null ? doubleValue.intValue() : null;
                }else if(field.getType() == String.class) {
                	cellValue = cell.getStringCellValue();
                }else if(field.getType() == Date.class) {
                	cellValue = cell.getDateCellValue();
                }
                field.setAccessible(true);
                field.set(vo, cellValue);
            }
            list.add((T) vo);
        }
        return list;
    }
    
    public static void exportExcel(HttpServletResponse response) throws Exception {
        //创建工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();//这里也可以设置sheet的Name
        //创建工作表对象
        HSSFSheet sheet = workbook.createSheet();
        //创建工作表的行
        HSSFRow row = sheet.createRow(0);//设置第一行，从零开始
        row.createCell(2).setCellValue("aaaaaaaaaaaa");//第一行第三列为aaaaaaaaaaaa
        row.createCell(0).setCellValue(new Date());//第一行第一列为日期
        workbook.setSheetName(0,"sheet的Name");//设置sheet的Name
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls";
 
        //文档输出
//        FileOutputStream out = new FileOutputStream("/Users/wangjun/temp/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls");
        FileOutputStream out = new FileOutputStream("C:/Users/HP/Desktop/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls");
        
        workbook.write(out);
        out.close();
    }
    
}
