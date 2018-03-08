package com.bringg.worky.setaddress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bringg.worky.main.MainActivity;
import com.bringg.worky.utils.AnimationsUtils;
import com.bringg.worky.utils.SharedPrefUtils;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.bringg.worky.R;

/**
 * Created by Mickael on 08/03/2018.
 */
public class SetAddressActivity extends AppCompatActivity {

    private static String TAG = SetAddressActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_address);

        if(checkAndMoveActivity())
            return;

        SupportPlaceAutocompleteFragment autocompleteFragment =
                (SupportPlaceAutocompleteFragment) getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        PlacesAutoCompleteManagement placesAutoCompleteManagement = new PlacesAutoCompleteManagement(
                this,
                autocompleteFragment,
                new PlacesAutoCompleteManagement.AutoCompleteCompletedListener() {
                    @Override
                    public void onComplete() {

                        SharedPrefUtils.getInstance(getBaseContext()).setFirstTimeInApp(false);

                        startMainActivity();
                    }
                });

        placesAutoCompleteManagement.init();

        AnimationsUtils.startSplashAnimation (
                findViewById(R.id.tv_logo_1),
                findViewById(R.id.tv_logo_2),
                findViewById(R.id.cv_address)
        );

    }

    private boolean checkAndMoveActivity()
    {
        if(!SharedPrefUtils.getInstance(this).isFirstTimeInApp())
        {
            startMainActivity();
            return true;
        }

        return false;
    }

    private void startMainActivity()
    {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);

        finish();

    }

}
