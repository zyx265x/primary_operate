package com.zt.operate.helper;

import com.zt.operate.entity.Question;

import java.util.Iterator;
import java.util.List;

public class PrintQuestion {

    /**
     * 打印单个题目
     * @param question
     * @return
     */
    public static String printSigleOne(Question question){
        return question.getTitle() + " = " + question.getAnswer();
    }

    public static void print(List<Question> questions){
        Iterator<Question> iterator = questions.iterator();
        int i=1;
        while (iterator.hasNext()){
            Question question = iterator.next();
            System.out.println(i+"."+printSigleOne(question));
            i++;
        }
    }
}
