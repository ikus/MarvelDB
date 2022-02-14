package com.example.marveldb.ui

import com.example.marveldb.data.CharacterRepository
import com.example.marveldb.data.model.Character
import javax.inject.Inject


class GetDetailUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(characterID:Int): Character {
        val detail= repository.geCharacterFromApi(characterID)
        return Character()
    }

}

