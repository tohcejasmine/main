//package seedu.address.logic.calendar.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.ClearCommand;
//import seedu.address.logic.commands.EditCommand;
//import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
//import seedu.address.calendarModel.task.Task;
//import seedu.address.testutil.EditPersonDescriptorBuilder;
//import seedu.address.testutil.PersonBuilder;
//
//
//
///**
// * Contains integration tests (interaction with the CalendarModel, UndoCommand and RedoCommand) and unit tests for
// * EditCommand.
// */
//public class EditCommandTest {
//
//    private CalendarModel calendarModel = new CalendarModelManager(getTypicalAddressBook(), new CalendarUserPrefs());
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Task editedPerson = new PersonBuilder().build();
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        CalendarModel expectedModel = new CalendarModelManager(
//                new CalendarAddressBook(calendarModel.getCalendarAddressBook()),
//                new CalendarUserPrefs());
//        expectedModel.setPerson(calendarModel.getFilteredPersonList().get(0), editedPerson);
//
//        assertCommandSuccess(editCommand, calendarModel, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastPerson = Index.fromOneBased(calendarModel.getFilteredPersonList().size());
//        Task lastPerson = calendarModel.getFilteredPersonList().get(indexLastPerson.getZeroBased());
//
//        PersonBuilder personInList = new PersonBuilder(lastPerson);
//        Task editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
//                .withTags(VALID_TAG_HUSBAND).build();
//
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
//                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
//        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        CalendarModel expectedModel = new CalendarModelManager(
//                new CalendarAddressBook(calendarModel.getCalendarAddressBook()),
//                new CalendarUserPrefs());
//        expectedModel.setPerson(lastPerson, editedPerson);
//
//        assertCommandSuccess(editCommand, calendarModel, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
//        Task editedPerson = calendarModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        CalendarModel expectedModel = new CalendarModelManager(
//                new CalendarAddressBook(calendarModel.getCalendarAddressBook()),
//                new CalendarUserPrefs());
//
//        assertCommandSuccess(editCommand, calendarModel, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showPersonAtIndex(calendarModel, INDEX_FIRST_PERSON);
//
//        Task personInFilteredList = calendarModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        Task editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
//                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        CalendarModel expectedModel = new CalendarModelManager(
//                new CalendarAddressBook(calendarModel.getCalendarAddressBook()),
//                new CalendarUserPrefs());
//        expectedModel.setPerson(calendarModel.getFilteredPersonList().get(0), editedPerson);
//
//        assertCommandSuccess(editCommand, calendarModel, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_duplicatePersonUnfilteredList_failure() {
//        Task firstPerson = calendarModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
//        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
//
//        assertCommandFailure(editCommand, calendarModel, EditCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//    @Test
//    public void execute_duplicatePersonFilteredList_failure() {
//        showPersonAtIndex(calendarModel, INDEX_FIRST_PERSON);
//
//        // edit task in filtered list into a duplicate in address book
//        Task personInList = calendarModel.getCalendarAddressBook()
//                .getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
//                new EditPersonDescriptorBuilder(personInList).build());
//
//        assertCommandFailure(editCommand, calendarModel, EditCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//    @Test
//    public void execute_invalidPersonIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(calendarModel.getFilteredPersonList().size() + 1);
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
//        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editCommand, calendarModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidPersonIndexFilteredList_failure() {
//        showPersonAtIndex(calendarModel, INDEX_FIRST_PERSON);
//        Index outOfBoundIndex = INDEX_SECOND_PERSON;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased()
//                < calendarModel.getCalendarAddressBook().getPersonList().size());
//
//        EditCommand editCommand = new EditCommand(outOfBoundIndex,
//                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        assertCommandFailure(editCommand, calendarModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);
//
//        // same values -> returns true
//        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
//        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
//    }
//
//}