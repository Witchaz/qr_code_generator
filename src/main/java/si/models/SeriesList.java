package si.models;

import java.util.ArrayList;

public class SeriesList {

    private final ArrayList<Series> seriesList;

    public SeriesList(){
        seriesList = new ArrayList<>();
    }

    public void addSeries(String constantText,int startNumber,int endNumber,int space,boolean fillWithZero){
        seriesList.add(new Series(constantText,startNumber,endNumber,space,fillWithZero));
    }
    
    public void addSeries(String constantText){
        seriesList.add(new Series(constantText));
    }

    public void removeSeries(Series series){
        seriesList.remove(series);
    }

    public void printAll(){
        for (Series series : seriesList){
            System.out.println(series.toString());
            for (String t : series.getSeries()){
                System.out.println(t);
            }
            System.out.println("\n\n");
        }
    }

    public ArrayList<Series> getSeriesList() {
        return seriesList;
    }
}
