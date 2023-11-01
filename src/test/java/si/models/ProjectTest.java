package si.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    @DisplayName("ทดสอบการสร้าง 2 Series")
    void create2Series(){
        Project project = new Project();
        project.addSeries("Meow",20,40,5,true);
        project.addSeries("Bork",10,50,3,true);

        project.printAll();
    }

    @Test
    @DisplayName("ทดสอบการลบ Series")
    void deleteSeries(){
        Project project = new Project();
        project.addSeries("Meow",20,40,5,true);
        project.addSeries("Bork",10,50,3,true);
        project.addSeries("WhatDoesTheFoxSay",1,20,4,false);

        Series test = new Series("test");
        project.removeSeries(project.getSeriesList().get(0));
        project.printAll();
    }
}