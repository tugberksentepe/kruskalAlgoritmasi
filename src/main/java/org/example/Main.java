package org.example;

import java.util.*;

//Bu algoritmada şu aşamalar izlenir:

// Kenarların Sıralanması: İlk adımda, tüm kenarlar (bağlantılar) minimize edilmek istenilen parametrelere göre sıralanır.
// (Bu parametre (mesafe, maliyet vb.) üzerinde yapılan optimizasyonu temsil eder.)
//
//Alt Ağın Oluşturulması: Kenarlar verilen parametreler sırasına göre küçükten büyüğe sıralandıktan sonra,
// algoritma en düşük parametreye sahip kenarla başlar ve bu kenarı Minimum Yayılan Ağaç'a (MYA) ekler.
//
//Döngü Kontrolü: Her kenar eklendiğinde, bir döngü oluşturup oluşturmadığı kontrol edilir.
// Döngü, eklenen kenarın yeni bir döngü oluşturmasını engellemek için kullanılır.
// Yani, ağaç yapısı oluşturulurken bir düğümün daha önce eklenip eklenmediği de kontrol edilir.
//
//Alt Ağın Genişletilmesi: Eğer eklenen kenar döngü oluşturmuyorsa, bu kenar MYA'ya eklenir.
// Aksi takdirde, bir sonraki en küçük ağırlığa sahip kenara geçilir.
//
//Bitiş Kontrolü: Eğer ağaçta (MYA) düğüm sayısı (kenar sayısı + 1) düğüm sayısına eşitse, işlem tamamlanır.
// Çünkü bir MYA'da düğüm sayısı (n-1) kadar olur, burada 'n' toplam düğüm sayısını temsil eder.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Düğüm Sayısını Girin: ");
        int dugumSayisi = scanner.nextInt();
        MinimumYayilanAgac mya = new MinimumYayilanAgac(dugumSayisi);

        System.out.print("Kenar Sayısını Girin: ");
        int kenarSayisi = scanner.nextInt();

        for (int i = 0; i < kenarSayisi; i++) {
            System.out.println("Kenar " + (i + 1) + " için bilgileri girin:");
            System.out.print("Başlangıç Düğümü: ");
            int kaynak = scanner.nextInt();
            System.out.print("Bitiş Düğümü: ");
            int hedef = scanner.nextInt();
            System.out.print("Mesafe: ");
            double mesafe = scanner.nextDouble();

            mya.kenarEkle(kaynak, hedef, mesafe);
        }

        List<Kenar> minimumYayilanAgac = mya.kruskalMYA();

        System.out.println("Minimum Yayılan Ağac Kenarları:");
        for (Kenar kenar : minimumYayilanAgac) {
            System.out.println(kenar.kaynak + " - " + kenar.hedef + " -> " + kenar.mesafe);
        }
    }
}