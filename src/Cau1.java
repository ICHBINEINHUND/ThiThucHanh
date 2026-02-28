public class Cau1 {
    public static void main(String[] args) {
        int[] a = {12, 35, 1, 10,2 };
        int threshold = 15;
        int n = a.length;
        int tong = 0;

        int max1 = a[0];
        int max2 = 0;

        int demthreshhold = 0;

        for (int i = 0; i < n; i++) {
            tong += a[i];
            if (a[i] > max1) {
                max2 = max1;
                max1 = a[i];
            } else if (a[i] > max2 && a[i] < max1) {
                max2 = a[i];
            }
            if (a[i] > threshold) {
                demthreshhold++;
            }
        }

        double trungbinnh = (double) tong / n;

        System.out.println("trung binh la binh la: " + trungbinnh);
        System.out.println("so lon thu 2 la: " + max2);
        System.out.println("so phan tu lon hon muc la: " + demthreshhold);
    }
}
