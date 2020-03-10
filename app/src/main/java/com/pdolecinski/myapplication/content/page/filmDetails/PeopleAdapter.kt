package com.pdolecinski.myapplication.content.page.filmDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdolecinski.myapplication.R
import com.pdolecinski.myapplication.data.model.app.Person
import kotlinx.android.synthetic.main.item_person.view.*

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private var items: List<Person>? = null

    fun setData(items: List<Person>?) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.itemView.apply {
            name.text = item?.name
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}