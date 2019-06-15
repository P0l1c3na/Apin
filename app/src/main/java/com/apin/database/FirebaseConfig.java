package com.apin.database;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public final class FirebaseConfig {

    public static FirebaseFirestore firebaseFirestore;

    public FirebaseConfig() {
    }

    public static FirebaseFirestore getFirebaseFirestore() {
        if (firebaseFirestore == null){
            firebaseFirestore = FirebaseFirestore.getInstance();

            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build();

            firebaseFirestore.setFirestoreSettings(settings);
        }
        return firebaseFirestore;
    }
}
