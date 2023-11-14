package si.services;

import si.models.SeriesList;
import si.models.Series;

public class Data {

    private static Data data;
    
    private SeriesList seriesList;
    
    private Series currentSelectedSeries;

    public static Data getData() {
        if (data == null) data = new Data();
        return data;
    }

    public SeriesList getProject(){
        if (seriesList == null) seriesList = new SeriesList();
        return seriesList;
    }
    
    public Series getCurrentSelectedSeries() {
        return currentSelectedSeries;
    }
    
    public void setCurrentSelectedSeries(Series currentSelectedSeries) {
        this.currentSelectedSeries = currentSelectedSeries;
    }
    
    public void setProject(SeriesList seriesList) {
        this.seriesList = seriesList;
    }
}
