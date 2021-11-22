package com.Skyflow

import Skyflow.*
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_reveal.*
import org.json.JSONArray
import org.json.JSONObject

class RevealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reveal)
        val card_number = intent.getStringExtra("cardNumber")
        val expiry_date = intent.getStringExtra("expiryDate")
        val name = intent.getStringExtra("name")
        val cvv_token = intent.getStringExtra("cvv")

        //tview.text = card_number+"\n"+expiry_date+"\n"+name
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(10, 10, 10, 10)

        val padding = Skyflow.Padding(10, 10, 10, 10)
        val bstyle = Skyflow.Style(Color.parseColor("#403E6B"), 10f, padding, 6, R.font.roboto_light, Gravity.START, Color.parseColor("#403E6B"))
        val istyle = Skyflow.Style(Color.RED, 15f, padding, 6, R.font.roboto_light, Gravity.START, Color.RED)
        val styles = Skyflow.Styles(bstyle,invalid = istyle)
        val labelStyles = Styles(bstyle)
        val base_error_style = Skyflow.Style(Color.RED, 10f, padding, 6, R.font.roboto_light, Gravity.START, Color.RED)
        val error_styles = Styles(base_error_style)
        val cardNumberInput = Skyflow.RevealElementInput(
           card_number.toString(),
            inputStyles = styles, labelStyles = labelStyles,errorTextStyles = error_styles,
            label = "card number",altText =  "41111"
        )

        val expiryDateInput = Skyflow.RevealElementInput(
            expiry_date.toString(),
            label =  "expire date",altText = "mm/yyyy"
        )

        val fullNameInput = Skyflow.RevealElementInput(
            name.toString(),
            inputStyles = styles, labelStyles = labelStyles,errorTextStyles = error_styles,
            label = "name",altText =  "name"
        )


        val cvvElement = Skyflow.RevealElementInput(
            cvv_token.toString(),
            inputStyles = styles, labelStyles = labelStyles,errorTextStyles = error_styles,
            label = "CVV",altText =  "***"
        )

        val tokenProvider = CollectActivity.DemoTokenProvider()
        val skyflowConfiguration = Skyflow.Configuration(
            "VAULT_ID",
            "VAULT_URL",
            tokenProvider
        )

        val skyflowClient = Skyflow.init(skyflowConfiguration)
        val revealContainer = skyflowClient.container(Skyflow.ContainerType.REVEAL)

        val cardnumber = revealContainer.create(this, cardNumberInput)
        val expiry = revealContainer.create(this, expiryDateInput)
        val fullname = revealContainer.create(this, fullNameInput)
        val cvv = revealContainer.create(this, cvvElement)

        cardnumber.layoutParams = lp
        expiry.layoutParams = lp
        fullname.layoutParams = lp
        cvv.layoutParams = lp

        linear_parent.addView(fullname)
        linear_parent.addView(cardnumber)
        linear_parent.addView(expiry)
        linear_parent.addView(cvv)


        reveal.setOnClickListener {
            getByIds()
            detokenize()
            val dialog = AlertDialog.Builder(this).create()
            dialog.setMessage("please wait..")
            dialog.show()
            revealContainer.reveal(object: Skyflow.Callback {
                override fun onSuccess(responseBody: Any) {
                    dialog.dismiss()
                    Log.d(TAG, "reveal success: ${responseBody}")
                }

                override fun onFailure(exception: Any) {
                    dialog.dismiss()
                    Log.d(TAG, "reveal failure: ${exception.toString()}")
                }})
        }
    }

    fun getByIds()
    {

        val skyflowConfiguration = Skyflow.Configuration(
            "VAULT_ID",
            "VAULT_URL",
            CollectActivity.DemoTokenProvider()
        )
        val skyflowClient = Skyflow.init(skyflowConfiguration)
        val recordsArray = JSONArray()
        val record = JSONObject()
        record.put("table","persons")
        record.put("redaction",RedactionType.PLAIN_TEXT)

        val skyflowIds = ArrayList<String>()
        skyflowIds.add("003ec10191faf50")
        skyflowIds.add("054c9b6668882")
        record.put("ids",skyflowIds)
        recordsArray.put(record)
        val records = JSONObject()
        records.put("records",recordsArray)
        skyflowClient.getById(records,object : Callback
        {
            override fun onSuccess(responseBody: Any) {
                Log.d("getbyskyflow_ids",responseBody.toString())
            }

            override fun onFailure(exception: Any) {
                Log.d("getbyskyflow_ids",exception.toString())

            }

        })
    }

    //pure reveal
    fun detokenize(){
        val skyflowConfiguration = Skyflow.Configuration(
            "VAULT_ID",
            "VAULT_URL",
            CollectActivity.DemoTokenProvider()
        )
        val revealRecords = JSONObject()
        val revealRecordsArray = JSONArray()
        val recordObj = JSONObject()
        recordObj.put("token", "32207876")
        val recordObj1 = JSONObject()
        recordObj1.put("token", "a1d928ff9d01d1c")
        revealRecordsArray.put(recordObj)
        revealRecordsArray.put(recordObj1)
        revealRecords.put("records", revealRecordsArray)
        val skyflowClient = Skyflow.init(skyflowConfiguration)
        skyflowClient.detokenize(records = revealRecords, object : Callback {
            override fun onSuccess(responseBody: Any) {
                Log.d("detokenize", "onSuccess: $responseBody")
            }

            override fun onFailure(exception: Any) {
                Log.d("detokenize", "onFailure: ${exception.toString()}")
            }

        })
    }

}