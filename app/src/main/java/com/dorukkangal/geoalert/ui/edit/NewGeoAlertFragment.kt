package com.dorukkangal.geoalert.ui.edit

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.dorukkangal.geoalert.R
import com.dorukkangal.geoalert.domain.repository.geoalert.model.GeoAlert
import com.dorukkangal.geoalert.ui.base.BaseMvvmDialogFragment
import com.dorukkangal.geoalert.util.KeyboardUtil
import com.dorukkangal.geoalert.util.LocationHelper
import com.dorukkangal.geoalert.util.clearText
import com.dorukkangal.geoalert.util.text
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_geo_alert_new.*
import javax.inject.Inject

@AndroidEntryPoint
class NewGeoAlertFragment : BaseMvvmDialogFragment<NewGeoAlertViewModel>() {

    @Inject
    lateinit var locationHelper: LocationHelper

    override val viewModel: NewGeoAlertViewModel by viewModels()

    private val args: NewGeoAlertFragmentArgs by navArgs()

    override fun getLayoutId(): Int = R.layout.fragment_geo_alert_new

    override fun initViews() {
        imageViewCurrentLocation.setOnClickListener {
            locationHelper.subscribeToLocationChanges { location ->
                editTextLatitude.setText(location.latitude.toString())
                editTextLongitude.setText(location.longitude.toString())
            }
        }

        buttonSave.setOnClickListener {
            val id = textViewId.text()
            val name = editTextName.text()
            val latitude = editTextLatitude.text()
            val longitude = editTextLongitude.text()

            if (
                name.isBlank().not()
                && latitude.isBlank().not()
                && longitude.isBlank().not()
            ) {
                KeyboardUtil.hideKeyboard(editTextName)
                viewModel.createOrUpdateGeoAlert(
                    id,
                    name,
                    latitude.toDouble(),
                    longitude.toDouble()
                )
            }
        }

        buttonDelete.isEnabled = args.geoAlert != null
        buttonDelete.setOnClickListener {
            viewModel.deleteGeoAlert(textViewId.text())
        }

        args.geoAlert?.let {
            fillInputFields(it)
        }
    }

    override fun observeEvents() {
        viewModel.saved.observe(this, Observer {
            if (it) {
                clearInputFields()
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                viewModel.saved.value = false
                dismiss()
            }
        })

        viewModel.deleted.observe(this, Observer {
            if (it) {
                clearInputFields()
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                viewModel.deleted.value = false
                dismiss()
            }
        })
    }

    private fun fillInputFields(geoAlert: GeoAlert) {
        textViewId.text = geoAlert.id.toString()
        editTextName.setText(geoAlert.name)
        editTextLatitude.setText(geoAlert.location.latitude.toString())
        editTextLongitude.setText(geoAlert.location.longitude.toString())
    }

    private fun clearInputFields() {
        textViewId.clearText()
        editTextName.clearText()
        editTextLatitude.clearText()
        editTextLongitude.clearText()
    }
}
