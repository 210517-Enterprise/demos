import com.revature.GSQL.GSQL.GSQL;
import Models.Person;

public class TestObjectUpdater {
    public static void main(String[] args) {
        GSQL g = GSQL.getInstance();
        g.addClass(Person.class);
       // LinkedList<Object> p = (LinkedList<Object>) GSQL.getInstance().getListObjectFromDB(Person.class, "firstname", "chris");
       // Person me = (Person) p.get(0);
        //me.setFirstName("spaceghost");
       // g.UpdateObjectInDB(me,"firstname","id",String.valueOf(me.getId()),"");
//         LinkedList<Object> p = (LinkedList<Object>) GSQL.getInstance().getListObjectFromDB(Person.class, "firstname", "chad").get();
//         Person chad = (Person) p.get(0);
//         chad.setFirstName("zorak");
//         g.UpdateObjectInDB(chad,"firstname");
//         System.out.println("updated object");
        final Person r = (Person) g.getListObjectFromDB(Person.class,"pk",String.valueOf(1)).get().get(0);
        System.out.println(r);
        final Person t = (Person) g.getListObjectFromDB(Person.class,"pk",String.valueOf(1)).get().get(0);
//        r.setFirstName("notchad");
        r.setLastName("none");
        g.UpdateObjectInDB(r,"firstname,lastname");
        g.beginCommit();

    }
}
