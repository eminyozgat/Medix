# MEDIX – Hastane Bilgi ve Yönetim Sistemi

## Proje Tanımı
Medix; hasta, doktor ve poliklinik sıra takibini yönetmek için geliştirilen bir masaüstü Hastane Bilgi Sistemidir. Kullanıcı (hastanede kayıt alan görevli) giriş yaptıktan sonra hasta kayıt işlemine başlayabilir. Veritabanı ile senkronize çalışarak kayıt işlemlerini kalıcı hale getirir.

## Ana Özellikler
- Giriş ekranı (admin)
- Hasta kayıt ekranı
- Poliklinik bazlı doktor hasta listeleri
- Veritabanı ile entegre çalışan Swing arayüzü

## Kullanılan Teknolojiler
- Java 
- Swing (GUI)
- MySQL 
- JDBC
- IntelliJ IDEA / Eclipse

## Veritabanı Yapısı
MySQL veritabanında aşağıdaki tablolar kullanılmaktadır:
- `kullanici` (giriş kontrolü)
- `hasta` (hasta bilgileri)

## Kurulum Talimatları

1. **Veritabanını Oluşturun:**
   - `veritabani.sql` dosyasındaki sorguları MySQL'e uygulayın.

2. **Java Projesini Açın:**
   - `Proje` klasörünü IntelliJ IDEA veya Eclipse ile açın.

3. **JDBC Bağlantı Ayarlarını Yapın:**
   - `DBConnection.java` içindeki url, username, password alanlarını kendi MySQL bilgilerinize göre düzenleyin.

4. **Projeyi Çalıştırın:**
   - `Main.java` dosyasını çalıştırarak uygulamayı başlatabilirsiniz.

## Giriş Bilgileri 

Admin kullanıcı:  
- Kullanıcı Adı: `admin`  
- Şifre: `12345`  

> Not: Kullanıcı bilgilerini `kullanici` tablosuna manuel olarak ekleyebilirsiniz.

## Geliştiriciler

- Muhammed Emin Yozğat – Veritabanı & Arayüz Tasarımı   
- Doğukan Sarıkaya – Sınıf Yapısı & JDBC Kodlama

## Geliştirme Önerileri
- Doktor kullanıcısı ve işlemleri
- Doktorun geçmiş kayıtları görmesi
- Kullanıcı ekleme/silme paneli
- JavaFX ile modern arayüz

## Lisans
Bu proje sadece eğitim amaçlı geliştirilmiştir. Her hakkı proje sahiplerine aittir.
