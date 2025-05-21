package Proje;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class DoktorKapiGUI extends JFrame {

    public DoktorKapiGUI() {
        setTitle("Poliklinik Sıraları");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Başlık etiketi
        JLabel titleLabel = new JLabel("Poliklinik Sıraları");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Tablo modeli (düzenlenemez)
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Poliklinik", "Doktor", "Hasta"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // hücre düzenlemeyi kapat
            }
        };
        JTable table = new JTable(model);

        // Satır yüksekliği ve font
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Başlık stilini ortala ve kalın yap
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));

        // Hücreleri ortalamak için renderer
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Zebra striping (alternatif satır renklendirme)
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(245, 245, 245)); // açık gri
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                } else {
                    c.setBackground(new Color(135, 206, 250)); // seçili mavi ton
                }
                return c;
            }
        });

        // Verileri çek ve tabloya ekle
        Map<String, Map<String, List<String>>> veri = verileriGetir();
        List<String> poliklinikler = new ArrayList<>(veri.keySet());
        Collections.sort(poliklinikler);

        for (String poliklinik : poliklinikler) {
            Map<String, List<String>> doktorlar = veri.get(poliklinik);

            List<String> doktorIsimleri = new ArrayList<>(doktorlar.keySet());
            Collections.sort(doktorIsimleri);

            for (String doktor : doktorIsimleri) {
                List<String> hastalar = doktorlar.get(doktor);

                for (int i = 0; i < hastalar.size(); i++) {
                    String hastaAdi = (i + 1) + ". " + hastalar.get(i);
                    if (i == 0) {
                        model.addRow(new Object[]{poliklinik, doktor, hastaAdi});
                    } else {
                        model.addRow(new Object[]{"", "", hastaAdi});
                    }
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
    }

    private Map<String, Map<String, List<String>>> verileriGetir() {
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT poliklinik, doktor_adi, isim, soyisim FROM hasta ORDER BY poliklinik, doktor_adi, id";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    String poliklinik = rs.getString("poliklinik");
                    String doktor = rs.getString("doktor_adi");
                    String hasta = rs.getString("isim") + " " + rs.getString("soyisim");

                    data.putIfAbsent(poliklinik, new HashMap<>());
                    Map<String, List<String>> doktorMap = data.get(poliklinik);
                    doktorMap.putIfAbsent(doktor, new ArrayList<>());
                    doktorMap.get(doktor).add(hasta);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Veri alınırken hata oluştu: " + e.getMessage());
        }
        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DoktorKapiGUI().setVisible(true));
    }
}
