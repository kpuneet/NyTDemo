package com.puneet.android.utils;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuzz.android.common.GlobalContext;
import com.puneet.android.R;
import com.squareup.picasso.Picasso;

/**
 * Description: keeps all of the custom databinding attribute definitions in one place.
 */
public class CustomDataBindings {

    @BindingAdapter({"hideToolbarTitle"})
    public static void hideToolbarTitle(TextView textView, boolean checked) {
        textView.setVisibility(checked ? View.GONE : View.VISIBLE);
    }

    @BindingAdapter("setToolbarTitle")
    public static void setToolbarTitle(TextView textView, String title) {
        textView.setText(title);
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        ((ViewGroup) imageView.getParent()).setVisibility(View.VISIBLE);
        Picasso.with(GlobalContext.getContext()).load(url).fit().centerInside().placeholder(R.drawable.Transparent).error(R.drawable.mid_grey_light).into(imageView);
    }
}
