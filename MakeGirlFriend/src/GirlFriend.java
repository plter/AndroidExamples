/**
 * Created by plter on 7/17/15.
 */
public class GirlFriend {


    public GirlFriend(String name,int age){
        setName(name);
        setAge(age);
    }


    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    private String name;
    private int age;

    @Override
    public String toString() {
        return String.format("name:%s,\tage:%d",getName(),getAge());
    }
}
