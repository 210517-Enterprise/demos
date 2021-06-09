import com.revature.GSQL.GSQL.GSQL;
import Models.Person;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TestObjGetter {
    public static void main(String[] args) {
        GSQL.getInstance().addClass(Person.class);

        LinkedList<Object> p = (LinkedList<Object>) GSQL.getInstance().getListObjectFromDB(Person.class,"firstname","chris").get();
        for(Object pers: p) {
            System.out.println(pers.toString());
        }
        System.out.println("second test");
        Optional<List<Object>> g =  GSQL.getInstance().getListObjectFromDB(Person.class,"id,firstname,lastname","4,chris,nope","AND,AND");
        System.out.println(g.isPresent());
        if(g.isPresent()) {
            for (Object gers : g.get()) {
                System.out.println(gers.toString());
            }
        }
        System.out.println("third test");
        Optional<List<Object>> y = GSQL.getInstance().getListObjectFromDB(Person.class,"firstname,lastname","no,name","AND");
        if(y.isPresent()) {
            for (Object gers : y.get()) {
                System.out.println(gers.toString());
            }
        }
    }

}
