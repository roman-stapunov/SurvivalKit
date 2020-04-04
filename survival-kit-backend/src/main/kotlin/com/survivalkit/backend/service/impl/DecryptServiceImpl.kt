package com.survivalkit.backend.service.impl

import com.survivalkit.backend.service.DecryptService
import org.springframework.stereotype.Service
import java.util.*

@Service
class DecryptServiceImpl : DecryptService {
    override fun decode(encodeString: String): String {
        return String(Base64.getDecoder().decode(encodeString))
    }
}