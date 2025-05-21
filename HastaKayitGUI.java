package Proje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HastaKayitGUI extends JFrame {
    private JTextField tfIsim, tfSoyisim, tfTelefon, tfTc, tfYas, tfPoliklinik, tfDoktorIsim, tfDoktorSoyisim;

    public HastaKayitGUI() {
        setTitle("Hasta Kayıt");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // İlk sütun: Label
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(new JLabel("Hasta Adı:"), gbc);
        gbc.gridy++;
        add(new JLabel("Hasta Soyadı:"), gbc);
        gbc.gridy++;
        add(new JLabel("Telefon:"), gbc);
        gbc.gridy++;
        add(new JLabel("TC Kimlik:"), gbc);
        gbc.gridy++;
        add(new JLabel("Yaş:"), gbc);
        gbc.gridy++;
        add(new JLabel("Poliklinik:"), gbc);
        gbc.gridy++;
        add(new JLabel("Doktor Adı:"), gbc);
        gbc.gridy++;
        add(new JLabel("Doktor Soyadı:"), gbc);


        // İkinci sütun: TextField
        gbc.gridx = 1;
        gbc.gridy = 0;

        tfIsim = new JTextField(20);
        add(tfIsim, gbc);
        gbc.gridy++;

        tfSoyisim = new JTextField(20);
        add(tfSoyisim, gbc);
        gbc.gridy++;

        tfTelefon = new JTextField(20);
        add(tfTelefon, gbc);
        gbc.gridy++;

        tfTc = new JTextField(20);
        add(tfTc, gbc);
        gbc.gridy++;

        tfYas = new JTextField(20);
        add(tfYas, gbc);
        gbc.gridy++;

        tfPoliklinik = new JTextField(20);
        add(tfPoliklinik, gbc);
        gbc.gridy++;

        tfDoktorIsim = new JTextField(20);
        add(tfDoktorIsim, gbc);
        gbc.gridy++;

        tfDoktorSoyisim = new JTextField(20);
        add(tfDoktorSoyisim, gbc);
        gbc.gridy++;

        // Butonlar alt satıra
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        JButton btnKaydet = new JButton("Kaydet");
        JButton btnTemizle = new JButton("Temizle");
        buttonPanel.add(btnKaydet);
        buttonPanel.add(btnTemizle);

        add(buttonPanel, gbc);

        btnKaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kaydet();
            }
        });

        btnTemizle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temizle();
            }
        });
    }

    private void kaydet() {
        String isim = tfIsim.getText().trim();
        String soyisim = tfSoyisim.getText().trim();
        String telefon = tfTelefon.getText().trim();
        String tc = tfTc.getText().trim();
        String yasStr = tfYas.getText().trim();
        String poliklinik = tfPoliklinik.getText().trim();
        String doktorIsim = tfDoktorIsim.getText().trim();
        String doktorSoyisim = tfDoktorSoyisim.getText().trim();

        // İsim soyisim kontrolü
        if (isim.length() < 2) {
            JOptionPane.showMessageDialog(this, "İsim en az 2 karakter olmalıdır.");
            tfIsim.requestFocus();
            return;
        }

        if (soyisim.length() < 2) {
            JOptionPane.showMessageDialog(this, "Soyisim en az 2 karakter olmalıdır.");
            tfSoyisim.requestFocus();
            return;
        }

        // Telefon kontrolü: sadece rakam ve uzunluk 10 veya 11 olabilir
        if (!telefon.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(this, "Telefon numarası 10 veya 11 haneli rakamlardan oluşmalıdır.");
            tfTelefon.requestFocus();
            return;
        }

        // TC Kimlik kontrolü: sadece rakam, 11 hane
        if (!tc.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "TC Kimlik numarası 11 haneli rakamlardan oluşmalıdır.");
            tfTc.requestFocus();
            return;
        }

        // Yaş kontrolü: sayı ve 0'dan büyük olmalı
        int yas;
        try {
            yas = Integer.parseInt(yasStr);
            if (yas <= 0) {
                JOptionPane.showMessageDialog(this, "Yaş 0'dan büyük olmalıdır.");
                tfYas.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Yaş alanı sayı olmalıdır.");
            tfYas.requestFocus();
            return;
        }

        // Poliklinik kontrolü: boş olamaz
        if (poliklinik.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Poliklinik boş bırakılamaz.");
            tfPoliklinik.requestFocus();
            return;
        }

        // Doktor bilgileri kontrolü (zorunlu)
        if (doktorIsim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Doktor adı boş bırakılamaz.");
            tfDoktorIsim.requestFocus();
            return;
        }

        if (doktorSoyisim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Doktor soyadı boş bırakılamaz.");
            tfDoktorSoyisim.requestFocus();
            return;
        }

        // Eğer tüm kontroller geçtiyse kaydet
        try {
            Doktor doktor = new Doktor(doktorIsim, doktorSoyisim, poliklinik);
            Hasta hasta = new Hasta(isim, soyisim, telefon, tc, yas, poliklinik, doktor);

            if (hasta.kaydet()) {
                JOptionPane.showMessageDialog(this, "Kayıt başarılı!");
                doktorKapiGoster();
                temizle();
            } else {
                JOptionPane.showMessageDialog(this, "Kayıt başarısız!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hata: " + ex.getMessage());
        }
    }


    private void temizle() {
        tfIsim.setText("");
        tfSoyisim.setText("");
        tfTelefon.setText("");
        tfTc.setText("");
        tfYas.setText("");
        tfPoliklinik.setText("");
        tfDoktorIsim.setText("");
        tfDoktorSoyisim.setText("");
    }

    private void doktorKapiGoster() {
        DoktorKapiGUI kapiGUI = new DoktorKapiGUI();
        kapiGUI.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HastaKayitGUI().setVisible(true);
        });
    }
}