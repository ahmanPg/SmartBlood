package com.ahman.smartblood.ui.request

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.ahman.smartblood.R
import com.ahman.smartblood.helper.HttpJsonParser
import com.ahman.smartblood.helper.URLs
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class NeedBlood : AppCompatActivity() {
    private var map: HashMap<String, String>? = null
    private var groupChoice: Spinner = TODO()
    private var districtChoice:Spinner = TODO()
    private var centerSpinner: Spinner? = null
    private var pDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_need_blood)
        supportActionBar!!.hide()

        districtChoice = findViewById(R.id.districtSpinner)
        groupChoice = findViewById(R.id.needBlood)
        centerSpinner = findViewById(R.id.centers)
        val need = findViewById<Button>(R.id.startSearch)
        FetchCentersAsyncTask().execute()
        val group = arrayOf("O+", "O-", "A+", "B+", "A-", "B-", "AB+", "AB-")
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, group)
        groupChoice.setAdapter(adapter1)
        need.setOnClickListener {
            val group = groupChoice.getSelectedItem().toString()
            val district = districtChoice.getSelectedItem().toString()
            val intent = Intent(applicationContext, DonorList::class.java)
            intent.putExtra("group", group)
            intent.putExtra("district", district)
            startActivity(intent)
        }
    }

    /**
     * leaks here, fix it
     */
    inner class FetchCentersAsyncTask : AsyncTask<String?, String?, String?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            //Display progress bar
            pDialog = ProgressDialog(this@NeedBlood)
            pDialog!!.setMessage("Loading donors. Please wait...")
            pDialog!!.isIndeterminate = false
            pDialog!!.setCancelable(false)
            pDialog!!.show()
        }

        protected override fun doInBackground(vararg p0: String?): String? {
            val httpJsonParser = HttpJsonParser()
            try {
                val jsonObject = httpJsonParser.makeHttpRequest(
                        BASE_URL + "fetch_all_centers.php", "GET", null)
                val success = jsonObject.getInt(KEY_SUCCESS)
                val centers: JSONArray
                if (success == 1) {
                    val centerList = ArrayList<HashMap<String, String>>()
                    centers = jsonObject.getJSONArray(KEY_DATA)
                    //Iterate through the response and populate donors list
                    for (i in 0 until centers.length()) {
                        val center = centers.getJSONObject(i)
                        val centerName = center.getString(KEY_CENTER_NAME)
                        map = HashMap()
                        map!![KEY_CENTER_NAME] = centerName
                        centerList.add(map!!)
                        //                        centerList.update(map);
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: String?) {
            pDialog!!.dismiss()
            runOnUiThread {
                val values: Collection<String> = map!!.values
                val array: Array<String> = values.toTypedArray<String>()
                Log.i("Myarray", "[$array]")
                val adapter = ArrayAdapter<CharSequence>(applicationContext, android.R.layout.simple_spinner_dropdown_item, array)
                centerSpinner!!.adapter = adapter
            }
        }
    }

    companion object {
        private const val BASE_URL = URLs.URL_ALL
        private const val KEY_CENTER_NAME = "center_name"
        private const val KEY_CENTER_ADDRESS = "center_address"
        private const val KEY_SUCCESS = "success"
        private const val KEY_DATA = "data"
    }
}