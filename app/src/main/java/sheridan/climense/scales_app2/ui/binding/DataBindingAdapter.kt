package sheridan.climense.scales_app2.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.scales_app2.R

/**
  scales_app2
  createdbyseth*
  studentID:991599894
  on2022-03-02
 **/

@BindingAdapter("practice")
fun PracticeAdaptor(textView: TextView, practice: Practice?) {
    if (practice != null) {
        textView.text = String.format(
            textView.resources.getString(R.string.practice_present),
            practice.root,
            practice.scale,
            practice.tech
        )
    }
}