package xyz.zsuatem.appstore.people;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Subcontractor extends Human {
    private final ArrayList<Skill> skillList = new ArrayList<>();
    private final Integer maxNumberOfSkills = 3;
    private final SubcontractorType type;

    public Subcontractor(String firstName, String lastName, SubcontractorType type) {
        super(firstName, lastName);
        this.type = type;

        Integer tmpNumberOfSkills = 0;
        while (tmpNumberOfSkills <= maxNumberOfSkills) {
            Integer randomSkillId = ThreadLocalRandom.current().nextInt(0, Skill.getNumberOfSkills());

            if (!skillList.contains(Skill.getById(randomSkillId))) {
                skillList.add(Skill.getById(randomSkillId));
                tmpNumberOfSkills++;
            }
        }
    }
}