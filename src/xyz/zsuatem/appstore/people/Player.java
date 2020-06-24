package xyz.zsuatem.appstore.people;

import xyz.zsuatem.appstore.FullFullNameGeneratorFromTxtFile;
import xyz.zsuatem.appstore.Office;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Subcontractor> subcontractorList = new ArrayList<>();
    private final ArrayList<Skill> skillList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private Office office = null;
    private Double money = 15000.0;
    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
        skillList.add(Skill.backend);
        skillList.add(Skill.frontend);
        skillList.add(Skill.database);
        skillList.add(Skill.wordPress);
        skillList.add(Skill.prestaShop);

        subcontractorList.add(new Subcontractor(new FullFullNameGeneratorFromTxtFile().getFullName(), SubcontractorType.good));
        subcontractorList.add(new Subcontractor(new FullFullNameGeneratorFromTxtFile().getFullName(), SubcontractorType.average));
        subcontractorList.add(new Subcontractor(new FullFullNameGeneratorFromTxtFile().getFullName(), SubcontractorType.bad));
    }

    public Double getMoney() {
        return money;
    }

    public String getPlayerName() {
        return playerName;
    }
}
