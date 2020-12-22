package com.CodeNaroNa.vendor.relief.Fragments.HomeFragment.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.CodeNaroNa.vendor.relief.databinding.UserFrameBinding
import com.CodeNaroNa.vendor.relief.Models.UserData

class UserAdapter(private val listener: UserAdapterEventListener) :RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{
    interface UserAdapterEventListener
    {
        fun onUserViewHolderClicked(userData: UserData)
    }

    private val diffUtil = object :DiffUtil.ItemCallback<UserData>()
    {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.phoneNumber == newItem.phoneNumber
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,diffUtil)

    inner class UserViewHolder(private val binding: UserFrameBinding) :RecyclerView.ViewHolder(binding.root)
    {
        fun bind(userData: UserData)
        {
            itemView.setOnClickListener {
                listener.onUserViewHolderClicked(userData)
            }
            binding.sname.text = userData.shopName
            binding.scat.text = userData.shopCategory
            binding.ctt.text = userData.closeTime
            binding.ott.text = userData.openTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserFrameBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}