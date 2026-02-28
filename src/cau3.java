import java.util.HashMap;

public class cau3 {

    public static void main(String[] args) {
        HashMap<String, Integer> ds = new HashMap<>();

        ds.put("Kiet", 696969);
        ds.put("Minh", 10000);
        ds.put("Phus", 28000);

        System.out.println("Sau khi them: " + ds);

        String ten = "Kiet";
        if (ds.containsKey(ten)) {
            System.out.println("luong cua " + ten + " " + ds.get(ten));
        }else {
            System.out.println("Khomg thay nhan vien");
        }

        String ten2 = "Nam";
        if (ds.containsKey(ten2)) {
            System.out.println("luong cua " + ten2 + " " + ds.get(ten2));
        } else {
            System.out.println("Khomg thay nhan vien");
        }

        String xoa = "Kiet";
        ds.remove(xoa);

        System.out.println("Sau khi xoa: " + ds);
    }
}

