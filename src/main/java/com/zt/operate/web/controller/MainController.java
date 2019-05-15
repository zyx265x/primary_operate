package com.zt.operate.web.controller;

import com.zt.operate.common.config.ServerConfig;
import com.zt.operate.common.utils.FrontUtils;
import com.zt.operate.common.utils.ResponseUtils;
import com.zt.operate.entity.OperateType;
import com.zt.operate.entity.Question;
import com.zt.operate.helper.GenerateQuestion;
import com.zt.operate.helper.OperateReader;
import com.zt.operate.helper.QuestionToWordHelper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.zt.operate.common.Constants.DOC_RES;

@Controller
public class MainController {
    @Resource
    public ServerConfig serverConfig;
    /**
     * 首页
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping({"/index","/"})
    public String index(HttpServletRequest request, ModelMap model) {
        FrontUtils.frontData(request, model);
        List<OperateType> operates = null;
        try {
            List<OperateType> operateTypes = OperateReader.getInstance().getOperates();
            operates = Optional.ofNullable(operateTypes).orElse(new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("operates",operates);
        return "index";
    }

    @GetMapping({"/generateQuestion"})
    public void generateQuestion(String id, String name,Integer count, HttpServletRequest request, HttpServletResponse response) throws Exception {

        XWPFDocument xdoc = new XWPFDocument();
        QuestionToWordHelper helper = new QuestionToWordHelper();
        String rootPath = serverConfig.getWebRoot();

        GenerateQuestion generateQuestion = new GenerateQuestion();
        List<Question> questions = null;
        if("1.1".equals(id)){
            //10以内的加法
            questions = generateQuestion.addWithinBound( count);
        }
        if("1.2".equals(id)){
            //100以内的两位数加一位数的简单加法
            questions = generateQuestion.addIn100(1,false, count);
        }
        if("1.3".equals(id)){
            //100以内的两位数加一位数的进位加法
            questions = generateQuestion.addIn100(1,true, count);
        }
        if("1.4".equals(id)){
            //100以内的两位数加两位数的简单加法
            questions = generateQuestion.addIn100(2,false, count);
        }
        if("1.5".equals(id)){
            //100以内的两位数加两位数的进位加法
            questions = generateQuestion.addIn100(2,true, count);
        }
        if("2.1".equals(id)){
            //10以内的减法
            questions = generateQuestion.subWithinBound( count);
        }
        if("2.2".equals(id)){
            //100以内的两位数减一位数的简单减法（非借位）
            questions = generateQuestion.subIn100(1,false, count);
        }
        if("2.3".equals(id)){
            //100以内的两位数减一位数的借位减法
            questions = generateQuestion.subIn100(1,true, count);
        }
        if("2.4".equals(id)){
            //100以内的两位数减两位数的简单减法
            questions = generateQuestion.subIn100(2,false, count);
        }
        if("2.5".equals(id)){
            //100以内的两位数减两位数的借位减法
            questions = generateQuestion.subIn100(2,true, count);
        }

        name +="("+questions.size()+"题)";
        helper.createDatabook(name,xdoc,questions);

        File file = helper.saveDocument(xdoc, rootPath+DOC_RES+ "/"+name + ".docx");
        ResponseUtils.download(file,response);
    }

}
