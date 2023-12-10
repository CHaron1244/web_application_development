package cn.itcast.session.entity;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
public class CakeDB {
    private static Map<String, Cake> cake = new LinkedHashMap<String, Cake>();
    static {
        cake.put("1", new Cake("1", "A类蛋糕"));
        cake.put("2", new Cake("2", "B类蛋糕"));
        cake.put("3", new Cake("3", "C类蛋糕"));
        cake.put("4", new Cake("4", "D类蛋糕"));
        cake.put("5", new Cake("5", "E类蛋糕"));
    }
    // 获得所有的蛋糕
    public static Collection<Cake> getAll() {
        return cake.values();
    }
    // 根据指定的id获蛋糕
    public static Cake getCake(String id) {
        return cake.get(id);
    }
}
