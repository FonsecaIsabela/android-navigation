package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import br.com.alura.aluraesporte.databinding.ListaPagamentosBinding
import br.com.alura.aluraesporte.ui.recyclerview.adapter.ListaPagamentosAdapter
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import br.com.alura.aluraesporte.ui.viewmodel.PagamentoViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaPagamentosFragment : BaseFragment() {

    private var _binding: ListaPagamentosBinding? = null
    private val binding get() = _binding!!

    private val adapter: ListaPagamentosAdapter by inject()
    private val viewModel: PagamentoViewModel by viewModel()
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListaPagamentosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listaPagamentosRecyclerview.adapter = adapter
        viewModel.todos().observe(this, Observer {
            it?.let { pagamentosEncontrados ->
                adapter.add(pagamentosEncontrados)
            }
        })

        estadoAppViewModel.temComponentes = ComponentesVisuais(
            appBar = true,
            bottomNavigation = true
        )
    }
}