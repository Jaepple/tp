package seedu.address.model.person;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person} matches any of the given keywords for tags and emails.
 */
public class ListCommandPredicate implements Predicate<Person> {
    private final TagContainsKeywordPredicate tagPredicate;
    private final EmailContainsKeywordPredicate emailPredicate;

    public ListCommandPredicate(TagContainsKeywordPredicate tagPredicate, EmailContainsKeywordPredicate emailPredicate) {
        this.tagPredicate = tagPredicate;
        this.emailPredicate = emailPredicate;
    }

    @Override
    public boolean test(Person person) {
        boolean tagMatch = tagPredicate == null || tagPredicate.test(person);
        boolean emailMatch = emailPredicate == null || emailPredicate.test(person);
        return tagMatch && emailMatch;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ListCommandPredicate)) {
            return false;
        }

        ListCommandPredicate otherPredicate = (ListCommandPredicate) other;
        return Objects.equals(tagPredicate, otherPredicate.tagPredicate)
                && Objects.equals(emailPredicate, otherPredicate.emailPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tagPredicate", tagPredicate)
                .add("emailPredicate", emailPredicate)
                .toString();
    }
}

