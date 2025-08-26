package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.databinding.CadastrarUsuarioBinding
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CadastroUsuarioFragment : Fragment() {

    private var _binding: CadastrarUsuarioBinding? = null
    private val binding get() = _binding!!

    private val controlador by lazy {
        findNavController()
    }

    private val estadoAppViewModel : EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CadastrarUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoAppViewModel.temComponentes = ComponentesVisuais()
        _binding?.cadastroUsuarioBotaoCadastrar?.setOnClickListener {
            controlador.popBackStack()
        }
    }

}