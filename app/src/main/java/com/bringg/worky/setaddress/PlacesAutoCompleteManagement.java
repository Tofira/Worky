package com.bringg.worky.setaddress;

import android.content.Context;
import android.util.Log;

import com.bringg.worky.R;
import com.bringg.worky.data.address.AddressCoord;
import com.bringg.worky.utils.SharedPrefUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

import timber.log.Timber;

/**
 * Created by Mickael on 08/03/2018.
 */

public class PlacesAutoCompleteManagement {

    public interface AutoCompleteCompletedListener {
        void onComplete();
    }

    private static final String TAG = PlacesAutoCompleteManagement.class.getSimpleName();

    private SupportPlaceAutocompleteFragment autocompleteFragment;

    private Context context;

    private AutoCompleteCompletedListener autoCompleteCompletedListener;

    public PlacesAutoCompleteManagement(Context context, SupportPlaceAutocompleteFragment autocompleteFragment, AutoCompleteCompletedListener autoCompleteCompletedListener)
    {
        this.context = context;
        this.autocompleteFragment = autocompleteFragment;
        this.autoCompleteCompletedListener = autoCompleteCompletedListener;
    }

    public void init()
    {
        autocompleteFragment.setHint(context.getString(R.string.work_address));

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();

        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                SharedPrefUtils.getInstance(context).setUserAddress(
                        new AddressCoord(place.getLatLng().latitude, place.getLatLng().longitude)
                );

                if(autoCompleteCompletedListener != null)
                    autoCompleteCompletedListener.onComplete();

            }

            @Override
            public void onError(Status status) {
                Timber.tag(TAG).v("onError - " + status.getStatusMessage());
            }

        });
    }

}
