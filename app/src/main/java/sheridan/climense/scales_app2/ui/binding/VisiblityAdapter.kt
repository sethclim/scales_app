package sheridan.climense.scales_app2.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter




/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */
@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.setVisibility(if (value) View.VISIBLE else View.GONE)
}