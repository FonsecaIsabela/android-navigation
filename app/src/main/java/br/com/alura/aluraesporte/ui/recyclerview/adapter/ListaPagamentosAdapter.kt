package br.com.alura.aluraesporte.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.aluraesporte.databinding.ItemPagamentoBinding
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.model.Pagamento

class ListaPagamentosAdapter(
    private val context: Context,
    private val pagamentos: MutableList<Pagamento> = mutableListOf()
) : RecyclerView.Adapter<ListaPagamentosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPagamentoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = pagamentos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincula(pagamentos[position])
    }

    fun add(pagamentosNovos: List<Pagamento>) {
        notifyItemRangeRemoved(0, pagamentos.size)
        pagamentos.clear()
        pagamentos.addAll(pagamentosNovos)
        notifyItemRangeInserted(0, pagamentos.size)
    }

    inner class ViewHolder(private val binding: ItemPagamentoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(pagamento: Pagamento) {
            binding.itemPagamentoId.text = pagamento.id.toString()
            binding.itemPagamentoPreco.text = pagamento.preco.formatParaMoedaBrasileira()
        }
    }
}
