package xyz.zsuatem.appstore.people;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubcontractorTest {

    @Test
    public void shouldSaySubcontractorIsNotNull(){
        Subcontractor subcontractor = new Subcontractor("FirstName", "LastName", SubcontractorType.average);
        assertNotNull(subcontractor);
    }

}