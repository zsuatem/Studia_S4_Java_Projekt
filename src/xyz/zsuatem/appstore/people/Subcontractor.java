package xyz.zsuatem.appstore.people;

import java.util.ArrayList;

public class Subcontractor extends Human {
    private final ArrayList<Technology> technologyList = new ArrayList<>();
    private final Integer maxNumberOfSkills = 3;
    private final SubcontractorType type;

    public Subcontractor(String fullName, SubcontractorType type) {
        super(fullName);
        this.type = type;

        technologyList.addAll(Technology.getRandomTechnologyList(maxNumberOfSkills));
    }
}