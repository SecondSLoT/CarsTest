package com.secondslot.carstest.presentation.models.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.secondslot.carstest.App
import com.secondslot.carstest.databinding.FragmentModelsBinding
import com.secondslot.carstest.presentation.models.adapter.ModelsPagingAdapter
import com.secondslot.carstest.presentation.models.di.DaggerModelsComponent
import com.secondslot.carstest.presentation.models.vm.ModelsViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ModelsFragment : Fragment(), OnModelClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _viewModel: ModelsViewModel? = null
    private val viewModel get() = requireNotNull(_viewModel)

    private var _binding: FragmentModelsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val modelsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ModelsPagingAdapter(this)
    }

    private val args: ModelsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val modelsComponent = DaggerModelsComponent.factory().create(App.appComponent)
        modelsComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModelsBinding.inflate(inflater, container, false)

        _viewModel = ViewModelProvider(this, viewModelFactory)[ModelsViewModel::class.java]

        initViews()
        setObservers()
        return binding.root
    }

    private fun initViews() = binding.run {
        modelTextView.text = args.make

        modelRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = modelsAdapter
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.loadModels(args.makeId).collectLatest(modelsAdapter::submitData)
        }
    }

    override fun onModelClick(model: String) {
        val action = ModelsFragmentDirections
            .actionModelsFragmentToYearsFragment(args.makeId, args.make, model)
        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "MakesFragment"
    }
}
