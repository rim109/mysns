package com.example.letter.common.exception

data class ModelNotFoundException(val modelName: String, val id: Long?) :
    RuntimeException("Model $modelName not found with given id: $id")