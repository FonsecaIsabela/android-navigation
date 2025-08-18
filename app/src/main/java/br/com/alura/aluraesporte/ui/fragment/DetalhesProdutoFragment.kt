package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.alura.aluraesporte.databinding.DetalhesProdutoBinding
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.ui.viewmodel.DetalhesProdutoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesProdutoFragment : Fragment() {

    private val argumentos by navArgs<DetalhesProdutoFragmentArgs>()

    private val produtoId by lazy {
        argumentos.produtoId
    }
    private val viewModel: DetalhesProdutoViewModel by viewModel { parametersOf(produtoId) }
    private val controlador by lazy { findNavController() }

    private var _binding: DetalhesProdutoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetalhesProdutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscaProduto()
        configuraBotaoComprar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configuraBotaoComprar() {
        binding.detalhesProdutoBotaoComprar.setOnClickListener {
            viewModel.produtoEncontrado.value?.let {
                vaiParaPagamento()
            }
        }
    }

    private fun vaiParaPagamento() {
        val direcao =
            DetalhesProdutoFragmentDirections.actionDetalhesProdutoToPagamento(produtoId)
        controlador.navigate(direcao)
    }

    private fun buscaProduto() {
        viewModel.produtoEncontrado.observe(this, Observer {
            it?.let { produto ->
                binding.detalhesProdutoNome.text = produto.nome
                binding.detalhesProdutoPreco.text = produto.preco.formatParaMoedaBrasileira()
            }
        })
    }

    companion object {
        private const val ID_PRODUTO_INVALIDO = "ID do produto inválido"
    }
}