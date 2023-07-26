package remote.interceptors

import com.example.kattabozortest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import remote.exceptions.*
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException


class JsonParseInterceptor(
    private val securityListener: Listener
) : Interceptor {

    interface Listener {
        fun openHomeWithClearStack()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        request.header("Content-Type", "application/json")
        request.header("X-App-Platform", "android")


        try {
            val response = chain.proceed(request.build())
            if (BuildConfig.DEBUG) {
                printTlsAndCipherSuiteInfo(response)
            }
            if (response.isSuccessful) {

            } else {
                val responseBody = response.body()?.string()
                val hasErrorBody = response.body() != null && responseBody?.isNotEmpty() == true
                val errorMessage = if (hasErrorBody) {
                    val errorJsonObject = JSONObject(responseBody)
                    if (errorJsonObject.has("message"))
                        errorJsonObject.getString("message")
                    else
                    "Unexpected error"
                } else
                    "Unexpected error"
                when (response.code()) {
                    in 500..505 -> throw ServerErrorException(errorMessage = "Server Error")
                    401 -> {
                        securityListener.openHomeWithClearStack()
                    }
                    in 400..499 -> {
                        throw BadRequestException(errorMessage = errorMessage)
                    }
                    else -> {}
                }
            }
            return response
        } catch (e: Exception) {
            when (e) {
                is SSLHandshakeException -> {
                    throw FakeTimeException()
                }
                is SocketTimeoutException -> {
                    throw CustomSocketTimeoutException()
                }
                is JSONException -> {
                    throw BackendErrorException("Something is coming wrong from the server!")
                }
                else -> throw e
            }
        }

    }

    private fun printTlsAndCipherSuiteInfo(response: Response?) {
        if (response != null) {
            val handshake = response.handshake()
            if (handshake != null) {
                val cipherSuite = handshake.cipherSuite()
                val tlsVersion = handshake.tlsVersion()
                Timber.tag("OkHttp3-SSLHandshake").v("TLS: $tlsVersion, CipherSuite: $cipherSuite")
            }
        }
    }
}