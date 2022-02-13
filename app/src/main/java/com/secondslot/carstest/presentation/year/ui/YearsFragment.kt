package com.secondslot.carstest.presentation.year.ui

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
import com.secondslot.carstest.databinding.FragmentYearsBinding
import com.secondslot.carstest.presentation.year.adapter.YearsListAdapter
import com.secondslot.carstest.presentation.year.di.DaggerYearsComponent
import com.secondslot.carstest.presentation.year.vm.YearsViewModel
import javax.inject.Inject

class YearsFragment : Fragment(), OnYearClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _viewModel: YearsViewModel? = null
    private val viewModel get() = requireNotNull(_viewModel)

    private var _binding: FragmentYearsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val yearsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        YearsListAdapter(this)
    }

    private val args: YearsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val yearsComponent = DaggerYearsComponent.factory().create(App.appComponent)
        yearsComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYearsBinding.inflate(inflater, container, false)

        _viewModel = ViewModelProvider(this, viewModelFactory)[YearsViewModel::class.java]

        initViews()
        setObservers()
        return binding.root
    }

    private fun initViews() = binding.run {
        val text = "${args.make} ${args.model}"
        yearTextView.text = text

        yearRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = yearsAdapter
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            val years = viewModel.loadYears(args.makeId, args.model)
            yearsAdapter.submitList(years.toList())
        }
    }

    override fun onYearClicked(year: Int) {
        val action = YearsFragmentDirections
            .actionYearsFragmentToSummaryFragment(args.make, args.model, year)
        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "YearsFragment"
    }
}
