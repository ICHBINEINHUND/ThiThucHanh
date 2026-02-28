public class Cau4 {
        static boolean kiemTraDi(String s) {
            int d = s.length();
            for (int i = 0; i < d / 2; i++) {
                if (s.charAt(i) != s.charAt(d - 1 - i)) {
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            String chuoi = "abacdfgdcabba";
            String kq = "";

            for (int i = 0; i < chuoi.length(); i++) {
                for (int j = i + 1; j <= chuoi.length(); j++) {
                    String tam = chuoi.substring(i, j);
                    if (kiemTraDi(tam)) {
                        if (tam.length() > kq.length()) {
                            kq = tam;
                        }
                    }
                }
            }

            if (kq.length() > 1) {
                System.out.println("Ok roi " +kq);
            } else {
                System.out.println("Khong thay palindromic ");
            }
        }
}
