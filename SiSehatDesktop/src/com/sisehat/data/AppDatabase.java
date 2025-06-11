package com.sisehat.data;

import com.sisehat.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Kelas ini berfungsi sebagai database sementara (in-memory).
 * Semua data aplikasi akan di-hardcode di sini.
 */
public class AppDatabase {

    // Properti 'static' artinya kita bisa mengaksesnya dari mana saja tanpa membuat objek AppDatabase.
    public static final List<Symptom> symptoms = new ArrayList<>();
    public static final List<Disease> diseases = new ArrayList<>();
    public static final List<FasilitasKesehatan> healthFacilities = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<History> histories = new ArrayList<>();

    /**
     * Method ini harus dipanggil sekali saat aplikasi pertama kali dijalankan
     * untuk mengisi semua data.
     */
    public static void initialize() {
        // --- DATA GEJALA ---
        symptoms.add(new Symptom(1, "Demam tinggi (>38°C)"));
        symptoms.add(new Symptom(2, "Sakit kepala parah"));
        symptoms.add(new Symptom(3, "Nyeri otot dan sendi"));
        symptoms.add(new Symptom(4, "Muncul ruam merah pada kulit"));
        symptoms.add(new Symptom(5, "Batuk kering terus-menerus"));
        symptoms.add(new Symptom(6, "Hidung tersumbat atau pilek"));
        symptoms.add(new Symptom(7, "Mual dan muntah"));

        // --- DATA PENYAKIT ---
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


        // --- DATA FASILITAS KESEHATAN ---
        healthFacilities.add(new FasilitasKesehatan("RSUP Dr. Hasan Sadikin", "Jl. Pasteur No.38, Bandung"));
        healthFacilities.add(new FasilitasKesehatan("RS Santo Borromeus", "Jl. Ir. H. Juanda No.100, Bandung"));
        healthFacilities.add(new FasilitasKesehatan("RSUD Kota Bandung", "Jl. Rumah Sakit No.22, Bandung"));
    }
}