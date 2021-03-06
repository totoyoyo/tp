package seedu.address.model.meeting;

import org.junit.jupiter.api.Test;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.testutil.MeetingBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.MEETING2;
//import static seedu.address.testutil.TypicalMeetings.MEETING3;
//import static seedu.address.testutil.TypicalMeetings.MEETING4;
//import static seedu.address.testutil.TypicalMeetings.MEETING5;


class UniqueMeetingListTest {
    private final UniqueMeetingList uniqueMeetingList = new UniqueMeetingList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueMeetingList.contains(MEETING1));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueMeetingList.add(MEETING1);
        assertTrue(uniqueMeetingList.contains(MEETING1));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueMeetingList.add(MEETING1);
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withPriority("1").withTags("RandomTag")
                .build();
        assertTrue(uniqueMeetingList.contains(editedMeeting1));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueMeetingList.add(MEETING1);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.add(MEETING1));
    }

    @Test
    public void setMeeting_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(null, MEETING1));
    }

    @Test
    public void setMeeting_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(MEETING1, null));
    }

    @Test
    public void setMeeting_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.setMeeting(MEETING1, MEETING1));
    }

    @Test
    public void setMeeting_editedPersonIsSamePerson_success() {
        uniqueMeetingList.add(MEETING1);
        uniqueMeetingList.setMeeting(MEETING1, MEETING1);
        UniqueMeetingList expecteduniqueMeetingList = new UniqueMeetingList();
        expecteduniqueMeetingList.add(MEETING1);
        assertEquals(expecteduniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedPersonHasSameIdentity_success() {
        uniqueMeetingList.add(MEETING1);
        Meeting editedMEETING1 = new MeetingBuilder(MEETING1).withPriority("1").withTags("randomTag")
                .build();
        uniqueMeetingList.setMeeting(MEETING1, editedMEETING1);
        UniqueMeetingList expecteduniqueMeetingList = new UniqueMeetingList();
        expecteduniqueMeetingList.add(editedMEETING1);
        assertEquals(expecteduniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedPersonHasDifferentIdentity_success() {
        uniqueMeetingList.add(MEETING1);
        uniqueMeetingList.setMeeting(MEETING1, MEETING2);
        UniqueMeetingList expecteduniqueMeetingList = new UniqueMeetingList();
        expecteduniqueMeetingList.add(MEETING2);
        assertEquals(expecteduniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueMeetingList.add(MEETING1);
        uniqueMeetingList.add(MEETING2);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeeting(MEETING1, MEETING2));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.remove(MEETING1));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueMeetingList.add(MEETING1);
        uniqueMeetingList.remove(MEETING1);
        UniqueMeetingList expecteduniqueMeetingList = new UniqueMeetingList();
        assertEquals(expecteduniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nulluniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_uniqueMeetingList_replacesOwnListWithProvideduniqueMeetingList() {
        uniqueMeetingList.add(MEETING1);
        UniqueMeetingList expecteduniqueMeetingList = new UniqueMeetingList();
        expecteduniqueMeetingList.add(MEETING2);
        uniqueMeetingList.setMeetings(expecteduniqueMeetingList);
        assertEquals(expecteduniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        uniqueMeetingList.add(MEETING1);
        List<Meeting> personList = Collections.singletonList(MEETING2);
        uniqueMeetingList.setMeetings(personList);
        UniqueMeetingList expecteduniqueMeetingList = new UniqueMeetingList();
        expecteduniqueMeetingList.add(MEETING2);
        assertEquals(expecteduniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Meeting> listWithDuplicatePersons = Arrays.asList(MEETING1, MEETING1);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeetings(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueMeetingList.asUnmodifiableObservableList().remove(0));
    }
}
