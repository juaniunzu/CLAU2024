package com.juanunzu.clau2024.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.juanunzu.clau2024.databinding.CustomActionItemBinding

class ItemCardView(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(
    context, attributeSet
) {
    private val binding by lazy {
        CustomActionItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        setupA11yFocus()
        setupA11yActions()
    }

    var image: Drawable?
        get() = binding.itemIv.drawable
        set(value) {
            binding.itemIv.setImageDrawable(value)
        }

    var title: CharSequence?
        get() = binding.itemTvTitle.text
        set(value) {
            binding.itemTvTitle.text = value
            setupContentDescription()
        }

    var subtitle: CharSequence?
        get() = binding.itemTvSubtitle.text
        set(value) {
            binding.itemTvSubtitle.text = value
            setupContentDescription()
        }

    var onFavoriteClick: OnClickListener? = null
        set(value) {
            field = value
            binding.itemIvFavorite.setOnClickListener(value)
        }

    var onCartClick: OnClickListener? = null
        set(value) {
            field = value
            binding.itemIvCart.setOnClickListener(value)
        }

    var onInfoClick: OnClickListener? = null
        set(value) {
            field = value
            binding.itemIvInfo.setOnClickListener(value)
        }

    private fun setupA11yFocus() {
        // necesito ignorar el container de vistas para que no sea alcanzable por servicios de a11y
        binding.itemContainer.importantForAccessibility =
            IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS

        // por el contrario, necesito que el root sea alcanzable por servicios de a11y ya que es
        // quien tendrá el texto alternativo y las custom actions.
        binding.root.isFocusable = true
    }

    private fun setupA11yActions() {
        // las custom actions se setean directo sobre el binding.root
        // (la card que contiene todas las vistas)
        ViewCompat.addAccessibilityAction(binding.root, "agregar a favoritos") { _, _ ->
            onFavoriteClick?.onClick(binding.itemIvFavorite)
            true
        }
        ViewCompat.addAccessibilityAction(binding.root, "agregar al carrito") { _, _ ->
            onCartClick?.onClick(binding.itemIvCart)
            true
        }
        ViewCompat.addAccessibilityAction(binding.root, "mas informacion") { _, _ ->
            onInfoClick?.onClick(binding.itemIvInfo)
            true
        }
    }

    private fun setupContentDescription() {
        // el texto alternativo también se coloca sobre el binding.root
        // (la card que contiene todas las vistas)
        // este método debe ser llamado cada vez que se cambie title o subtitle,
        // para actualizar la lectura accesible junto con lo que se cambia visualmente.
        binding.root.contentDescription = "$title, $subtitle"
    }
}
