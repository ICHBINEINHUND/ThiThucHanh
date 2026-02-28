public class Cau2 {

    public static void main(String[] args) {
        int[] a = {29, 10, 14, 37};
        int n = a.length;

//        System.out.print("mang ban dau: ");
//        for (int i = 0; i < n; i++) {
//            System.out.print(a[i] + " ");
//        }
//        System.out.println();

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }

            int tam = a[i];
            a[i] = a[min];
            a[min] = tam;

            System.out.print("Day la " + (i + 1)+" ");
            for (int k = 0; k < n; k++) {
                System.out.print(a[k] + " ");
            }
            System.out.println();
        }

        System.out.print("Mang sau khi sap xep");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}

