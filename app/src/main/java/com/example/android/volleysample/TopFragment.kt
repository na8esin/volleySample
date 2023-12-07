/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package com.example.android.volleysample

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.android.volleysample.databinding.TopFragmentBinding
import com.squareup.picasso.Picasso

/**
 * Fragment where the game is played, contains the game logic.
 */
class TopFragment : Fragment() {
    // Binding object instance with access to the views in the game_fragment.xml layout
    private lateinit var binding: TopFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout XML file and return a binding object instance
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.top_fragment,
            container,
            false)

        // なんも表示されない
        Picasso
            .get()
            .load("https://media.geeksforgeeks.org/wp-content/cdn-uploads/logo-new-2.svg")
            .into(binding.imageView);

        Picasso
            .get()
            .load("https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png")
            .into(binding.imageView2);

        val imageLoader = ImageLoader.Builder(requireContext())
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
        val request = ImageRequest.Builder(requireContext())
            .data("https://media.geeksforgeeks.org/wp-content/cdn-uploads/logo-new-2.svg")
            .target(binding.imageView3)
            .build()
        imageLoader.enqueue(request)

        if(Build.VERSION.SDK_INT >= 30) {
            Log.d(TAG, context?.display?.mode?.physicalHeight.toString())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // Specify the fragment view as the lifecycle owner of the binding.
            // This is used so that the binding can observe LiveData updates
            lifecycleOwner = viewLifecycleOwner
        }

        // Setup a click listener for the Submit and Skip buttons.
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GameFragment", "GameFragment destroyed!")
    }

    companion object {
        const val TAG = "TopFragment"
        fun newInstance() = TopFragment()
    }
}
