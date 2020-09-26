package com.mohamed.halim.essa.simplegallery.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.halim.essa.simplegallery.R
import com.mohamed.halim.essa.simplegallery.databinding.AlbumsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AlbumsFragment : Fragment() {
    lateinit var binding: AlbumsFragmentBinding
    lateinit var adapter: AlbumsAdapter
    val viewModel: AlbumsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.albums_fragment, container, false)
        binding.lifecycleOwner = this
        setupRecycleView()
        setupViewModel()
        return binding.root
    }

    private fun setupViewModel() {
        setupAlbumsObserver()
    }

    private fun setupAlbumsObserver() {
        viewModel.albums.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupRecycleView() {
        adapter = AlbumsAdapter(AlbumClickListener {
            Timber.d("Start album : $it")
            findNavController().navigate(AlbumsFragmentDirections.actionAlbumsFragmentToImagesFragment(it))
        })
        val manager = LinearLayoutManager(requireContext())
        binding.albumList.layoutManager = manager
        binding.albumList.adapter = adapter
    }


}