package remote.exceptions

import okio.IOException

class ServerErrorException (private val errorMessage : String = "") : IOException() {
    override val message: String
        get() = errorMessage
}