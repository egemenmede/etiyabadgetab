/**
 * 2017 Etiya Badge Tab for Android (http://www.etiya.com)
 *
 * @author – Egemen Mede (DeliPenguen - http://www.delipenguen.com)
 * @version – 1.0
 * @since - 03.03.2017
 */

package com.etiya.etiyabadgetab;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * The type Etiya badge tab.
 */
public class EtiyaBadgeTab extends TabLayout {

    private final SparseArray<EtiyaBadgeTabBuilder> mTabBuilders = new SparseArray<>();

    /**
     * Instantiates a new Etiya badge tab.
     *
     * @param context the context
     */
    public EtiyaBadgeTab(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Etiya badge tab.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public EtiyaBadgeTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Etiya badge tab.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public EtiyaBadgeTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Etiya badge tab builder.
     *
     * @param position Tab'in konumunu integer olarak belirtir.
     * @return Uzerinde islem yapilacak tab'i dondurur.
     */
    public EtiyaBadgeTabBuilder selectEtiyaBadgeTab(int position) {
        Tab tab = getTabAt(position);

        if (tab == null) throw new IllegalArgumentException("Tab null olmamali.");

        EtiyaBadgeTabBuilder builder = mTabBuilders.get(tab.getPosition());
        if (builder == null) {
            builder = new EtiyaBadgeTabBuilder(this, tab);
            mTabBuilders.put(tab.getPosition(), builder);
        }

        return builder;
    }

    /**
     * The type EtiyaBadgeTabBuilder.
     */
    public static final class EtiyaBadgeTabBuilder {
        @Nullable
        private final View mView;
        private final Context mContext;
        private final TabLayout.Tab mTab;
        private TextView mTabBadgeLayout;
        private Integer mTabTitleColorFilter;
        private Integer mTabIconColor;
        private Integer mTabBadgeBgColor;
        private Integer mTabBadgeTextColor;
        private Integer mTabBadgeCornerRadius;
        private Integer mTabBadgeStrokeWidth;
        private Integer mTabBadgeStrokeColor;
        private Integer mTabIconToTitle = 1;
        private GradientDrawable badgeRedraw;
        private TextView mTabTitle;
        private String mTabTitleValue = "";
        private int mBadgeCount = Integer.MIN_VALUE;
        private String mTabIconDirectionValue = "LEFT";
        private Drawable mTabIcon;
        private boolean mTabBadgeCountMore = false;
        private boolean mIsBadge = false;

        private EtiyaBadgeTabBuilder (TabLayout parent, @NonNull TabLayout.Tab tab) {
            super();
            this.mContext = parent.getContext();
            this.mTab = tab;

            if (tab.getCustomView() != null) {
                this.mView = tab.getCustomView();
            } else {
                this.mView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.etiya_badge_tab_layout, parent, false);
            }

            if (mView != null) {
                this.mTabBadgeLayout = (TextView) mView.findViewById(R.id.tabBadgeLayout);
                this.mTabTitle = (TextView) mView.findViewById(R.id.tabTitleLayout);
            }

            if (this.mTabBadgeLayout != null) {
                if (mIsBadge) {
                    this.mIsBadge = mTabBadgeLayout.getVisibility() == View.VISIBLE;
                }
            }
        }
    
        /**
         * Tab badge EtiyaBadgeTabBuilder.
         *
         * @param isBadge Tab icerisinde Badge olup olmamasini belirten degerdir. true/false degerlerini alabilir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabBadge(boolean isBadge) {
            mIsBadge = isBadge;
            return this;
        }

        /**
         * Tab title color EtiyaBadgeTabBuilder.
         *
         * @param color Tab metninin rengini belirten integer degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabTitleColor(Integer color) {
            mTabTitleColorFilter = color;
            return this;
        }

        /**
         * Tab badge count EtiyaBadgeTabBuilder.
         *
         * @param count Badge icerisindeki rakami belirten integer degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabBadgeCount(int count) {
            mBadgeCount = count;
            return this;
        }

        /**
         * Tab badge count more EtiyaBadgeTabBuilder.
         *
         * @param isTabBadgeCountMore Badge'in gosterim seklini belirtir. true/false degeri alir. Degeri true ise 9'dan sonrasi "9+" olarak gosterilir. Aksi durumda sayinin tamami gosterilir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabBadgeCountMore(boolean isTabBadgeCountMore) {
            mTabBadgeCountMore = isTabBadgeCountMore;
            return this;
        }

        /**
         * Tab title EtiyaBadgeTabBuilder.
         *
         * @param tabTitleText Tab metnini ifade eder.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabTitle(String tabTitleText) {
            mTabTitleValue = tabTitleText;
            return this;
        }

        /**
         * Tab icon EtiyaBadgeTabBuilder.
         *
         * @param drawableRes Tab'ta kullanilacak ikonun integer degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabIcon(int drawableRes) {
            mTabIcon = getDrawableCompat(mContext, drawableRes);
            return this;
        }

        /**
         * Tab icon EtiyaBadgeTabBuilder.
         *
         * @param drawableRes Tab'ta kullanilacak ikonun Drawable degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabIcon(Drawable drawableRes) {
            mTabIcon = drawableRes;
            return this;
        }

        /**
         * Tab icon color EtiyaBadgeTabBuilder.
         *
         * @param color Tab ikon rengini belirten integer degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabIconColor(Integer color) {
            mTabIconColor = color;
            return this;
        }

        /**
         * Tab icon direction EtiyaBadgeTabBuilder.
         *
         * @param tabIconDirectionText Tab icerisinde gosterilecek ikonun konumunu belirler. "LEFT" ve "RIGHT" olmak uzere 2 metin degeri alir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabIconDirection(String tabIconDirectionText) {
            mTabIconDirectionValue = tabIconDirectionText;
            return this;
        }

        /**
         * Tab icon to title EtiyaBadgeTabBuilder.
         *
         * @param value Tab icerisindeki ikon ve metin arasindaki boslugu belirleyen Integer degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabIconToTitle(Integer value) {
            mTabIconToTitle = value;
            return this;
        }

        /**
         * Tab badge bg color EtiyaBadgeTabBuilder.
         *
         * @param color Badge'in arkaplan renginin integer degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabBadgeBgColor(Integer color) {
            mTabBadgeBgColor = color;
            return this;
        }

        /**
         * Tab badge text color EtiyaBadgeTabBuilder.
         *
         * @param color Badge'in metin renginin integer degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabBadgeTextColor(Integer color) {
            mTabBadgeTextColor = color;
            return this;
        }

        /**
         * Tab badge corner radius EtiyaBadgeTabBuilder.
         *
         * @param value Badge'in kose yaricap degerinin integer degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabBadgeCornerRadius(Integer value) {
            mTabBadgeCornerRadius = value;
            return this;
        }

        /**
         * Tab badge stroke EtiyaBadgeTabBuilder.
         *
         * @param strokeWidth Badge'in kenar cizgi kalinligini gosteren integer degeridir.
         * @param strokeColor Badge'in kenar cizgi rengini gosteren integer degeridir.
         * @return Deger set edilmis nesneyi dondurur.
         */
        EtiyaBadgeTabBuilder tabBadgeStroke(Integer strokeWidth, Integer strokeColor) {
            mTabBadgeStrokeWidth = strokeWidth;
            mTabBadgeStrokeColor = strokeColor;
            return this;
        }

        /**
         * Create etiya badge tab.
         */
        void createEtiyaBadgeTab() {
            if (mView == null) {
                return;
            }

            if (mTabIcon != null && mTabIconColor != null){
                mTabIcon.setColorFilter(mContext.getResources().getColor(mTabIconColor), PorterDuff.Mode.SRC_IN);
            }

            if (mTabBadgeLayout != null) {
                badgeRedraw = (GradientDrawable) mTabBadgeLayout.getBackground().mutate();
                badgeRedraw.setShape(GradientDrawable.RECTANGLE);

                if (mTabBadgeCornerRadius != null) {
                    badgeRedraw.setCornerRadius(mTabBadgeCornerRadius);
                }

                if (mTabBadgeStrokeWidth != null && mTabBadgeStrokeColor != null) {
                    badgeRedraw.setStroke(mTabBadgeStrokeWidth, mContext.getResources().getColor(mTabBadgeStrokeColor));
                }

                if (mTabBadgeBgColor != null) {
                    badgeRedraw.setColor(mContext.getResources().getColor(mTabBadgeBgColor));
                }

                if (mTabBadgeTextColor != null) {
                    mTabBadgeLayout.setTextColor(mContext.getResources().getColor(mTabBadgeTextColor));
                }

                if (mIsBadge) {
                    mTabBadgeLayout.setVisibility(View.VISIBLE);
                    mTabBadgeLayout.setText(formattedBadgeView(mBadgeCount, mTabBadgeCountMore));
                } else {
                    mTabBadgeLayout.setVisibility(View.GONE);
                }
            }

            if (mTabTitle != null) {
                mTabTitle.setText(displayTabTitle(mTabTitleValue, mTabIconToTitle, mTabIconDirectionValue));

                if (mTabTitleColorFilter != null) {
                    mTabTitle.setTextColor(mContext.getResources().getColor(mTabTitleColorFilter));
                }

                if (mTabIconDirectionValue.equals("LEFT")) {
                    mTabTitle.setCompoundDrawablesWithIntrinsicBounds(mTabIcon, null, null, null);
                }
                if (mTabIconDirectionValue.equals("RIGHT")) {
                    mTabTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, mTabIcon, null);
                }
            }

            mTab.setCustomView(mView);
        }
    }

    /**
     *
     * @param tabTitleValue Tab'ta gosterilecek metin degeridir.
     * @param tabIconToTitle Tab icerisindeki ikon ve metin arasindaki boslugu belirleyen Integer
     *                       degeridir.
     * @param tabIconDirectionValue Tab icerisinde gosterilecek ikonun konumunu belirler. "LEFT"
     *                              ve "RIGHT" olmak uzere 2 metin degeri alir.
     * @return Geriye Tab metnini onunde ya da arkasindaki bosluklarla birlikte dondurur.
     */
    private static String displayTabTitle(String tabTitleValue, Integer tabIconToTitle, String tabIconDirectionValue) {
        StringBuilder tabTitleBuilder = new StringBuilder();

        if (tabIconDirectionValue.equals("RIGHT")){
            tabTitleBuilder.append(tabTitleValue);
        }

        for (int i = 0; i < tabIconToTitle; i++) {
            tabTitleBuilder.append(" ");
        }

        if (tabIconDirectionValue.equals("LEFT")){
            tabTitleBuilder.append(tabTitleValue);
        }

        return tabTitleBuilder.toString();
    }

    /**
     *
     * @param context Islemin yapilacagi context'i belirtir.
     * @param drawableRes Drawable nesnesine donusturulecek olan kaynagin integer olarak gosterilen
     *                    halidir.
     * @return Geriye bir Drawable nesnesi dondurur.
     */
    private static Drawable getDrawableCompat(Context context, int drawableRes) {
        Drawable drawable = null;
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                drawable = context.getResources().getDrawable(drawableRes);
            } else {
                drawable = context.getResources().getDrawable(drawableRes, context.getTheme());
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return drawable;
    }

    /**
     *
     * @param value Bicimlendirilecek Badge sayi degerini integer olarak belirtir.
     * @param mTabBadgeCountMore Badge'in gosterim seklini belirtir. true/false degeri alir.
     *                           Degeri true ise 9'dan sonrasi "9+" olarak gosterilir.
     *                           Aksi durumda sayinin tamami gosterilir.
     * @return Geriye "18" ya da "9+" gibi bir metin degeri dondurur.
     */
    private static String formattedBadgeView(int value, boolean mTabBadgeCountMore) {
        if (0 != value && 0 < value && value < 10) {
            return Integer.toString(value);
        }else{
            if (mTabBadgeCountMore) {
                return "9+";
            }else {
                return Integer.toString(value);
            }
        }
    }

}