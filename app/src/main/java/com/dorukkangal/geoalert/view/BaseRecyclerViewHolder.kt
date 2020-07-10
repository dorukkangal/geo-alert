package com.dorukkangal.geoalert.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.dorukkangal.geoalert.util.inflate
import kotlinx.android.extensions.LayoutContainer

/**
 * A base ViewHolder that handles click and long click events.
 */
abstract class BaseRecyclerViewHolder<T> : RecyclerView.ViewHolder,
    View.OnClickListener, View.OnLongClickListener, LayoutContainer {

    override val containerView: View?
        get() = itemView

    val context: Context
        get() = itemView.context

    protected var onItemClick: ((Int) -> Unit)? = null

    protected var onItemLongClick: ((Int) -> Unit)? = null

    /**
     * Constructor.
     *
     * @param parentView parent
     * @param layoutId   layout resource to inflate
     */
    constructor(parentView: ViewGroup, @LayoutRes layoutId: Int)
            : this(parentView.inflate(layoutId))

    /**
     * Constructor.
     *
     * @param itemView item view
     */
    constructor(itemView: View) : super(itemView)

    /**
     * Calls to update the contents with the item.
     *
     *
     * Override to set up some private fields to be used by RecyclerView.
     */
    abstract fun bindItem(item: T)

    /**
     * Register a callback to be invoked when this item is clicked.
     *
     * @param onItemClick callback
     */
    fun setItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick = onItemClick
        this.itemView.setOnClickListener(this)
    }

    /**
     * Register a callback to be invoked when this item is clicked and held.
     *
     * @param onItemLongClick callback
     */
    fun setItemLongClickListener(onItemLongClick: (Int) -> Unit) {
        this.onItemLongClick = onItemLongClick
        this.itemView.setOnLongClickListener(this)
    }

    override fun onClick(itemView: View) {
        onItemClick?.invoke(adapterPosition)
    }

    override fun onLongClick(itemView: View): Boolean {
        onItemLongClick?.invoke(adapterPosition)
        return true
    }
}

@Suppress("UNCHECKED_CAST")
fun BaseRecyclerViewHolder<*>.any(): BaseRecyclerViewHolder<Any> =
    this as BaseRecyclerViewHolder<Any>
