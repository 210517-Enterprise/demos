import Models.Person;
import com.revature.GSQL.GSQL.GSQL;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class TestCache {
    final GSQL g   = GSQL.getInstance();


    @Test
    public void test_CorrectUserName() {
        System.out.println("test correct");
        g.addClass(Person.class);
        final Optional<List<Object>> l = g.getListObjectFromDB(Person.class,"firstname","chris");
        assertTrue(l.isPresent());
        assertTrue(l.get().size() > 0);
        assertEquals("chris",((Person)l.get().get(0)).getFirstName());
        System.out.println("test correct");
        final Optional<List<Object>> t = g.getListObjectFromDB(Person.class,"firstname","chris");
        assertTrue(t.isPresent());
        assertTrue(t.get().size() > 0);
        Person me = (Person) t.get().get(0);
        assertEquals("chris",me.getFirstName());
    }
}
