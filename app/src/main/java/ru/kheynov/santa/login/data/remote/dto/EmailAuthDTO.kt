package remote.dto

@kotlinx.serialization.Serializable
data class RegisterViaEmailDTO(
    val username: String,
    val email: String,
    val password: String,
)

@kotlinx.serialization.Serializable
data class LoginViaEmailDTO(
    val email: String,
    val password: String,
)