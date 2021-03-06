package seedu.address.model.meeting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

class DateTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }


    @Test
    public void isValidDateTime() {
        // null address
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid DateTime
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime("2020/01/02 19:00"));
        assertFalse(DateTime.isValidDateTime("2021-13-02 18:00"));
        assertFalse(DateTime.isValidDateTime("2021-11-02 25:00"));

        // valid DateTime
        assertTrue(DateTime.isValidDateTime("2021-11-02 23:00"));
    }
}
