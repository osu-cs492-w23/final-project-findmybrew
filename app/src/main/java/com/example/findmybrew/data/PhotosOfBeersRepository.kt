package com.example.findmybrew.data

class PhotosOfBeersRepository(private val dao: BreweryDao) {

    suspend fun insertPhotoOfBeer(beer: PhotoOfBeer) = dao.insert(beer)
    suspend fun deletePhotoOfBeer(beer: PhotoOfBeer) = dao.delete(beer)
    fun getAllPhotosOfBeers() = dao.getAllBeers()
}