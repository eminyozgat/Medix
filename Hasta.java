package Proje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;

public class Hasta extends Kisi implements VeritabaniIslemleri {
    private String tc;
    private int yas;
    private String poliklinik;
    private Doktor doktor;

    public Hasta(String isim, String soyisim, String telefon, String tc, int yas, String poliklinik, Doktor doktor) {
        super(isim, soyisim, telefon);
        this.tc = tc;
        this.yas = yas;
        this.poliklinik = poliklinik;
        this.doktor = doktor;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public String getPoliklinik() {
        return poliklinik;
    }

    public void setPoliklinik(String poliklinik) {
        this.poliklinik = poliklinik;
    }

    public Doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
    }

    @Override
    public void bilgiGoster() {
        System.out.println("Hasta: " + getIsim() + " " + getSoyisim() +
                ", TC: " + tc + ", Yaş: " + yas + ", Poliklinik: " + poliklinik +
                ", Doktor: " + doktor.getIsim() + " " + doktor.getSoyisim());
    }

    private boolean tcDogruMu() {
        return Pattern.matches("\\d{11}", tc);
    }

    @Override
    public boolean kaydet() {
        if (!tcDogruMu()) {
            System.out.println("TC Kimlik numarası 11 haneli rakamlardan oluşmalıdır.");
            return false;
        }

        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "INSERT INTO hasta (isim, soyisim, telefon, tc, yas, poliklinik, doktor_adi) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, getIsim());
            pst.setString(2, getSoyisim());
            pst.setString(3, getTelefon());
            pst.setString(4, tc);
            pst.setInt(5, yas);
            pst.setString(6, poliklinik);
            pst.setString(7, doktor.getIsim() + " " + doktor.getSoyisim());

            int added = pst.executeUpdate();
            pst.close();
            return added > 0;
        } catch (Exception e) {
            System.out.println("Veritabanı kaydetme hatası: " + e.getMessage());
            return false;
        }
    }
}