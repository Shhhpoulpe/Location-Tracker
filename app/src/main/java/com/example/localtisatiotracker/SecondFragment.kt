package com.example.localtisatiotracker

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SecondFragment : Fragment() {
    private var locationId = mutableListOf<String>()
    private var location = mutableListOf<Double>()
    private var location2 = mutableListOf<Double>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //locationId location location2 = getDocuments()
    }

    fun getDocuments(): Triple<MutableList<String>, MutableList<Double>, MutableList<Double>> {
        var locationId = mutableListOf<String>()
        var location = mutableListOf<Double>()
        var location2 = mutableListOf<Double>()
        val db = Firebase.firestore
        db.collection("Localisation")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("getDocuments", "${document.id} => ${document.data}")
                        locationId.add(document.id)
                        document.getString("Latitude")?.toDouble()?.let { location.add(it) }
                        document.getString("Longitude")?.toDouble()?.let { location2.add(it) }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("getDocuments", "Error getting documents.", exception)
                }
        return Triple(locationId,location,location2)
    }

    fun deleteDocument(documentId: String){
        val db = Firebase.firestore
        db.collection("Localisation").document(documentId)
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }
}