import com.revature.GSQL.Annotations.PrimaryKey;
import com.revature.GSQL.GSQL.GSQL;
import com.revature.GSQL.GSQLogger.GSQLogger;
import com.revature.GSQL.META.MetaConstructor;
import Models.Person;
import com.revature.GSQL.ObjectMapper.ObjectCache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class TestObjRemover {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        try {
            final GSQL g = GSQL.getInstance();
            Object obj = new Person();
            g.addClass(Person.class);
            Person p = (Person) g.getListObjectFromDB(Person.class, "firstname,lastname", "chris,nichols", "AND").get().get(0);
            System.out.println(p.toString());
            System.out.println(ObjectCache.getInstance().getCache().toString());
            g.removeObjectFromDB(p);
            System.out.println("removed obj from cache");
            System.out.println(ObjectCache.getInstance().getCache().toString());
            String name = Arrays.stream(obj.getClass().getDeclaredFields()).filter(f -> f.getDeclaredAnnotation(PrimaryKey.class) != null).map(z -> z.getDeclaredAnnotation(PrimaryKey.class).name()).findAny().get();
            System.out.println(name);
            HashMap<String,Method> hg = MetaConstructor.getInstance().getModels().get(Person.class.getSimpleName()).getGetters();
            Method m = hg.get(name);
            System.out.println(m.invoke(p));
        }catch(Exception e) {
            GSQLogger.getInstance().writeError(e);
        }
    }
}
