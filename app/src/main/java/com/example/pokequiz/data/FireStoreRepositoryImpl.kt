package com.example.pokequiz.data

import com.example.pokequiz.core.type.ErrorType
import com.example.pokequiz.domain.FireStoreRepository
import com.example.pokequiz.domain.model.ScoreModel
import com.example.pokequiz.domain.state.ScoreState
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FireStoreRepositoryImpl @Inject constructor(
    private val firebaseFireStore: FirebaseFirestore
) : FireStoreRepository {

    override suspend fun getScorePoints(): Task<ScoreState> {
        val documentRef = firebaseFireStore.collection("points")
        return documentRef.get().continueWith { task ->
            if (task.isSuccessful) {
                val notesList = mutableListOf<ScoreModel>()
                if (!task.result.isEmpty) {
                    for (data in task.result.documents) {
                        val notesModel: ScoreModel? = data.toObject(ScoreModel::class.java)
                        if (notesModel != null) {
                            notesList.add(notesModel)
                        }
                    }
                }
                ScoreState.Success(notesList)
            } else {
                throw Exception(ErrorType.GetPoints.errorMessage.toString())
            }
        }
    }

    override suspend fun saveScorePoints(scoreModel: ScoreModel): Task<ScoreState> {
        val documentRef = firebaseFireStore.collection("points")
        return documentRef.document().set(scoreModel).continueWith { task ->
            if (task.isSuccessful) {
                ScoreState.Success(emptyList())
            } else {
                throw Exception(ErrorType.SavePoints.errorMessage.toString())
            }
        }
    }
}