//package seedu.address.logic.quiz.commands;
//
//import static java.util.Objects.requireNonNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
//
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.function.Predicate;
//
//import org.junit.jupiter.api.Test;
//
//import javafx.collections.ObservableList;
//import seedu.address.commons.core.GuiSettings;
//import seedu.address.logic.quiz.commands.exceptions.CommandException;
//import seedu.address.model.quiz.Model;
//import seedu.address.model.quiz.ReadOnlyAddressBook;
//import seedu.address.model.quiz.ReadOnlyUserPrefs;
//import seedu.address.model.quiz.person.Question;
//
//
//public class AddCommandTest {
//
//    @Test
//    public void constructor_nullQuestion_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new AddCommand(null));
//    }
//
//    @Test
//    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
//        ModelStubAcceptingQuestionAdded modelStub = new ModelStubAcceptingQuestionAdded();
//        Question validQuestion = new QuestionBuilder().build();
//
//        CommandResult commandResult = new AddCommand(validQuestion).execute(modelStub);
//
//        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validQuestion), commandResult.getFeedbackToUser());
//        assertEquals(Arrays.asList(validQuestion), modelStub.personsAdded);
//    }
//
//    @Test
//    public void execute_duplicateQuestion_throwsCommandException() {
//        Question validQuestion = new QuestionBuilder().build();
//        AddCommand addCommand = new AddCommand(validQuestion);
//        ModelStub modelStub = new ModelStubWithQuestion(validQuestion);
//
//        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON,
//                () -> addCommand.execute(modelStub));
//    }
//
//    @Test
//    public void equals() {
//        Question alice = new QuestionBuilder().withName("Alice").build();
//        Question bob = new QuestionBuilder().withName("Bob").build();
//        AddCommand addAliceCommand = new AddCommand(alice);
//        AddCommand addBobCommand = new AddCommand(bob);
//
//        // same object -> returns true
//        assertTrue(addAliceCommand.equals(addAliceCommand));
//
//        // same values -> returns true
//        AddCommand addAliceCommandCopy = new AddCommand(alice);
//        assertTrue(addAliceCommand.equals(addAliceCommandCopy));
//
//        // different types -> returns false
//        assertFalse(addAliceCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(addAliceCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(addAliceCommand.equals(addBobCommand));
//    }
//
//    /**
//     * A default model stub that have all of the methods failing.
//     */
//    private class ModelStub implements Model {
//        @Override
//        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyUserPrefs getUserPrefs() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public GuiSettings getGuiSettings() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setGuiSettings(GuiSettings guiSettings) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getAddressBookFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setAddressBookFilePath(Path addressBookFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addQuestion(Question person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setAddressBook(ReadOnlyAddressBook newData) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyAddressBook getAddressBook() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasQuestion(Question person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteQuestion(Question target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setQuestion(Question target, Question editedQuestion) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Question> getFilteredQuestionList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredQuestionList(Predicate<Question> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//    }
//
//    /**
//     * A Model stub that contains a single person.
//     */
//    private class ModelStubWithQuestion extends ModelStub {
//        private final Question person;
//
//        ModelStubWithQuestion(Question person) {
//            requireNonNull(person);
//            this.person = person;
//        }
//
//        @Override
//        public boolean hasQuestion(Question person) {
//            requireNonNull(person);
//            return this.person.isSameQuestion(person);
//        }
//    }
//
//    /**
//     * A Model stub that always accept the person being added.
//     */
//    private class ModelStubAcceptingQuestionAdded extends ModelStub {
//        final ArrayList<Question> personsAdded = new ArrayList<>();
//
//        @Override
//        public boolean hasQuestion(Question person) {
//            requireNonNull(person);
//            return personsAdded.stream().anyMatch(person::isSameQuestion);
//        }
//
//        @Override
//        public void addQuestion(Question person) {
//            requireNonNull(person);
//            personsAdded.add(person);
//        }
//
//        @Override
//        public ReadOnlyAddressBook getAddressBook() {
//            return new AddressBook();
//        }
//    }
//
//}