package si.models;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Series {

    final LocalDateTime generateTime;
    String constantText;
    int startNumber;
    int endNumber;
    int space;
    boolean fillWithZero;


    public Series (String constantText,int startNumber,int endNumber,int space,boolean fillWithZero){
        this.constantText = constantText;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.space = space;
        this.fillWithZero = fillWithZero;
        this.generateTime = LocalDateTime.now();
    }
    public Series (String constantText){
        this(constantText,0,0,0,false);
    }

    public ArrayList<String> getSeries(){
        ArrayList<String> series= new ArrayList<>();
        String text;
        for (int i = startNumber; i <= endNumber ; i++){
            if (fillWithZero){
                DecimalFormat df = new DecimalFormat(generateDecimalFormat());
                text = constantText + df.format(i);
            }
            else if (startNumber == 0 && endNumber == 0) text =constantText;
            else text = constantText + i;

            series.add(text);
        }
        return series;
    }

    public Series equals(Series series){
        if (generateTime.equals(series.getGenerateTime())) return series;
        return null;
    }
    private String generateDecimalFormat(){
        String s = "";
        for (int i = 0; i < space ;i++) s += '0';
        return s;
    }

    public String getConstantText() {
        return constantText;
    }

    public void setConstantText(String constantText) {
        this.constantText = constantText;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(int endNumber) {
        this.endNumber = endNumber;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public boolean isFillWithZero() {
        return fillWithZero;
    }

    public void setFillWithZero(boolean fillWithZero) {
        this.fillWithZero = fillWithZero;
    }

    public LocalDateTime getGenerateTime() {
        return generateTime;
    }

    @Override
    public String toString() {
        if (startNumber ==  0 && endNumber == 0) return constantText;
        if (fillWithZero) {
            DecimalFormat df = new DecimalFormat(generateDecimalFormat());
            return constantText + '{' + df.format(startNumber) + " - " + df.format(endNumber) + '}';
        }
        return constantText + '{' + startNumber + " - " + endNumber + '}';
    }
}
