package Proje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GirisGUI extends JFrame {
    private JTextField tfKullaniciAdi;
    private JPasswordField pfSifre;

    public GirisGUI() {
        setTitle("Giriş Ekranı");
        setSize(300, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Kullanıcı Adı
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Kullanıcı Adı:"), gbc);

        tfKullaniciAdi = new JTextField(15);
        gbc.gridx = 1;
        add(tfKullaniciAdi, gbc);

        // Şifre
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Şifre:"), gbc);

        pfSifre = new JPasswordField(15);
        gbc.gridx = 1;
        add(pfSifre, gbc);

        // Giriş Butonu
        JButton btnGiris = new JButton("Giriş");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnGiris, gbc);

        btnGiris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    girisYap();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(GirisGUI.this, "Veritabanı hatası: " + ex.getMessage());
                }
            }
        });
    }

    private void girisYap() throws SQLException {
        String kullaniciAdi = tfKullaniciAdi.getText().trim();
        String sifre = new String(pfSifre.getPassword());

        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM kullanici WHERE kullanici_adi = ? AND sifre = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, kullaniciAdi);
            pst.setString(2, sifre); // hash'leme yoksa düz metin

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Giriş başarılı!");
                    HastaKayitGUI hastaGUI = new HastaKayitGUI();
                    hastaGUI.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Kullanıcı adı veya şifre hatalı!");
                    pfSifre.setText("");
                    pfSifre.requestFocus();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GirisGUI().setVisible(true));
    }
}
