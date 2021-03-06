package seedu.address.model.meeting;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a meeting in MeetBuddy.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting {

    public static final String MESSAGE_CONSTRAINTS =
            "The start date time of a meeting should be strictly earlier than the terminate date time.";

    // Identity fields
    private final Name name;
    private final DateTime start;
    private final DateTime terminate;

    // Data fields
    private final Priority priority;
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Meeting(Name name, DateTime start, DateTime terminate, Priority priority,
                   Description description, Set<Tag> tags) {
        requireAllNonNull(name, start, terminate, priority, description, tags);
        checkArgument(isValidStartTerminate(start, terminate), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.start = start;
        this.terminate = terminate;
        this.priority = priority;
        this.description = description;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public DateTime getStart() {
        return start;
    }

    public DateTime getTerminate() {
        return terminate;
    }

    public Priority getPriority() {
        return priority;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both meetings have the same name, start and terminate time. (Use identify fields only)
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getName().equals(getName())
                && otherMeeting.getStart().equals(getStart())
                && otherMeeting.getTerminate().equals(getTerminate());
    }

    /**
     * Returns true if a given date time for the meeting is valid.
     */
    public static boolean isValidStartTerminate(DateTime start, DateTime terminate) {
        return start.compareTo(terminate) == -1;
    }

    /**
     * Returns true if both meetings have the same identity and data fields.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getName().equals(getName())
                && otherMeeting.getStart().equals(getStart())
                && otherMeeting.getTerminate().equals(getTerminate())
                && otherMeeting.getPriority().equals(getPriority())
                && otherMeeting.getDescription().equals(getDescription())
                && otherMeeting.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, start, terminate, priority, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Start: ")
                .append(getStart())
                .append("; Terminate: ")
                .append(getTerminate())
                .append("; Priority: ")
                .append(getPriority())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
