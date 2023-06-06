package com.example.appdevelopment

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CollapsePanelAdapter(private val panels: List<CollapsePanel>) :
    RecyclerView.Adapter<CollapsePanelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_collapse_panel, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val panel = panels[position]
        holder.bind(panel)
    }

    override fun getItemCount(): Int {
        return panels.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvPanelContent: TextView = itemView.findViewById(R.id.tv_panel_content)
        private val imgExpand: ImageView = itemView.findViewById(R.id.img_expand)

        fun bind(panel: CollapsePanel) {
            tvPanelContent.text = panel.content
            tvPanelContent.ellipsize = TextUtils.TruncateAt.END
            val Expandbtn = imgExpand
            Expandbtn.setOnClickListener{
                panel.isExpanded = !panel.isExpanded
                notifyItemChanged(adapterPosition)
                if (panel.isExpanded) {
                    tvPanelContent.maxLines = 10
                    imgExpand.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    tvPanelContent.maxLines = 1
                    imgExpand.setImageResource(R.drawable.ic_arrow_down)
                }
                // 用 post() 方法更新元素，确保在布局重新计算大小后再进行更新
                tvPanelContent.post { notifyItemChanged(adapterPosition) }
            }
            if (panel.isExpanded) {
                tvPanelContent.maxLines = 10
                Expandbtn.setImageResource(R.drawable.ic_arrow_up)
            } else {
                tvPanelContent.maxLines = 1
                Expandbtn.setImageResource(R.drawable.ic_arrow_down)
            }
//            itemView.setOnClickListener {
//                panel.isExpanded = !panel.isExpanded
//                notifyItemChanged(adapterPosition)
//            }
        }
    }
}
