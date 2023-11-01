package si.services;

import si.models.Project;

public class Data {

    private static Data data;

    private Project project;

    public static Data getData() {
        if (data == null) data = new Data();
        return data;
    }

    public Project getProject(){
        if (project == null) project = new Project();
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
