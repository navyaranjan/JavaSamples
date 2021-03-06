package com.example.kafka;

import lombok.RequiredArgsConstructor;

public class KafkaModel {

    private String field1;
    private String field2;

    public KafkaModel() {

    }

    public KafkaModel(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    /**
     * @return String return the field1
     */
    public String getField1() {
        return field1;
    }

    /**
     * @param field1 the field1 to set
     */
    public void setField1(String field1) {
        this.field1 = field1;
    }

    /**
     * @return String return the field2
     */
    public String getField2() {
        return field2;
    }

    /**
     * @param field2 the field2 to set
     */
    public void setField2(String field2) {
        this.field2 = field2;
    }

}