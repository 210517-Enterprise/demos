import Models.Person;
import com.revature.GSQL.GSQL.GSQL;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


public class TestGettingObj {
    final GSQL g   = GSQL.getInstance();

    @Test
    public void test_WrongUsername() {
        System.out.println("test wrong");
        g.addClass(Person.class);
        final Optional<List<Object>> l = g.getListObjectFromDB(Person.class,"firstname","jill");
        assertFalse(l.isPresent());
    }

    @Test
    public void test_CorrectUserName() {
        System.out.println("test correct");
        g.addClass(Person.class);
        final Optional<List<Object>> l = g.getListObjectFromDB(Person.class,"firstname","chris");
        assertTrue(l.isPresent());
        assertTrue(l.get().size() > 0);
        assertEquals("chris",((Person)l.get().get(0)).getFirstName());
    }

    @Test
    public void test_mutlipleConditions() {
        System.out.println("test multiple");
        g.addClass(Person.class);
        final Optional<List<Object>> l = g.getListObjectFromDB(Person.class,"firstname,lastname","chris,none","AND");
        assertTrue(l.isPresent());
        assertTrue(l.get().size() > 0);
        assertEquals("chris",((Person)l.get().get(0)).getFirstName());
        assertEquals("none",((Person)l.get().get(0)).getLastName());
    }
}
