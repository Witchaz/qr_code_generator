package si.services;

import si.models.Project;
import si.models.Series;

public class Data {

    private static Data data;

    private Project project;
    
    private Series currentSelectedSeries;

    public static Data getData() {
        if (data == null) data = new Data();
        return data;
    }

    public Project getProject(){
        if (project == null) project = new Project();
        return project;
    }
    
    public Series getCurrentSelectedSeries() {
        return currentSelectedSeries;
    }
    
    public void setCurrentSelectedSeries(Series currentSelectedSeries) {
        this.currentSelectedSeries = currentSelectedSeries;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
}
