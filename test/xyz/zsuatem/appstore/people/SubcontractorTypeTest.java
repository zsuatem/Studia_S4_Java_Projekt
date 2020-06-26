package xyz.zsuatem.appstore.people;

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
        assertEquals(8, subcontractorType.getPunctuality());
    }

    @Test
    void getErrorMultiplier() {
        assertEquals(0, subcontractorType.getErrors());
    }
}