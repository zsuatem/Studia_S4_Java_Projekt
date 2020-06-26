package xyz.zsuatem.appstore.people;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SubcontractorTest {

    @Test
    public void shouldSaySubcontractorIsNotNull() {
        Subcontractor subcontractor = new Subcontractor(SubcontractorType.average);
        assertNotNull(subcontractor);
    }

}