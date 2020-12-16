package com.demoapp.utills

import android.text.TextUtils
import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.security.spec.InvalidParameterSpecException
import javax.crypto.*
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import javax.inject.Named


class AESUtills @Inject constructor(
    @Named("EncKey")
    val key: String
) {


    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun generateKey(): SecretKey {

        return SecretKeySpec(key.toByteArray(), "AES")
    }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidParameterSpecException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class,
        UnsupportedEncodingException::class
    )
    @JvmOverloads
    fun encryptString(message: String, secret: SecretKey = generateKey()): String {

        if(TextUtils.isEmpty(message)) return ""
        /* Encrypt the message. */
        var cipher: Cipher? = null
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secret)
        return toHex(cipher.doFinal(message.toByteArray(Charsets.UTF_8)))
    }

    @Throws(
        NoSuchPaddingException::class,
        NoSuchAlgorithmException::class,
        InvalidParameterSpecException::class,
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        BadPaddingException::class,
        IllegalBlockSizeException::class,
        UnsupportedEncodingException::class
    )
    fun decryptString(cipherText: String, secret: SecretKey = generateKey()): String {
        if(TextUtils.isEmpty(cipherText)) return ""
        /* Decrypt the message, given derived encContentValues and initialization vector. */
        var cipher: Cipher? = null
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secret)
        return String(cipher.doFinal(toByte(cipherText)))
    }


    fun toByte(hexString: String): ByteArray {
        val len = hexString.length / 2
        val result = ByteArray(len)
        for (i in 0 until len)
            result[i] = Integer.valueOf(
                hexString.substring(2 * i, 2 * i + 2),
                16
            ).toByte()
        return result
    }

    fun toHex(buf: ByteArray?): String {
        if (buf == null)
            return ""
        val result = StringBuffer(2 * buf.size)
        for (i in buf.indices) {
            appendHex(result, buf[i])
        }
        return result.toString()
    }


    private val HEX: String = "0123456789ABCDEF"


    private fun appendHex(sb: StringBuffer, b: Byte) {
        sb.append(HEX[b.toInt() shr 4 and 0x0f]).append(HEX[b.toInt() and 0x0f])
    }

}