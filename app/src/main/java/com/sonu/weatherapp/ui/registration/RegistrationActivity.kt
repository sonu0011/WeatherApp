package com.sonu.weatherapp.ui.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sonu.weatherapp.R
import com.sonu.weatherapp.model.DistrictAndState
import com.sonu.weatherapp.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {
    private val registrationViewModel: RegistrationViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        registrationViewModel.setStateEvent(RegistrationViewModel.MainStateEvent.GetDistrictAndStateEvent)
    }

    private fun subscribeObservers() {
        registrationViewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<DistrictAndState>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }


    private fun displayError(message: String?) {
        if (message != null) text.text = message else text.text = "Unknown error."
    }

    private fun appendBlogTitles(districtAndStates: List<DistrictAndState>) {
        val sb = StringBuilder()
        for (d in districtAndStates) {
            sb.append(" District " + d.district + " State " + d.state + "\n")
        }
        text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

}