package com.zt.operate.helper;

import com.zt.operate.common.utils.XmlBuilder;
import com.zt.operate.entity.OperateList;
import com.zt.operate.entity.OperateType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class OperateReader {

    private static class SingletonHolder {

        private static final OperateReader INSTANCE = new OperateReader();

    }

    private OperateReader(){}

    public static final OperateReader getInstance() {

        return SingletonHolder.INSTANCE;

    }
    private static List<OperateType> operates;


    public List<OperateType> getOperates() throws Exception {
        if(operates==null){
            // 读取XML文件
            Resource resource = new ClassPathResource("./operate.xml");
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) !=null) {
                buffer.append(line);
            }
            br.close();
            // XML转为Java对象
            OperateList operateList = (OperateList) XmlBuilder.xmlStrToOject(OperateList.class, buffer.toString());
            operates = operateList.getTypes();
        }
        return  operates;
    }


}
