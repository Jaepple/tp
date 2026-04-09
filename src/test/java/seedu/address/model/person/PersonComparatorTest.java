package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.PersonComparator.SortCriteria;
import seedu.address.model.person.PersonComparator.SortField;
import seedu.address.model.person.PersonComparator.SortOrder;

public class PersonComparatorTest {

    @Test
    public void hashCode_equalComparators_sameHashCode() {
        List<SortCriteria> criteria = List.of(new SortCriteria(SortField.NAME, SortOrder.ASCENDING));
        PersonComparator comparator1 = new PersonComparator(criteria);
        PersonComparator comparator2 = new PersonComparator(criteria);

        assertEquals(comparator1, comparator2);
        assertEquals(comparator1.hashCode(), comparator2.hashCode());
    }

    @Test
    public void hashCode_differentComparators_differentHashCode() {
        PersonComparator comparator1 = new PersonComparator(
                List.of(new SortCriteria(SortField.NAME, SortOrder.ASCENDING)));
        PersonComparator comparator2 = new PersonComparator(
                List.of(new SortCriteria(SortField.PHONE, SortOrder.DESCENDING)));

        assertNotEquals(comparator1, comparator2);
        assertNotEquals(comparator1.hashCode(), comparator2.hashCode());
    }
}
