package com.survivalkit.backend.service

interface DecryptService {
    fun decode(encodeString: String): String
}