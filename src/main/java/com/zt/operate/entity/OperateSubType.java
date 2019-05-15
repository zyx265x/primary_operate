package com.zt.operate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//根元素
@XmlRootElement(name = "subType")
//访问类型，通过字段
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateSubType {
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "name")
    private String name;

    @Override
    public String toString() {
        return "OperateSubType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
