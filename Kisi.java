package Proje;

public abstract class Kisi {
    private String isim;
    private String soyisim;
    private String telefon;

    public Kisi(String isim, String soyisim, String telefon) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.telefon = telefon;
    }
    public Kisi(String isim, String soyisim) {
        this.isim = isim;
        this.soyisim = soyisim;
    }

    // Getter ve Setter'lar
    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }
    public String getSoyisim() {
        return soyisim;
    }
    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }
    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public abstract void bilgiGoster();
}
