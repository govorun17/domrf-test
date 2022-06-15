package org.example;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = "#")
public class MyObject {
    @DataField(pos = 1, required = true)
    private Long id;
    @DataField(pos = 2, required = true)
    private String name;
    @DataField(pos = 3, required = true)
    private Integer sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public void incSum(int increment) {
        this.sum *= increment;
    }

    @Override
    public String toString() {
        return "id:"+id+
                " name:\""+name+"\""+
                " sum:"+sum;
    }
}
