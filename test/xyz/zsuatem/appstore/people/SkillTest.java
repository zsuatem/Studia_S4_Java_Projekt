package xyz.zsuatem.appstore.people;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @Test
    public void shouldSayThatId2IsEqualsDatabaseSkill() {
        assertEquals(Skill.database, Skill.getById(2));
    }

    @Test
    public void shouldSayThatId10IsNull() {
        assertNull(Skill.getById(10));
    }

    @Test
    public void shouldSayThat6IsNumberOfSkills() {
        assertEquals(6, Skill.getNumberOfSkills());
    }

    @Test
    public void shouldSayThat8IsNotNumberOfSkills() {
        assertNotEquals(8, Skill.getNumberOfSkills());
    }

    @Test
    public void shouldSayThatExpectedNameOfDatabaseSkillIsCorrect() {
        Skill skill = Skill.backend;
        assertEquals("Back-end", skill.getName());
    }

    @Test
    public void shouldSayThatExpectedNameOfDatabaseSkillIsNotCorrect() {
        Skill skill = Skill.backend;
        assertNotEquals("Backend", skill.getName());
    }
}