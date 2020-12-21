package com.CodeNaroNa.vendor.relief.adapterKotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.CodeNaroNa.vendor.relief.databinding.QuestionBinding
import com.CodeNaroNa.vendor.relief.modelKotlin.PrecautionData

class PrecAdapter :RecyclerView.Adapter<PrecAdapter.PrecViewHolder>()
{

    private val diffUtil  = object : DiffUtil.ItemCallback<PrecautionData>()
    {
        override fun areItemsTheSame(oldItem: PrecautionData, newItem: PrecautionData): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PrecautionData, newItem: PrecautionData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,diffUtil)

    inner class PrecViewHolder(private val binding:QuestionBinding) :RecyclerView.ViewHolder(binding.root)
    {

        fun bind(precautionData: PrecautionData)
        {
            binding.apply {
                ques.text = precautionData.question
                answer.text = precautionData.answer
            }

            itemView.setOnClickListener {
                if(binding.answer.visibility == View.GONE)
                    binding.answer.visibility = View.VISIBLE
                else
                    binding.answer.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrecViewHolder {
        return PrecViewHolder(QuestionBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PrecViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}