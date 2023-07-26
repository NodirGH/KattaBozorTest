package remote.exceptions

import okio.IOException

class BackendErrorException(private val errorMessage : String = "") : IOException() {
    override val message: String
        get() = errorMessage
}