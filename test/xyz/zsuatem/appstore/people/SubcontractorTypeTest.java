package xyz.zsuatem.appstore.people;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubcontractorTypeTest {
    private  SubcontractorType subcontractorType;

    @BeforeEach
    void setUp(){
        subcontractorType = SubcontractorType.good;
    }

    @Test
    void getSalary() {
        assertEquals(7000.0, subcontractorType.getSalary());
    }

    @Test
    void getTimeMultiplier() {
        assertEquals(1.0, subcontractorType.getTimeMultiplier());
    }

    @Test
    void getErrorMultiplier() {
        assertEquals(1.0, subcontractorType.getErrorMultiplier());
    }
}