package sheridan.climense.scales_app2.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.util.DateConverters
import java.util.*

@BindingAdapter("date")
fun DateBindingAdapter(textView: TextView, date: Date?) {
    if (date is Date) {
        val context = textView.context
        textView.text = DateConverters.formatDate(date)
    }
}