package xyz.zsuatem.appstore.people;

import xyz.zsuatem.appstore.Office;

import java.util.ArrayList;
import java.util.List;

public class Player extends Human {
    private final ArrayList<Skill> skillList = new ArrayList<>();
    private final List<Subcontractor> subcontractorList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private Office office = null;
    private Double money = 15000.0;

    public Player(String firstName, String lastName) {
        super(firstName, lastName);
        skillList.add(Skill.backend);
        skillList.add(Skill.frontend);
        skillList.add(Skill.database);
        skillList.add(Skill.wordPress);
        skillList.add(Skill.prestaShop);
    }
}
