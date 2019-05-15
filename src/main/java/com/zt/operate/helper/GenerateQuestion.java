package com.zt.operate.helper;

import com.zt.operate.common.utils.CommonUtils;
import com.zt.operate.common.utils.StringUtils;
import com.zt.operate.entity.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成问题
 */
public class GenerateQuestion {
    
    public static final int LENGTH = 2;

    public static void main(String[] args) {
        GenerateQuestion generateQuestion = new GenerateQuestion();
        PrintQuestion.print(generateQuestion.addWithinBound( 100));
    }

    /**
     * 生成10以内的加法题
     *
     * @param count 题目数
     * @return
     */
    public List<Question> addWithinBound( Integer count) {
        List<Question> list = new ArrayList<>();
        for(int m=1;m<10;m++){
            for(int n=1;n<10;n++){
                Question question = add(m, n,LENGTH);
                list.add(question);
            }
        }
        if(count==null){
            count = list.size();
        }
        return CommonUtils.getRandomList(list, count);
    }

    /**
     * 100以内加法
     *
     * @param type   1:两位数 加 一位数 2：两位数 加两位数
     * @param jinwei 是否进位运算
     * @param count
     * @return
     */
    public List<Question> addIn100(int type, boolean jinwei, Integer count) {
        List<Question> list = new ArrayList<>();
        int minM = 10, maxM = 100;
        int minN = 1, maxN = 10;
        if (type == 2) {
            minN = 10;
            maxN = 100;
        }
        for (int m = minM; m < maxM; m++) {
            for (int n = minN; n < maxN; n++) {
                if (m + n < 100) {
                    int mark = m % 10 + n;
                    if (type == 2) {
                        mark = m % 10 + n % 10;
                    }
                    if (jinwei) {
                        if (mark > 10) {
                            Question question = add(m, n,LENGTH);
                            list.add(question);
                        }
                    } else {
                        if (mark <= 10) {
                            Question question = add(m, n,LENGTH);
                            list.add(question);
                        }
                    }
                }
            }
        }
        if(count==null){
            count = list.size();
        }
        return CommonUtils.getRandomList(list, count);
    }

    /**
     * 生成10以内的减法题，差不为负数
     *
     * @param count 题目数
     * @return
     */
    public List<Question> subWithinBound( Integer count) {
        List<Question> list = new ArrayList<>();
        for(int m=1;m<10;m++){
            for(int n=1;n<=m;n++){
                Question question = sub(m, n,LENGTH);
                list.add(question);
            }
        }
        if(count==null){
            count = list.size();
        }
        return CommonUtils.getRandomList(list, count);
    }

    /**
     * 100以内减法，差不为负数
     *
     * @param type   1:两位数 减 一位数 2：两位数 减两位数
     * @param jiewei 是否借位运算
     * @param count
     * @return
     */
    public List<Question> subIn100(int type, boolean jiewei, Integer count) {
        List<Question> list = new ArrayList<>();
        int minM = 10, maxM = 100;
        int minN = 1;
        if (type == 2) {
            minN = 10;
        }
        for (int m = minM; m < maxM; m++) {
            for (int n = minN; n <=m; n++) {
                int mark = m % 10 - n;
                if (type == 2) {
                    mark = m % 10 - n % 10;
                }
                if (jiewei) {
                    if (mark < 0) {
                        Question question = sub(m, n,LENGTH);
                        list.add(question);
                    }
                } else {
                    if (mark >= 0) {
                        Question question = sub(m, n,LENGTH);
                        list.add(question);
                    }
                }
            }
        }
        if(count==null){
            count = list.size();
        }
        return CommonUtils.getRandomList(list, count);
    }

    public Question add(int a, int b,int num) {
        String title = StringUtils.fullWithSpace(a,num) + " + " + StringUtils.fullWithSpace(b,num);
        String answer = StringUtils.fullWithSpace(a+b,num);
        return new Question(title, answer);
    }

    public Question sub(int a, int b,int num) {
        String title = StringUtils.fullWithSpace(a,num) + " - " + StringUtils.fullWithSpace(b,num);
        String answer = StringUtils.fullWithSpace(a-b,num);
        return new Question(title, answer);
    }

    public Question mul(int a, int b,int num) {
        String title = StringUtils.fullWithSpace(a,num) + " × " + StringUtils.fullWithSpace(b,num);
        String answer = StringUtils.fullWithSpace(a*b,num);
        return new Question(title, answer);
    }

    public Question div(int a, int b,int num) {
        String title = StringUtils.fullWithSpace(a,num) + " ÷ " + StringUtils.fullWithSpace(b,num);
        String answer = StringUtils.fullWithSpace(a/b,num);
        return new Question(title, answer);
    }
}
