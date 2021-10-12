package com.Skyflow

import Skyflow.*
import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import com.skyflow_android.BuildConfig
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Before
import org.junit.Test

class InsertTest {

    lateinit var skyflow : Client
    lateinit var context: Context

    @Before
    fun setup() {
        val configuration = Configuration(
            "b359c43f1b844ff4bea0f098d2c",
            "https://sb1.area51.vault.skyflowapis.tech",
            DemoTokenProvider()
        )
        skyflow = Client(configuration)
        context = ApplicationProvider.getApplicationContext<Context>()
    }


    @Test
    fun testEmptyVaultURL() //applicable for null value also
    {
        val configuration = Configuration( "b359c43f1b844ff4bea0f098d2",
            "",
            DemoTokenProvider())
        val skyflow = Client(configuration)
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table", "cards")
        val fields = JSONObject()
        fields.put("fullname", "san")
        record.put("fields", fields)
        recordsArray.put(record)
        records.put("records", recordsArray)
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
            }

            override fun onFailure(exception: Any) {
                 assertEquals((exception as SkyflowError).message,SkyflowErrorCode.EMPTY_VAULT_URL.getMessage())
            }

        })
    }
    @Test
    fun testEmptyVaultID()
    {
        val configuration = Configuration( "",
            "https://sb1.area51.vault.skyflowapis.tech",
            DemoTokenProvider())
        val skyflow = Client(configuration)
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table", "cards")
        val fields = JSONObject()
        fields.put("fullname", "san")
        fields.put("card_number", "41111111111")
        fields.put("expiry_date","11/22")
        record.put("fields", fields)
        recordsArray.put(record)
        records.put("records", recordsArray)
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
               }
            override fun onFailure(exception: Any) {
                 assertEquals((exception as SkyflowError).message,SkyflowErrorCode.EMPTY_VAULT_ID.getMessage())
            }

        })
    }
    @Test
    fun testInvalidVaultURL()
    {
        val configuration = Configuration( "b359c43f1b844ff4bea0f098",
            "https://na1.area51.vault.skyfwapis.com",
            DemoTokenProvider())
        val skyflow = Client(configuration)
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table", "cards")
        val fields = JSONObject()
        fields.put("fullname", "san")
        fields.put("card_number", "41111111111")
        fields.put("expiry_date","11/22")
        record.put("fields", fields)
        recordsArray.put(record)
        records.put("records", recordsArray)
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
            }

            override fun onFailure(exception: Any) {
                val skyflowError = SkyflowError(SkyflowErrorCode.INVALID_VAULT_URL,params = arrayOf(configuration.vaultURL))
                assertEquals((exception as SkyflowError).message,skyflowError.getErrorMessage())
            }

        })
    }

    @Test
    fun testEmptyTableName()
    {
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table", "")
        val fields = JSONObject()
        fields.put("fullname", "san")
        fields.put("card_number", "41111111111")
        fields.put("expiry_date","11/22")
        record.put("fields", fields)
        recordsArray.put(record)
        records.put("records", recordsArray)
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
            }

            override fun onFailure(exception: Any) {
                assertEquals((exception as SkyflowError).message.toString(),SkyflowErrorCode.EMPTY_TABLE_NAME.getMessage())
            }

        })
    }

    @Test
    fun testEmptyColumnName()
    {
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table", "cards")
        val fields = JSONObject()
        fields.put("", "xyz")
        fields.put("cardNumber", "41111111111")
        fields.put("expiry_date","11/22")
        record.put("fields", fields)
        recordsArray.put(record)
        records.put("records", recordsArray)
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
            }

            override fun onFailure(exception: Any) {
                assertEquals((exception as SkyflowError).message.toString(),SkyflowErrorCode.EMPTY_COLUMN_NAME.getMessage())
            }

        })
    }

    @Test
    fun testMissingTable()
    {
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        val fields = JSONObject()
       // fields.put("table", "cards")
        fields.put("cardNumber", "41111111111")
        fields.put("expiry_date","11/22")
        record.put("fields", fields)
        recordsArray.put(record)
        records.put("records", recordsArray)
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
            }

            override fun onFailure(exception: Any) {
                assertEquals((exception as SkyflowError).message.toString(),SkyflowErrorCode.MISSING_TABLE.getMessage())
            }

        })
    }

    @Test
    fun testMissingFields()
    {
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table", "cards")
        val fields = JSONObject()
        fields.put("", "san")
        fields.put("cardNumber", "41111111111")
        fields.put("expiry_date","11/22")
        //record.put("fields", fields)
        recordsArray.put(record)
        records.put("records", recordsArray)
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
            }

            override fun onFailure(exception: Any) {
                assertEquals((exception as SkyflowError).message,SkyflowErrorCode.FIELDS_KEY_ERROR.getMessage())
            }

        })
    }

    @Test
    fun testMissingRecords()
    {
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table", "cards")
        val fields = JSONObject()
        fields.put("cardNumber", "41111111111")
        fields.put("expiry_date","11/22")
        record.put("fields", fields)
        recordsArray.put(record)
        //records.put("records", recordsArray)
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
            }

            override fun onFailure(exception: Any) {
                assertEquals((exception as SkyflowError).message.toString(),SkyflowErrorCode.RECORDS_KEY_NOT_FOUND.getMessage())
            }

        })
    }

    @Test
    fun testInvalidTableType()
    {
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table", JSONObject())
        val fields = JSONObject()
        fields.put("cardNumber", "41111111111")
        fields.put("expiry_date","11/22")
        record.put("fields", fields)
        recordsArray.put(record)
        records.put("records", recordsArray)
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
            }

            override fun onFailure(exception: Any) {
                assertEquals((exception as SkyflowError).message,SkyflowErrorCode.INVALID_TABLE_NAME.getMessage())
            }

        })
    }

    @Test
    fun testInvalidRecordsType()
    {
        val records = JSONObject()
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table", "cards")
        val fields = JSONObject()
        fields.put("cardNumber", "41111111111")
        fields.put("expiry_date","11/22")
        record.put("fields", fields)
        recordsArray.put(record)
        records.put("records", JSONObject())
        skyflow.insert(records, InsertOptions(),object : Callback
        {
            override fun onSuccess(responseBody: Any) {
            }

            override fun onFailure(exception: Any) {
                assertEquals((exception as SkyflowError).message,SkyflowErrorCode.INVALID_RECORDS.getMessage())
            }

        })
    }
}