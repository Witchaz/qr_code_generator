package si.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeriesTest {

    @Test
    @DisplayName("ทดสอบการสร้าง series แบบมีแค่ค่าคงตัว")
    void constantOnly() {
        Series series = new Series("Hi");
        assertEquals("Hi",series.getConstantText());
    }
    @Test
    @DisplayName("ทดสอบการสร้าง series แบบมีการไล่ลำดับ")
    void orderWithoutFIllWithZero() {
        Series series = new Series("Hi",1,35,3,false);
        for (String t : series.getSeries()){
            System.out.println(t);
        }
    }

    @Test
    @DisplayName("ทดสอบการสร้าง series แบบมีการไล่ลำดับ และมีการใส่ 0 แทนช่องว่าง")
    void orderWithFIllWithZero() {
        Series series = new Series("Hi",1,50,5,true);
        for (String t : series.getSeries()){
            System.out.println(t);
        }
    }

}