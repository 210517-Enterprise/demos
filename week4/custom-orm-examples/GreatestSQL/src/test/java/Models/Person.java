package Models;

import com.revature.GSQL.Annotations.*;

import java.util.Objects;
@Table(name = "Users")
public class Person {
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    private String first_name;

    @Column(name = "lastname")
    private String last_name;

    @SerialKey(name = "pk")
    @PrimaryKey(name = "pk")
    private int sk;

    public Person() {
        super();
    }

    public Person(final String first_name,final String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;

    }

    public Person(final int id, final String first_name, final String last_name) {
        this(first_name,last_name);
        this.id = id;
    }

    @Getter(name = "pk")
    public final int getsk() {
        return sk;
    }
    @Setter(name = "pk")
    public void setPk(final int sk) {
        this.sk = sk;
    }

    @Getter(name = "id")
    public final int getId() {
        return id;
    }

    @Setter(name = "id")
    public void setId(final int id) {
        this.id = id;
    }

    @Getter(name = "firstname")
    public final String getFirstName() {
        return first_name;
    }

    @Setter(name = "firstname")
    public void setFirstName(final String first_name) {
        this.first_name = first_name;
    }

    @Getter(name = "lastname")
    public final String getLastName() {
        return last_name;
    }

    @Setter(name = "lastname")
    public void setLastName( final String last_name) {
        this.last_name = last_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && sk == person.sk && first_name.equals(person.first_name) && last_name.equals(person.last_name);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, first_name, last_name);
    }

    @Override
    public final String toString() {
        return "Model.Person{" +
                "pk=" + sk +
                ", id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
