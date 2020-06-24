package xyz.zsuatem.appstore.people;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubcontractorTest {

    @Test
    public void shouldSaySubcontractorIsNotNull(){
        Subcontractor subcontractor = new Subcontractor("FullName", SubcontractorType.average);
        assertNotNull(subcontractor);
    }

}