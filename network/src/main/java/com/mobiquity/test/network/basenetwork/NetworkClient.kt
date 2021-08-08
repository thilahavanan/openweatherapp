package com.mobiquity.test.network.basenetwork

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.X509TrustManager

sealed class NetworkClient(
    private val interceptorList: List<Interceptor>? = null,
    private val connectTimeoutSeconds: Long,
    private val readTimeoutSeconds: Long
) : KoinComponent {
    class ApiClient(
        interceptorList: List<Interceptor>? = null,
        connectTimeoutSeconds: Long,
        readTimeoutSeconds: Long
    ) : NetworkClient(
        interceptorList = interceptorList,
        connectTimeoutSeconds = connectTimeoutSeconds,
        readTimeoutSeconds = readTimeoutSeconds
    ) {
        init {
            try {
                val trustCerts = arrayOf<X509TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                        //DO NOTHING
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
                        //DO NOTHING
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
                        return arrayOfNulls<X509Certificate?>(0)
                    }

                })
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, trustCerts, java.security.SecureRandom())

                val sslSocketFactory = sslContext.socketFactory

                httpClientBuilder.sslSocketFactory(sslSocketFactory, trustCerts[0])
                httpClientBuilder.hostnameVerifier(object : HostnameVerifier {
                    override fun verify(p0: String?, p1: SSLSession?): Boolean {
                        return true
                    }
                })
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

    private val requestOfflineInterceptor: RequestOfflineInterceptor by inject()
    private val appIdInterceptor: AppIdInterceptor by inject()
    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
        redactHeader("Authorization")
        redactHeader("Cache")
    }

    protected val httpClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder().apply {
            connectTimeout(connectTimeoutSeconds, TimeUnit.SECONDS)
            readTimeout(readTimeoutSeconds, TimeUnit.SECONDS)

            apply { interceptorList?.run { forEach { addInterceptor(it) } } }
            addInterceptor(appIdInterceptor)
            // addInterceptor(requestOfflineInterceptor)
            addInterceptor(loggingInterceptor)

        }
    }

    fun getOkhttpClient(): OkHttpClient {
        return httpClientBuilder.build()
    }
}
