package lightcycleconsulting.com.squarecodesample.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import lightcycleconsulting.com.squarecodesample.R
import lightcycleconsulting.com.squarecodesample.databinding.EmployeeItemBinding
import lightcycleconsulting.com.squarecodesample.model.Employee

class EmployeeViewHolder(val binding: EmployeeItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(employee: Employee?, expandedList: MutableList<Boolean>, position: Int) {
        val avatarSize =
            binding.root.context.resources.getInteger(R.integer.default_avatar_size)

        binding.employee = employee
        binding.executePendingBindings()
        Picasso.get().load(employee?.photoUrlLarge)
            .into(binding.employeeImage)
        Picasso.get().load(employee?.photoUrlSmall)
            .resize(avatarSize, avatarSize)
            .transform(CropCircleTransformation())
            .into(binding.avatarImage)
        if (expandedList[position]) {
            binding.hideableContents.visibility = View.VISIBLE
            binding.moreInfoButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
        } else {
            binding.hideableContents.visibility = View.GONE
            binding.moreInfoButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
        }
    }
}