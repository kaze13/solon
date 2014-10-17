package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



import com.solon.dto.NetValue;

public class NetValueReader 
{
	private static String DEFAULT_PREFIX = "Sheet";
	
	public static Map<String, List<NetValue>> readXLS(String filePath) throws IOException, InvalidFormatException 
    {
        //Blank workbook
    	
    	Map<String, List<NetValue>> values = new HashMap<String, List<NetValue>>();
    	
    	FileInputStream inputStream = new FileInputStream(new File(filePath));
    	 
        Workbook workbook = WorkbookFactory.create(inputStream);

        int nSheet = workbook.getNumberOfSheets();
        for(int i = 0; i < nSheet; i++){
        	 //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(i);
            List<NetValue> myValues = new ArrayList<NetValue>();
            String sheetName = sheet.getSheetName();
            
            if(sheetName.indexOf(DEFAULT_PREFIX) > 0){
            	continue;
            }
            
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            if(rowIterator.hasNext()){
            	rowIterator.next();//skip the header row
            }
            else{
            	continue;//there is now data in this sheet
            }
            
            
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Date date = row.getCell(0).getDateCellValue();
                double netvalue = row.getCell(1).getNumericCellValue();
                double ns300 = row.getCell(2).getNumericCellValue();
                NetValue value = new NetValue();
                value.setEvalueDate(new java.sql.Date(date.getTime()));
                value.setNetValue(netvalue);
                value.setNetIncreaseRate(ns300);
                myValues.add(value);
            }
            values.put(sheetName, myValues);
        }
       
        inputStream.close();
        
        return values;
    }
	
	
  
}