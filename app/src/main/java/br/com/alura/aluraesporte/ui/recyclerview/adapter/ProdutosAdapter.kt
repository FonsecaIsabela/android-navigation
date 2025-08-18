package br.com.alura.aluraesporte.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.aluraesporte.databinding.ItemProdutoBinding
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.model.Produto

class ProdutosAdapter(
    private val produtos: MutableList<Produto> = mutableListOf(),
    var onItemClickListener: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ProdutosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProdutoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(produtos[position])
    }

    fun atualiza(produtosNovos: List<Produto>) {
        notifyItemRangeRemoved(0, produtos.size)
        produtos.clear()
        produtos.addAll(produtosNovos)
        notifyItemRangeInserted(0, produtos.size)
    }

    inner class ViewHolder(private val binding: ItemProdutoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var produto: Produto

        init {
            binding.root.setOnClickListener {
                if (::produto.isInitialized) {
                    onItemClickListener(produto)
                }
            }
        }

        fun vincula(produto: Produto) {
            this.produto = produto
            binding.itemProdutoNome.text = produto.nome
            binding.itemProdutoPreco.text = produto.preco.formatParaMoedaBrasileira()
        }
    }
}