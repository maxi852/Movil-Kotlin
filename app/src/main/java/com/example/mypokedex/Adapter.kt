package com.example.mypokedex
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(val context: Context) : ListAdapter<Pokemon, Adapter.ViewHolder>(DiffCallBack) {

    lateinit var onItemClickListener: (Pokemon) -> Unit
    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nombreElem: TextView = view.findViewById(R.id.item_name)
        val imageElem: ImageView = view.findViewById(R.id.item_img)
        val strElem: TextView = view.findViewById(R.id.item_str)
        val hpElem: TextView = view.findViewById(R.id.item_hp)
        val defElem: TextView = view.findViewById(R.id.item_def)
        val typeElem: ImageView = view.findViewById(R.id.item_type)


        fun bind (poke: Pokemon) {
            nombreElem.text=poke.name
            strElem.text=poke.ataque.toString()
            defElem.text=poke.defensa.toString()
            hpElem.text=poke.vida.toString()

            Glide.with(context).load(poke.url).into(imageElem)

            val image = when(poke.tipo){
                PokemonType.AGUA -> R.drawable.agua
                PokemonType.ELECTRICO-> R.drawable.electrico
                PokemonType.FUEGO -> R.drawable.fuego
                PokemonType.PLANTA -> R.drawable.planta
                else -> R.drawable.fighter
            }
            typeElem.setImageResource(image)

            view.setOnClickListener() {
                onItemClickListener(poke)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }
}