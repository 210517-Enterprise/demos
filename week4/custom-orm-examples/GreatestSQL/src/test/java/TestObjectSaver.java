import com.revature.GSQL.GSQL.GSQL;
import Models.Person;
import com.revature.GSQL.ObjectMapper.ObjectCache;

public class TestObjectSaver {
    public static void main(String[] args) {
        GSQL.getInstance().addClass(Person.class);
        Person p = new Person(21,"chris","nopenope");
        GSQL.getInstance().addObjectToDB(p);
        System.out.println(p.toString());
        Person g = new Person(21,"chris","nopenope");
        GSQL.getInstance().addObjectToDB(g);
        Person r = new Person(21,"chris","nope2");
        GSQL.getInstance().addObjectToDB(r);
        System.out.println(ObjectCache.getInstance().getCache().toString());
    }
}
