package io.github.droidkaigi.confsched2019.staff_dfm.ui.item

import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.squareup.picasso.Picasso
import com.xwray.groupie.databinding.BindableItem
import io.github.droidkaigi.confsched2019.model.Staff
import io.github.droidkaigi.confsched2019.staff_dfm.R
import io.github.droidkaigi.confsched2019.staff_dfm.databinding.ItemStaffBinding
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class StaffItem(
    val staff: Staff
) : BindableItem<ItemStaffBinding>(staff.id.hashCode().toLong()) {

    override fun getLayout(): Int = R.layout.item_staff

    override fun bind(itemBinding: ItemStaffBinding, position: Int) {
        itemBinding.staff = staff
        val context = itemBinding.staffImage.context
        val placeHolderColor = ContextCompat.getColor(
            context,
            io.github.droidkaigi.confsched2019.widget.component.R.color.gray2
        )
        val placeHolder = VectorDrawableCompat.create(
            context.resources,
            io.github.droidkaigi.confsched2019.widget.component.R.drawable.ic_person_outline_black_24dp,
            null
        )
        placeHolder?.setTint(placeHolderColor)
        staff.iconUrl?.let { iconUrl ->
            itemBinding.staffImage.clearColorFilter()
            Picasso.get()
                .load(iconUrl)
                .transform(CropCircleTransformation())
                .apply {
                    placeHolder?.let {
                        placeholder(it)
                    }
                }
                .into(itemBinding.staffImage)
        } ?: run {
            itemBinding.staffImage.setImageDrawable(placeHolder)
        }
    }
}
