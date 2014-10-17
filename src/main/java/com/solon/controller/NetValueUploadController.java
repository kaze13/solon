package com.solon.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.solon.dto.NetValue;
import com.solon.service.spec.INetValueService;
import com.solon.service.spec.IProductService;

import util.NetValueReader;
import util.UpdateResult;

@Controller
public class NetValueUploadController {
	
	@Autowired
	IProductService productService;
	@Autowired
	INetValueService netValueService;
	
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, ModelMap model) {
 
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
 
                String serverfileDir = dir.getAbsolutePath()
                        + File.separator + "uploadNetVal";
                File serverFile = new File(serverfileDir);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                Map<String, List<NetValue>> netValues = null;
                // Create the file on server
                if(name.indexOf(".xlsx") > 0 || name.indexOf(".xls") > 0){
                	netValues = NetValueReader.readXLS(serverfileDir);
                }
                
                else{
                	return "File is not valid Office Excel file";
                }
               

                
                
                Set<String> keys = netValues.keySet();
                List<UpdateResult> updates = new ArrayList<UpdateResult>();
                
                StringBuffer resultString = new StringBuffer("更新结果\n");
                for(String key: keys){
                	int id = productService.queryProductIdByName(key);
                	UpdateResult r = new UpdateResult();
                	r.setProductName(key);
                	resultString.append(key + "   ");
                
                	
                	if(id != -1){
                		List<NetValue> values = netValues.get(key);
                		for(NetValue val: values){
                			val.setProductId(id);
                		}
                		
                		netValueService.removeByProduct(id);
                		netValueService.insertByBatch(values);
                		r.setResultString("更新成功，删除原有净值，新增净值" + values.size() + "项");
                		resultString.append("更新成功，删除原有净值，新增净值" + values.size() + "项\n");
                	}
                	else{
                		r.setResultString("更新失败，没有找到匹配产品，请确保Sheet名称和产品名称一致");
                		resultString.append("更新失败，没有找到匹配产品，请确保Sheet名称和产品名称一致\n");
                	}
                	updates.add(r);
                }
                
                model.addAttribute("updateResults", updates);
                return "update success";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name
                    + " because the file was empty.";
        }
    }
}