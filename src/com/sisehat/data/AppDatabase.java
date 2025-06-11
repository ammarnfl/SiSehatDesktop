package com.sisehat.data;

import com.sisehat.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppDatabase {
    public static final List<User> users = new ArrayList<>();
    public static final List<Symptom> symptoms = new ArrayList<>();
    public static final List<Disease> diseases = new ArrayList<>();
    public static final List<FasilitasKesehatan> healthFacilities = new ArrayList<>();
    public static final List<History> histories = new ArrayList<>();

    public static void initialize() {
        // Data Gejala (tidak berubah)
        symptoms.add(new Symptom(1, "Demam tinggi (>38°C)"));
        symptoms.add(new Symptom(2, "Sakit kepala parah"));
        symptoms.add(new Symptom(3, "Nyeri otot dan sendi"));
        symptoms.add(new Symptom(4, "Muncul ruam merah pada kulit"));
        symptoms.add(new Symptom(5, "Batuk kering terus-menerus"));
        symptoms.add(new Symptom(6, "Hidung tersumbat atau pilek"));
        symptoms.add(new Symptom(7, "Mual dan muntah"));

        // Data Penyakit (tidak berubah)
        Disease dbd = new Disease("Demam Berdarah Dengue (DBD)");
        dbd.setRelatedSymptomIds(Arrays.asList(1, 2, 3, 4, 7));
        diseases.add(dbd);

        Disease flu = new Disease("Influenza (Flu)");
        flu.setRelatedSymptomIds(Arrays.asList(1, 2, 3, 5, 6));
        diseases.add(flu);

        Disease campak = new Disease("Campak");
        campak.setRelatedSymptomIds(Arrays.asList(1, 4, 5, 6));
        diseases.add(campak);

        Disease covid19 = new Disease("COVID-19");
        covid19.setRelatedSymptomIds(Arrays.asList(1, 2, 3, 5));
        diseases.add(covid19);

        // --- DATA FASILITAS KESEHATAN (DIUPDATE) ---
        healthFacilities.clear(); // Kosongkan dulu untuk menghindari duplikat
        healthFacilities.add(new FasilitasKesehatan(
                "RS Santo Borromeus", "Jl. Ir. H. Juanda No.100", "Bandung",
                "Senin - Minggu, 24 Jam", true, "6281122334455",
                4.8, "RS Umum", "/images/rs_borromeus.png",
                Arrays.asList("Demam Berdarah Dengue (DBD)", "Influenza (Flu)", "COVID-19")));

        healthFacilities.add(new FasilitasKesehatan(
                "RSUP Dr. Hasan Sadikin", "Jl. Pasteur No.38", "Bandung",
                "Senin - Jumat, 08:00 - 16:00", true, "6281298765432",
                4.7, "RS Umum", "/images/rs_hasan_sadikin.png",
                Arrays.asList("Demam Berdarah Dengue (DBD)", "Influenza (Flu)", "COVID-19", "Campak")));

        healthFacilities.add(new FasilitasKesehatan(
                "Klinik Prodia Buah Batu", "Jl. Buah Batu No. 123", "Bandung",
                "Senin - Sabtu, 07:00 - 19:00", false, "6285611112222",
                4.5, "Klinik", "/images/klinik_prodia.png",
                Arrays.asList("Influenza (Flu)")));

        healthFacilities.add(new FasilitasKesehatan(
                "Puskesmas Garuda", "Jl. Dadali No. 81", "Bandung",
                "Senin - Jumat, 08:00 - 14:00", true, "6287733334444",
                4.2, "Puskesmas", "/images/puskesmas_garuda.png",
                Arrays.asList("Influenza (Flu)", "Campak")));
    }
}