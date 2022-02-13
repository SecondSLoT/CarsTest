package com.secondslot.carstest.presentation.makes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.secondslot.carstest.App
import com.secondslot.carstest.R
import com.secondslot.carstest.databinding.FragmentMakesBinding
import com.secondslot.carstest.presentation.makes.adapter.MakesPagingAdapter
import com.secondslot.carstest.presentation.makes.di.DaggerMakesComponent
import com.secondslot.carstest.presentation.makes.vm.MakesViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MakesFragment : Fragment(), OnMakeClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _viewModel: MakesViewModel? = null
    private val viewModel get() = requireNotNull(_viewModel)

    private var _binding: FragmentMakesBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val makesAdapter by lazy(LazyThreadSafetyMode.NONE) { MakesPagingAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val makesComponent = DaggerMakesComponent.factory().create(App.appComponent)
        makesComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakesBinding.inflate(inflater, container, false)

        _viewModel = ViewModelProvider(this, viewModelFactory)[MakesViewModel::class.java]

        initViews()
        setObservers()
        return binding.root
    }

    private fun initViews() = binding.run {
        makeTextView.text = getString(R.string.choose_brand)

        makeRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = makesAdapter
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.makes.collectLatest(makesAdapter::submitData)
        }
    }

    override fun onMakeClicked(makeId: Int, make: String) {
        val action = MakesFragmentDirections.actionMakesFragmentToModelsFragment(makeId, make)
        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "MakesFragment"
    }
}
