package com.secondslot.carstest.presentation.summary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.secondslot.carstest.R
import com.secondslot.carstest.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args: SummaryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)

        initViews()
        return binding.root
    }

    private fun initViews() = binding.run {
        captionTextView.text = getString(R.string.your_choice)
        val text = "${args.make} ${args.model}"
        summaryTextView.text = text
        yearTextView.text = "${args.year}"
    }
}
