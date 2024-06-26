package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskPriority;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_TASK_NAME = "";
    private static final String INVALID_TASK_DESCRIPTION = "";
    private static final String INVALID_TASK_PRIORITY = "0";
    private static final String INVALID_TASK_DATE = "";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_TASK_NAME = "Task 1";
    private static final String VALID_TASK_DESCRIPTION = "Description 1";
    private static final String VALID_TASK_PRIORITY = "low";
    private static final String VALID_TASK_DATE = "12-12-2024 16:00";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTaskName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskName((String) null));
    }

    @Test
    public void parseTaskName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskName(INVALID_TASK_NAME));
    }

    @Test
    public void parseTaskName_validValueWithoutWhitespace_returnsTaskName() throws Exception {
        TaskName expectedTaskName = new TaskName(VALID_TASK_NAME);
        assertEquals(expectedTaskName, ParserUtil.parseTaskName(VALID_TASK_NAME));
    }

    @Test
    public void parseTaskName_validValueWithWhitespace_returnsTrimmedTaskName() throws Exception {
        String taskNameWithWhitespace = WHITESPACE + VALID_TASK_NAME + WHITESPACE;
        TaskName expectedTaskName = new TaskName(VALID_TASK_NAME);
        assertEquals(expectedTaskName, ParserUtil.parseTaskName(taskNameWithWhitespace));
    }

    @Test
    public void parseTaskDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskDescription((String) null));
    }

    @Test
    public void parseTaskDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskDescription(INVALID_TASK_DESCRIPTION));
    }

    @Test
    public void parseTaskDescription_validValueWithoutWhitespace_returnsTaskDescription() throws Exception {
        TaskDescription expectedTaskDescription = new TaskDescription(VALID_TASK_DESCRIPTION);
        assertEquals(expectedTaskDescription, ParserUtil.parseTaskDescription(VALID_TASK_DESCRIPTION));
    }

    @Test
    public void parseTaskDescription_validValueWithWhitespace_returnsTrimmedTaskDescription() throws Exception {
        String taskDescriptionWithWhitespace = WHITESPACE + VALID_TASK_DESCRIPTION + WHITESPACE;
        TaskDescription expectedTaskDescription = new TaskDescription(VALID_TASK_DESCRIPTION);
        assertEquals(expectedTaskDescription, ParserUtil.parseTaskDescription(taskDescriptionWithWhitespace));
    }

    @Test
    public void parseTaskPriority_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskPriority((String) null));
    }

    @Test
    public void parseTaskPriority_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskPriority(INVALID_TASK_PRIORITY));
    }

    @Test
    public void parseTaskPriority_validValueWithoutWhitespace_returnsTaskPriority() throws Exception {
        TaskPriority expectedTaskPriority = new TaskPriority(VALID_TASK_PRIORITY);
        assertEquals(expectedTaskPriority, ParserUtil.parseTaskPriority(VALID_TASK_PRIORITY));
    }

    @Test
    public void parseTaskPriority_validValueWithWhitespace_returnsTrimmedTaskPriority() throws Exception {
        String taskPriorityWithWhitespace = WHITESPACE + VALID_TASK_PRIORITY + WHITESPACE;
        TaskPriority expectedTaskPriority = new TaskPriority(VALID_TASK_PRIORITY);
        assertEquals(expectedTaskPriority, ParserUtil.parseTaskPriority(taskPriorityWithWhitespace));
    }

    @Test
    public void parseTaskDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskDeadline((String) null));
    }

    @Test
    public void parseTaskDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskDeadline(INVALID_TASK_DATE));
    }

    @Test
    public void parseTaskDeadline_validValueWithoutWhitespace_returnsTaskDeadline() throws Exception {
        TaskDeadline expectedTaskDeadline = new TaskDeadline(VALID_TASK_DATE);
        assertEquals(expectedTaskDeadline, ParserUtil.parseTaskDeadline(VALID_TASK_DATE));
    }

    @Test
    public void parseTaskDeadline_validValueWithWhitespace_returnsTrimmedTaskDeadline() throws Exception {
        String taskDeadlineWithWhitespace = WHITESPACE + VALID_TASK_DATE + WHITESPACE;
        TaskDeadline expectedTaskDeadline = new TaskDeadline(VALID_TASK_DATE);
        assertEquals(expectedTaskDeadline, ParserUtil.parseTaskDeadline(taskDeadlineWithWhitespace));
    }
}
