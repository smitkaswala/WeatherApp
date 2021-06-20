package com.bbot.darkweatherforecast.FifteenDays;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbot.darkweatherforecast.R;
import com.facebook.ads.AdError;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FifteenDaysAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context_1;
    private NativeAdsManager nativeAdsManager;
    private List<Object> fifteenDaysModelList;
    private List<NativeAd> mAdItems = new ArrayList<>();
    private static final int ITEM = 0;
    private static final int AD_TYPE = 1;

    private int FIRST_ADS_ITEM_POSITION = 4;
    private int ADS_FREQUENCY = 5;

    public FifteenDaysAdapter(Context context_2, List<Object> fifteenDaysModelList, int listSize) {
        context_1 = context_2;
        this.fifteenDaysModelList = fifteenDaysModelList;
        int x = listSize / (ADS_FREQUENCY - 1);
        nativeAdsManager = new NativeAdsManager(context_1, "IMG_16_9_APP_INSTALL#2822925034589011_2830338053847709", x);

    }
    public void initNativeAds() {
        nativeAdsManager.setListener(new NativeAdsManager.Listener() {
            @Override
            public void onAdsLoaded() {

                Log.i(TAG, "onAdsLoaded!" + nativeAdsManager.getUniqueNativeAdCount());

                int count = nativeAdsManager.getUniqueNativeAdCount();
                for (int i = 0; i < count; i++) {
                    NativeAd ad = nativeAdsManager.nextNativeAd();
                    addNativeAds(i, ad);
                    Log.d("viewtyp", "bharat " + count);

                }

            }

            @Override
            public void onAdError(AdError adError) {
                Toast.makeText(context_1, "Ads Load Failed : Error Code - " + adError.getErrorCode() + "-" + adError.getErrorMessage(), Toast.LENGTH_SHORT).show();

            }

        });

        nativeAdsManager.loadAds();

    }

    public void addNativeAds(int i, NativeAd ad) {

        if (ad == null) {
            return;
        }
        if (this.mAdItems.size() > i && this.mAdItems.get(i) != null) {
            this.mAdItems.get(i).unregisterView();
            this.fifteenDaysModelList.remove(FIRST_ADS_ITEM_POSITION + (i * ADS_FREQUENCY));
            this.mAdItems = null;
            notifyDataSetChanged();

        }

        this.mAdItems.add(i, ad);

        if (fifteenDaysModelList.size() > FIRST_ADS_ITEM_POSITION + (i * ADS_FREQUENCY)) {
            fifteenDaysModelList.add(FIRST_ADS_ITEM_POSITION + (i * ADS_FREQUENCY), ad);
            notifyItemInserted(FIRST_ADS_ITEM_POSITION + (i * ADS_FREQUENCY));

        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.e("viewtyp", "onCreateViewHolder: " + viewType);
        if (viewType == AD_TYPE) {

            return new AdsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_native, parent, false));
        } else {
            return new FifteenDaysViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FifteenDaysViewHolder) {

            FifteenDaysModel fifteenDaysModel = (FifteenDaysModel) fifteenDaysModelList.get(position);
            FifteenDaysViewHolder fifteenDaysViewHolder = (FifteenDaysViewHolder) holder;
            String i = String.valueOf(fifteenDaysModel.getIcon1());

            int iconID_1 = context_1.getResources().getIdentifier("a" + i, "drawable", context_1.getPackageName());
            int iconID_3 = context_1.getResources().getIdentifier("a" + i, "drawable", context_1.getPackageName());

            String date = String.valueOf(fifteenDaysModel.getTime1());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date iconID_2 = null;
            try {
                iconID_2 = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateFormat = new SimpleDateFormat("E dd, yyyy ");

            fifteenDaysViewHolder.time_1.setText(dateFormat.format(iconID_2));
            fifteenDaysViewHolder.icon_1.setImageResource(iconID_1);
            fifteenDaysViewHolder.value_1.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
            fifteenDaysViewHolder.tem1.setText(fifteenDaysModel.getTemp1() + "⁰");
            fifteenDaysViewHolder.tem2.setText(String.valueOf(fifteenDaysModel.getTemp2()));
            fifteenDaysViewHolder.win1.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
            fifteenDaysViewHolder.win2.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");


        }
        else {


            NativeAd nativeAd = (NativeAd) this.fifteenDaysModelList.get(position);
            AdsViewHolder adsViewHolder = (AdsViewHolder) holder;

            // Set the Text.
            adsViewHolder.nativeAdTitle.setText(nativeAd.getAdvertiserName());
            adsViewHolder.nativeAdBody.setText(nativeAd.getAdBodyText());
            adsViewHolder.nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
            adsViewHolder.nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            adsViewHolder.nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
            adsViewHolder.sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(adsViewHolder.nativeAdTitle);
            clickableViews.add(adsViewHolder.nativeAdCallToAction);

            // Register the Title and CTA button to listen for clicks.
            nativeAd.registerViewForInteraction(
                    adsViewHolder.itemView, adsViewHolder.nativeAdMedia, adsViewHolder.nativeAdIcon, clickableViews);

        }

    }

    @Override
    public int getItemCount() {

        if(fifteenDaysModelList == null)
            return 0;
        return fifteenDaysModelList.size();
    }

    @Override
    public int getItemViewType(final int position) {
        if (fifteenDaysModelList.get(position) instanceof NativeAd) {
            return AD_TYPE;
        }else
            return ITEM;
    }

    public static class FifteenDaysViewHolder extends RecyclerView.ViewHolder {

        TextView time_1;
        TextView value_1;
        ImageView icon_1;
        TextView tem1;
        TextView tem2;
        TextView win1;
        TextView win2;


        public FifteenDaysViewHolder(@NonNull View itemView) {
            super(itemView);

            time_1 = itemView.findViewById(R.id.tax_4);
            value_1 = itemView.findViewById(R.id.val_4);
            icon_1 = itemView.findViewById(R.id.icn_4);
            tem1 = itemView.findViewById(R.id.temp_1);
            tem2 = itemView.findViewById(R.id.temp_2);
            win1 = itemView.findViewById(R.id.wind_1);
            win2 = itemView.findViewById(R.id.wind_2);

        }

    }

    public class AdsViewHolder extends RecyclerView.ViewHolder {


        public MediaView nativeAdIcon;
        public TextView nativeAdTitle;
        public MediaView nativeAdMedia;
        public TextView nativeAdSocialContext;
        public TextView nativeAdBody;
        public TextView sponsoredLabel;
        public Button nativeAdCallToAction;


        public AdsViewHolder(@NonNull View itemView) {
            super(itemView);

            nativeAdIcon = itemView.findViewById(R.id.native_ad_icon1);
            nativeAdTitle = itemView.findViewById(R.id.native_ad_title1);
            nativeAdMedia = itemView.findViewById(R.id.native_ad_media1);
            nativeAdSocialContext = itemView.findViewById(R.id.native_ad_social_context1);
            nativeAdBody = itemView.findViewById(R.id.native_ad_body1);
            sponsoredLabel = itemView.findViewById(R.id.native_ad_sponsored_label1);
            nativeAdCallToAction = itemView.findViewById(R.id.native_ad_call_to_action1);

        }

    }

}



