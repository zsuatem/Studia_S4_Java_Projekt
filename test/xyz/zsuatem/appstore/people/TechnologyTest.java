package xyz.zsuatem.appstore.people;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechnologyTest {

    @Test
    public void shouldSayThatId2IsEqualsDatabaseSkill() {
        assertEquals(Technology.database, Technology.getById(2));
    }

    @Test
    public void shouldSayThatId10IsNull() {
        assertNull(Technology.getById(10));
    }

    @Test
    public void shouldSayThat6IsNumberOfSkills() {
        assertEquals(6, Technology.getNumberOfSkills());
    }

    @Test
    public void shouldSayThat8IsNotNumberOfSkills() {
        assertNotEquals(8, Technology.getNumberOfSkills());
    }

    @Test
    public void shouldSayThatExpectedNameOfDatabaseSkillIsCorrect() {
        Technology technology = Technology.backend;
        assertEquals("Back-end", technology.getName());
    }

    @Test
    public void shouldSayThatExpectedNameOfDatabaseSkillIsNotCorrect() {
        Technology technology = Technology.backend;
        assertNotEquals("Backend", technology.getName());
    }
}