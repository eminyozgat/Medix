package Proje;

public class Doktor extends Kisi {
    private String poliklinik;

    public Doktor(String isim, String soyisim, String poliklinik) {
        super(isim, soyisim);
        this.poliklinik = poliklinik;
    }

    public String getPoliklinik() {
        return poliklinik;
    }

    public void setPoliklinik(String poliklinik) {
        this.poliklinik = poliklinik;
    }

    @Override
    public void bilgiGoster() {
        System.out.println("Doktor: " + getIsim() + " " + getSoyisim() + ", Poliklinik: " + poliklinik);
    }
}
