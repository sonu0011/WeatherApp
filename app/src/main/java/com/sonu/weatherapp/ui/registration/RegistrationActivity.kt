package com.sonu.weatherapp.ui.registration

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.MaterialDatePicker
import com.sonu.weatherapp.R
import com.sonu.weatherapp.model.DistrictAndState
import com.sonu.weatherapp.ui.todayweather.TodayWeatherActivity
import com.sonu.weatherapp.utils.DataState
import com.sonu.weatherapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {
    private val registrationViewModel: RegistrationViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val items = listOf("Male", "Female")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (gender_menu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        gender_auto_complete_textView.setText(items[0], false)
        dob.editText!!.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

            datePicker.show(supportFragmentManager, "DateDialog")
            datePicker.addOnPositiveButtonClickListener {
                dob.editText!!.setText(datePicker.headerText)
            }


        }

        btn_reg.setOnClickListener {
            if (TextUtils.isEmpty(mobile_number.editText!!.text)){
               showToast("Please enter your mobile number")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(full_name.editText!!.text)){
                showToast("Please enter your full name")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(gender_menu.editText!!.text)){
                showToast("Please select your gender")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(dob.editText!!.text)){
                showToast("Please enter the data of birth")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(address_line1.editText!!.text)){
                showToast("Please enter your address")
                return@setOnClickListener
            }

            startActivity(Intent(this, TodayWeatherActivity::class.java))
        }

        subscribeObservers()
        btn_check_pin_code.setOnClickListener {
            if (TextUtils.isEmpty(pincode.editText!!.text)) {
                showToast("Please enter pin code")
            } else {
                registrationViewModel.setStateEvent(RegistrationViewModel.MainStateEvent.GetDistrictAndStateEvent , pincode.editText!!.text.toString())
            }
        }

    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun subscribeObservers() {
        registrationViewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<DistrictAndState>> -> {
                    displayProgressBar(false)
                    fetchStateAndDistrict(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    dataState.exception.message?.let { showToast(it) }
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun fetchStateAndDistrict(districtAndStates: List<DistrictAndState>) {
        val states = mutableListOf<String>()
        val districts = mutableListOf<String>()
        val stateAdapter = ArrayAdapter(this, R.layout.list_item, states)
        val districtAdapter = ArrayAdapter(this, R.layout.list_item, districts)

        for (d in districtAndStates) {
            states.add(d.state)
            districts.add(d.district)
        }
        (district_menu.editText as? AutoCompleteTextView)?.setAdapter(districtAdapter)
        (state_menu.editText as? AutoCompleteTextView)?.setAdapter(stateAdapter)
        state_auto_complete.setText(states[0], false)
        district_auto_complete.setText(districts[0], false)

    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

}