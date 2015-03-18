import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;


public class Test {
    private float red, white, blue;

    public Test() {
        red = white = blue = 0.0f;
    }

    public static void main(String args[]) {
        Map m = new HashMap();
        String str = null;
        m.put(new Test(), "mill");
        m.put(new Test(), "sill");
        System.out.println(m.size());
    }
}