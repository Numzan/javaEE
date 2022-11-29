package edu.whu.demo.entity;
import lombok.Data;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
public class action {
    String name;

    String remark;

    @Convert(converter = StringListConverter.class)
    List<String> authorities = new ArrayList<>();
}
