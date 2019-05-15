package com.zt.operate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

//根元素
@XmlRootElement(name = "type")
//访问类型，通过字段
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateType {
    @XmlElement(name = "subType")
    private List<OperateSubType> subTypes;
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "name")
    private String name;

    @Override
    public String toString() {
        return "OperateType{" +
                "subTypes=" + subTypes +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
