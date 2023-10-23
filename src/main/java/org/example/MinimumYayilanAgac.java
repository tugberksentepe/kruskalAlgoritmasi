package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MinimumYayilanAgac {
    private int dugumSayisi;
    private List<Kenar> kenarlar;

    public MinimumYayilanAgac(int dugumSayisi) {
        this.dugumSayisi = dugumSayisi;
        this.kenarlar = new ArrayList<>();
    }

    public void kenarEkle(int kaynak, int hedef, double mesafe) {
        Kenar kenar = new Kenar(kaynak, hedef, mesafe);
        kenarlar.add(kenar);
    }

    public List<Kenar> kruskalMYA() {
        // Adım 0: Kenarları mesafe sırasına göre sırala
        Collections.sort(kenarlar, Comparator.comparingDouble(kenar -> kenar.mesafe));

        //Bu dizi, her bir düğümün hangi alt ağaçta olduğunu takip etmek için kullandığımız bir veri yapısıdır.
        //Başlangıçta her düğümün kendi alt ağacında olduğunu kabul ediyoruz.
        int[] parent = new int[dugumSayisi];
        for (int i = 0; i < dugumSayisi; i++) {
            parent[i] = i;
        }

        //Bu liste, sonuçta elde edilecek olan Minimum Yayılan Ağac'ı (MYA) temsil eder.
        List<Kenar> minimumYayilanAgac = new ArrayList<>();

        //Bu iki değişken, kenar sayısını ve bir sonraki kenara geçişi takip etmek için kullanılır.
        int kenarSayisi = 0;
        int i = 0;

        // Alt Ağın Oluşturulması adımı:
        while (kenarSayisi < dugumSayisi - 1) {
            Kenar siradakiKenar = kenarlar.get(i++); // Döngünün her dönüşünde, bir sonraki kenara geçilir.
            int kaynakUstDugum = bul(parent, siradakiKenar.kaynak);
            int hedefUstDugum = bul(parent, siradakiKenar.hedef);
            //Her kenarın başlangıç ve bitiş düğümleri,
            // bu kenarların bağlı olduğu alt ağaçların köklerini temsil eden
            // kaynakUstDugum ve hedefUstDugum olarak bulunur.

            if (kaynakUstDugum != hedefUstDugum) {
                minimumYayilanAgac.add(siradakiKenar);
                birlestir(parent, kaynakUstDugum, hedefUstDugum);
                kenarSayisi++;
            }
        }

        return minimumYayilanAgac;
    }

    // Bir düğümün ait olduğu alt ağacın kökünün kim olduğunu döndürür.
    // İki düğümün aynı alt ağaca ait olup olmadığını kontrol etmek için bu işlem yapılır.
    private int bul(int[] parent, int dugum) {
        if (parent[dugum] == dugum) { //Bu koşul, bir düğümün kendisinin alt ağaç kökü olup olmadığını kontrol eder.
            return dugum;
            //Eğer düğümün parent'i (kökü) kendisiyse,
            // bu düğümün ait olduğu alt ağacın köküdür ve o düğümün kimliği (indeksi) döndürülür.
        }
        return bul(parent, parent[dugum]);
        //Düğümün parent'i (kökü) kendisi değilse, bul fonksiyonu tekrar kendisini çağırır
        // bu düğümün parent'inin (kökünün) kendisi olup olmadığını kontrol eder.
        // Bu işlem, düğümün ait olduğu alt ağacın kökünü bulana kadar devam eder
    }

    private void birlestir(int[] parent, int x, int y) {
        int xKumesi = bul(parent, x);
        int yKumesi = bul(parent, y);
        parent[yKumesi] = xKumesi;
    }
}

